package com.shitouren.core.config.redisListener;

import com.shitouren.core.autogenerate.bean.*;
import com.shitouren.core.autogenerate.dao.*;
import com.shitouren.core.config.exception.CloudException;
import com.shitouren.core.config.exception.ExceptionConstant;
import com.shitouren.core.config.redis.CloudRedisTemplate;
import com.shitouren.core.config.redis.RedisKeyExpirationService;
import com.shitouren.core.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * @description: 订单过期监听实现类
 * @author: Donugh
 * @create: 2023-05-11 17:01
 **/
@Slf4j
@Service("OrderExpireImpl")
public class OrderExpireImpl implements RedisKeyExpirationService {
    @Autowired
    MyOrderDao myOrderDao;
    @Autowired
    CollectionDao collectionDao;
    @Autowired
    IssueDao issueDao;
    @Autowired
    CancelRecordDao cancelRecordDao;
    @Autowired
    BlindboxDao blindboxDao;
    @Autowired
    CloudRedisTemplate cloudRedisTemplate;
    @Autowired
    UsersDao usersDao;
    @Resource
    UserService userService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void handle(String key) {
        String lockKey = "dopai:lock:OrderExpireImpl:handle:"  + key;
        String lockValue = UUID.randomUUID().toString();
        boolean isLocked = cloudRedisTemplate.getLock(lockKey, lockValue, 5);
        if (!isLocked) {
            log.warn("getLock failed orderId={}", key);
            return;
        }
        MyOrder myOrder = myOrderDao.selectByPrimaryKey(Integer.valueOf(key));
        if (myOrder.getOrdertype() == 2) {
            log.warn("orderId={} already cancel", key);
            return;
        }
        myOrder.setOrdertype(2);
        int i1 = myOrderDao.updateByPrimaryKeySelective(myOrder);
        log.info("i1 = {}", i1);
        if (i1 == 0) {
            return;
        }
        final Integer issueId = myOrder.getIssueId();
        if (myOrder.getIstype() == 1) {
            Collection collections = collectionDao.selectByPrimaryKey(myOrder.getCollid());
            if (collections != null) {
                IssueExample issueExample = new IssueExample();
                issueExample.createCriteria().andCollidEqualTo(myOrder.getCollid()).andIdEqualTo(issueId);
                issueExample.setOrderByClause("endtime desc");
                List<Issue> issues = issueDao.selectByExample(issueExample);
                if (CollectionUtils.isNotEmpty(issues)) {
                    final int i = issueDao.increaseSoldByPrimaryKey(issueId, -1);
                    if (i == 0) {
                        log.error("订单反减失败, 扣减数量不足, issue.id = {}", issues.get(0).getId());
                        throw new CloudException(ExceptionConstant.更新失败);
                    }
                }

                CancelRecord cancelRecord = new CancelRecord();
                cancelRecord.setUserid(myOrder.getUserid());
                cancelRecord.setImg(collections.getImg());
                cancelRecord.setName(collections.getName());
                cancelRecord.setNo(myOrder.getOrderno());
                cancelRecord.setPrice(myOrder.getPrice());
                cancelRecordDao.insertSelective(cancelRecord);
            }
        } else {
            Blindbox blindbox = blindboxDao.selectByPrimaryKey(myOrder.getCollid());
            if (blindbox != null) {
                IssueExample issueExample = new IssueExample();
                issueExample.createCriteria().andCollidEqualTo(myOrder.getCollid()).andIdEqualTo(issueId);
                List<Issue> issues = issueDao.selectByExample(issueExample);
                if (!issues.isEmpty()) {
                    final int i = issueDao.increaseSoldByPrimaryKey(issueId, -1);
                    if (i == 0) {
                        log.error("订单反减失败, 扣减数量不足, issue.id = {}", issues.get(0).getId());
                        throw new CloudException(ExceptionConstant.更新失败);
                    }
                }
                CancelRecord cancelRecord = new CancelRecord();
                cancelRecord.setUserid(myOrder.getUserid());
                cancelRecord.setImg(blindbox.getImg());
                cancelRecord.setName(blindbox.getName());
                cancelRecord.setNo(myOrder.getOrderno());
                cancelRecord.setPrice(myOrder.getPrice());
                cancelRecordDao.insertSelective(cancelRecord);
            }


        }

        //订单过期返还用户5积分
        userService.returnPoints(myOrder.getUserid());

        boolean b = cloudRedisTemplate.releaseLock(lockKey, lockValue);
        if (!b) {
            throw new CloudException("并发订单处理失败");
        }
        //redis乐观锁控制预售数量
        // cloudRedisTemplate.watchLock(BusinessConstant.ISSUE_KEY + issueId, 1,false);
    }
}
