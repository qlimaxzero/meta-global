package com.shitouren.core.service;

import com.shitouren.core.autogenerate.bean.Blindbox;
import com.shitouren.core.autogenerate.bean.Issue;
import com.shitouren.core.autogenerate.bean.MyOrder;
import com.shitouren.core.autogenerate.dao.BlindboxDao;
import com.shitouren.core.autogenerate.dao.IssueDao;
import com.shitouren.core.autogenerate.dao.MyOrderDao;
import com.shitouren.core.bean.eums.IdEnum;
import com.shitouren.core.bean.eums.OrderStatusEnum;
import com.shitouren.core.config.exception.ExceptionConstant;
import com.shitouren.core.utils.AssertUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final MyOrderDao myOrderDao;
    private final BlindboxDao blindboxDao;
    private final IssueDao issueDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MyOrder createBlindBoxOrder(Integer uid, OrderStatusEnum statusEnum, BigDecimal price, Integer collId,
                                       Integer issueId, Integer payType, Integer num) {
        MyOrder entity = new MyOrder();
        entity.setUserid(uid);
        entity.setOrderno(IdEnum.BLIND_BOX.getRid());
        entity.setCollid(collId);
        entity.setIstype(2);
        entity.setPrice(price);
        entity.setCreatetime(new Date());
        entity.setEndtime(new Date());
        entity.setOrdertype(statusEnum.getStatus());
        entity.setPaytype(payType);
        //entity.setCyid(grantId);
        entity.setIssueId(issueId);
        entity.setSuccessTime(new Date());
        entity.setGrants(num);
        int i = myOrderDao.insertSelective(entity);
        return i == 1 ? entity : null;
    }

    @Override
    public MyOrder getById(Integer id) {
        return myOrderDao.selectByPrimaryKey(id);
    }

    @Override
    public Blindbox getByIssueId(Integer issueId) {
        Issue issue = issueDao.selectByPrimaryKey(issueId);
        AssertUtil.notNull(issue, ExceptionConstant.参数异常);
        return blindboxDao.selectByPrimaryKey(issue.getCollid());
    }

    @Override
    public Blindbox getBlindBoxById(Integer orderId) {
        MyOrder myOrder = myOrderDao.selectByPrimaryKey(orderId);
        AssertUtil.notNull(myOrder, ExceptionConstant.参数异常);
        Issue issue = issueDao.selectByPrimaryKey(myOrder.getIssueId());
        AssertUtil.notNull(issue, ExceptionConstant.参数异常);
        return blindboxDao.selectByPrimaryKey(issue.getCollid());
    }

}
