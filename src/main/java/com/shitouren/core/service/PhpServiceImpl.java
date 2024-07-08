package com.shitouren.core.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.math.Money;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.ImmutableMap;
import com.shitouren.core.autogenerate.bean.Task;
import com.shitouren.core.autogenerate.bean.TaskExample;
import com.shitouren.core.autogenerate.bean.Users;
import com.shitouren.core.autogenerate.dao.TaskDao;
import com.shitouren.core.autogenerate.dao.UsersDao;
import com.shitouren.core.bean.eums.BalanceTypeEnum;
import com.shitouren.core.bean.eums.IdEnum;
import com.shitouren.core.enums.BalanceOrderStatusEnum;
import com.shitouren.core.bean.eums.PointsTypeEnum;
import com.shitouren.core.model.vo.php.*;
import com.shitouren.core.mp.bo.BalanceOrder;
import com.shitouren.core.mp.bo.BalanceOrderAlloc;
import com.shitouren.core.config.exception.ExceptionConstant;
import com.shitouren.core.config.redis.CloudRedisTemplate;
import com.shitouren.core.mp.mapper.BalanceOrderAllocMapper;
import com.shitouren.core.mp.mapper.BalanceOrderMapper;
import com.shitouren.core.model.vo.php.PhpShopPointsReqVO;
import com.shitouren.core.service.processor.BalanceRecordProcessor;
import com.shitouren.core.service.processor.TaskProcessor;
import com.shitouren.core.utils.AssertUtil;
import com.shitouren.core.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class PhpServiceImpl implements PhpService {

    @Resource(name = "cloudRedisTemplate")
    private CloudRedisTemplate redisTemplate;

    @Resource
    private UserService userService;

    @Resource
    private BalanceRecordProcessor balanceRecordProcessor;

    @Resource
    private UsersDao userDao;

    @Resource
    private TaskDao taskDao;

    @Resource
    private TaskProcessor taskProcessor;

    @Resource
    private BalanceOrderMapper balanceOrderMapper;

    @Resource
    private BalanceOrderAllocMapper balanceOrderAllocMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void shopPoints(PhpShopPointsReqVO reqVO) {
        Users user = userService.getUser(reqVO.getUsername());
        AssertUtil.notNull(user, ExceptionConstant.账户名不存在);

        // 商城或者商品时, 校验是否重复处理
        if (reqVO.getType() == PointsTypeEnum.SHOP || reqVO.getType() == PointsTypeEnum.PRODUCT) {
            List<Task> taskList = getTaskList(reqVO, user);
            AssertUtil.isEmpty(taskList, ExceptionConstant.此任务已做);
        }

        BigDecimal currentPoints = user.getMoney().add(reqVO.getPoints());
        AssertUtil.isFalse(currentPoints.intValue() < 0, ExceptionConstant.积分不足);

        userDao.addMoneySafely(reqVO.getPoints(), user.getUserId(), user.getMoney());
        taskProcessor.addTask(user.getUserId(), reqVO.getPoints(), reqVO.getType(), null, reqVO.getOtherId(),
                reqVO.getExtend(), currentPoints);
    }

    @Override
    public Map<String, Object> userPoints(String username) {
        Users user = userService.getUser(username);
        AssertUtil.notNull(user, ExceptionConstant.账户名不存在);

        return ImmutableMap.of("points", user.getMoney(), "balance", user.getBalance());
    }

    private List<Task> getTaskList(PhpShopPointsReqVO reqVO, Users user) {
        TaskExample example = new TaskExample();
        TaskExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(user.getUserId())
                .andStateEqualTo(reqVO.getType().getType())
                .andOtherIdEqualTo(reqVO.getOtherId());
        if (reqVO.getPoints().compareTo(BigDecimal.ZERO) > 0) {
            criteria.andPriceGreaterThan(BigDecimal.ZERO);
        } else {
            criteria.andPriceLessThan(BigDecimal.ZERO);
        }
        return taskDao.selectByExample(example);
    }

    private String getRemark(PhpShopPointsReqVO reqVO) {
        return StrUtil.join(StrUtil.COLON, reqVO.getType().getDesc(), reqVO.getExtend());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String balanceOrderSubmit(PhpBalanceOrderSubmitReqVO reqVO) {
        Users user = userService.getUser(reqVO.getUsername());
        AssertUtil.notNull(user, ExceptionConstant.账户名不存在);

        boolean exist = balanceOrderMapper.checkExist(reqVO.getOrderNo(), reqVO.getSource());
        AssertUtil.isFalse(exist, ExceptionConstant.订单已存在);

        Map<String, String> map = new HashMap<>();
        BigDecimal sum = this.resolveAllocDetail(reqVO.getAllocDetail(), map);
        AssertUtil.isFalse(sum.compareTo(reqVO.getAmount()) > 0, ExceptionConstant.金额不对);

        BalanceOrder record = new BalanceOrder();
        record.setUserid(user.getUserId());
        record.setOrderNo(reqVO.getOrderNo());
        record.setAmount(reqVO.getAmount());
        record.setAllocDetail(StringUtil.map2Str(map, StrUtil.COMMA, StrUtil.COLON));
        record.setSubject(reqVO.getSubject());
        record.setStatus(BalanceOrderStatusEnum.PAY.getStatus());
        record.setRemark(reqVO.getRemark());
        record.setTradeTime(DateUtil.parseLocalDateTime(reqVO.getTradeTime()));
        record.setSource(reqVO.getSource());
        record.setSn(IdEnum.RECHARGE.getRid());
        record.setUpdatetime(LocalDateTime.now());
        record.setCreatetime(LocalDateTime.now());
        balanceOrderMapper.insert(record);

        BigDecimal amount = reqVO.getAmount().negate();
        this.handleBalance(user, reqVO.getOrderNo(), amount, BalanceOrderStatusEnum.PAY, record.getId());

        return record.getSn();
    }

    /**
     * 更新用户余额并记录
     *
     * @param user       用户
     * @param orderNo    余额订单号
     * @param amount     操作变更金额
     * @param statusEnum 订单状态
     * @param bid        余额支付订单id
     */
    private void handleBalance(Users user, String orderNo, BigDecimal amount, BalanceOrderStatusEnum statusEnum, Integer bid) {
        BigDecimal balance = new Money(user.getBalance()).add(new Money(amount)).getAmount();
        int i = userDao.updateBalanceByCAS(user.getUserId(), user.getBalance(), balance);
        AssertUtil.isFalse(i == 0, ExceptionConstant.余额更新失败);
        log.info("<{}> userId {} orderNo {} oldBalance {} newBalance {}",
                statusEnum, user.getUserId(), orderNo, user.getBalance(), balance);
        balanceRecordProcessor.add(user.getUserId(), amount, BalanceTypeEnum.SHOP, bid,
                statusEnum.getBalanceDesc(), balance);
    }


    /**
     * 解析分账参数重新组装
     */
    private BigDecimal resolveAllocDetail(String allocDetail, Map<String, String> map) {
        BigDecimal amt = BigDecimal.ZERO;
        String[] arr = allocDetail.split(StrUtil.COMMA);
        for (String str : arr) {
            String[] kv = str.split(StrUtil.COLON);
            Users tmpUser = userService.getUser(kv[0]);
            AssertUtil.notNull(tmpUser, ExceptionConstant.分账账户名不存在);
            map.put(String.valueOf(tmpUser.getUserId()), kv[1]);
            amt = amt.add(new BigDecimal(kv[1]));
        }
        return amt;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void balanceOrderConfirm(PhpBalanceOrderConfirmReqVO reqVO) {
        AssertUtil.isFalse(StrUtil.isBlank(reqVO.getSn()) && StrUtil.isBlank(reqVO.getOrderNo()),
                ExceptionConstant.参数异常);
        BalanceOrder order;
        if (StrUtil.isNotBlank(reqVO.getSn())) {
            order = balanceOrderMapper.getBySn(reqVO.getSn());
            AssertUtil.notNull(order, ExceptionConstant.流水号不存在);
        } else {
            order = balanceOrderMapper.getByOrderNo(reqVO.getOrderNo(), reqVO.getSource());
            AssertUtil.notNull(order, ExceptionConstant.订单号错误);
        }

        AssertUtil.isFalse(BalanceOrderStatusEnum.isFinalState(order.getStatus()), ExceptionConstant.订单状态错误);
        AssertUtil.isTrue(BalanceOrderStatusEnum.isFinalState(reqVO.getStatus()), ExceptionConstant.订单状态错误);

        order.setStatus(reqVO.getStatus().getStatus());
        order.setUpdatetime(LocalDateTime.now());
        balanceOrderMapper.updateById(order);

        // 订单取消操作退款
        if (BalanceOrderStatusEnum.CANCEL == reqVO.getStatus()) {
            Users user = userDao.selectByPrimaryKey(order.getUserid());
            this.handleBalance(user, order.getOrderNo(), order.getAmount(), BalanceOrderStatusEnum.CANCEL, order.getId());
            return;
        }

        // 订单确认开始分账
        String[] arr = StrUtil.split(order.getAllocDetail(), StrUtil.COMMA);
        for (String str : arr) {
            String[] kv = StrUtil.split(str, StrUtil.COLON);
            Users user = userDao.selectByPrimaryKey(Integer.valueOf(kv[0]));
            BigDecimal amount = new BigDecimal(kv[1]);
            this.handleBalance(user, order.getOrderNo(), amount, BalanceOrderStatusEnum.CONFIRM, order.getId());

            BalanceOrderAlloc record = new BalanceOrderAlloc();
            record.setBalanceId(order.getId());
            record.setAmount(amount);
            record.setSellerId(user.getUserId());
            record.setCreatetime(LocalDateTime.now());
            balanceOrderAllocMapper.insert(record);
        }
    }

}
