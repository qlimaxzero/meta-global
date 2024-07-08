package com.shitouren.core.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.shitouren.core.common.Constants;
import com.shitouren.core.autogenerate.bean.*;
import com.shitouren.core.autogenerate.dao.MyOrderDao;
import com.shitouren.core.config.exception.ExceptionConstant;
import com.shitouren.core.config.interceptor.WebContextHolder;
import com.shitouren.core.config.pay.pi.PiClient;
import com.shitouren.core.config.redis.CloudRedisTemplate;
import com.shitouren.core.model.dto.VirtualCoinDTO;
import com.shitouren.core.model.vo.pi.*;
import com.shitouren.core.mp.bo.ThirdUser;
import com.shitouren.core.utils.AssertUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.shitouren.core.bean.eums.OrderStatusEnum.*;

@Slf4j
@Service
public class PiServiceImpl implements PiService {

    public static final Byte THIRD_TYPE_PI = 1;

    public static final Byte AUTH_SUCC = 1;

    @Resource
    private ThirdUserService thirdUserService;

    @Resource
    private MyOrderDao myOrderDao;

    @Resource
    private HeePayService heePayService;

    @Resource
    private PiClient piClient;

    @Resource(name = "cloudRedisTemplate")
    private CloudRedisTemplate redisTemplate;

    public void auth(LoginVO vo) {
        JSONObject jsonObject = piClient.auth(vo.getAccessToken());
        AssertUtil.isTrue(StrUtil.equals(jsonObject.getStr("uid"), vo.getUid()), ExceptionConstant.参数异常);

        // 检查pi用户是否存在
        ThirdUser thirdUser = thirdUserService.getByOpenId(THIRD_TYPE_PI, vo.getUid());
        if (thirdUser != null) {
            AssertUtil.isTrue(Objects.equals(WebContextHolder.get(), thirdUser.getUserid()), ExceptionConstant.当前π账户已被绑定);
            // 如果用户存在更新token
            if (!thirdUser.getToken().equals(vo.getAccessToken())) {
                thirdUser.setToken(vo.getAccessToken());
                thirdUser.setUpdatetime(LocalDateTime.now());

                thirdUserService.updateById(thirdUser);
            }
        } else {
            // 新用户
            thirdUser = new ThirdUser();
            thirdUser.setOpenid(vo.getUid());
            thirdUser.setToken(vo.getAccessToken());
            thirdUser.setNickname(jsonObject.getStr("username"));
            thirdUser.setRawUserInfo(jsonObject.toString());
            thirdUser.setType(THIRD_TYPE_PI);
            thirdUser.setStatus(AUTH_SUCC);
            thirdUser.setUserid(WebContextHolder.get());
            thirdUser.setUpdatetime(LocalDateTime.now());
            thirdUser.setCreatetime(LocalDateTime.now());

            thirdUserService.save(thirdUser);
        }
    }


    @Override
    public void incomplete(IncompleteVO vo) {
        // 校验链上信息
        JSONObject resp = piClient.blockChainTransQuery(vo.getLink());
        String paymentId = resp.getStr("memo");//pi订单id
        Boolean successful = resp.getBool("successful", false);
        AssertUtil.isTrue(successful && StrUtil.equals(paymentId, vo.getPaymentId()), ExceptionConstant.订单不存在);

        // 该订单已完成确认, 仅需调整状态即可
        MyOrder order = this.getOrderByOutNo(vo.getPaymentId());
        AssertUtil.notNull(order, ExceptionConstant.订单不存在);
        if (order.getOrdertype() == CANCEL.getStatus()) {
            order.setOrdertype(CALLBACK.getStatus());
            order.setGrants(0);
            myOrderDao.updateByPrimaryKeySelective(order);
        }

        // 通过后进行支付完成处理
        CompleteVO completeVO = new CompleteVO();
        completeVO.setPaymentId(vo.getPaymentId());
        completeVO.setTxid(vo.getTxid());
        this.complete(completeVO);
    }

    @Override
    public void approve(Integer orderId, String paymentId, BigDecimal expPayAmt) {
        // 获取支付信息并进行相关校验
        JSONObject resp = piClient.paymentInfo(paymentId);
        JSONObject metadata = resp.getJSONObject("metadata");
        AssertUtil.isTrue(metadata != null && metadata.getInt("orderId", 0).equals(orderId),
                ExceptionConstant.订单号错误);
        AssertUtil.isTrue(expPayAmt.compareTo(resp.getBigDecimal("amount")) == 0, ExceptionConstant.金额不对);

        // 信息真实，通知PI我准备好了，可以付款了
        JSONObject respApprove = piClient.paymentApprove(paymentId);
        Boolean approved = respApprove.getJSONObject("status").getBool("developer_approved", false);
        AssertUtil.isTrue(approved, ExceptionConstant.订单确认失败);
    }

    @Override
    public void complete(CompleteVO vo) {
        String lockKey = Constants.PI_COMPLETE_LOCK_KEY + vo.getPaymentId();
        boolean lock = redisTemplate.getLock(lockKey, vo.getTxid(), 60);
        AssertUtil.isTrue(lock, ExceptionConstant.重复请求);

        MyOrder order = this.getOrderByOutNo(vo.getPaymentId());
        AssertUtil.notNull(order, ExceptionConstant.订单不存在);
        AssertUtil.isTrue(order.getOrdertype() == CALLBACK.getStatus(), ExceptionConstant.订单状态错误);

        // 通知PI完成交易
        JSONObject resp = piClient.paymentComplete(vo.getPaymentId(), vo.getTxid());
        JSONObject respStatus = resp.getJSONObject("status");
        Boolean approved = respStatus.getBool("developer_approved", false);
        Boolean verified = respStatus.getBool("transaction_verified", false);
        Boolean completed = respStatus.getBool("developer_completed", false);
        AssertUtil.isTrue(approved && verified && completed, ExceptionConstant.订单完成错误);

        // 支付完成业务处理
        VirtualCoinDTO dto = new VirtualCoinDTO();
        dto.setTxid(vo.getTxid());
        dto.setFromAddr(resp.getStr("from_address"));
        heePayService.updPay(order.getOrderno(), resp.getBigDecimal("amount"), dto);

        boolean release = redisTemplate.releaseLock(lockKey, vo.getTxid());
        AssertUtil.isTrue(release, ExceptionConstant.订单完成错误);
    }

    @Override
    public void cancel(PaymentVO vo) {
        MyOrder order = this.getOrderByOutNo(vo.getPaymentId());
        AssertUtil.notNull(order, ExceptionConstant.订单不存在);
        AssertUtil.isFalse(order.getOrdertype() == COMPLETE.getStatus(), ExceptionConstant.订单已支付);

        // 恢复商品库存
        order.setOrdertype(CANCEL.getStatus());// 订单关闭
        myOrderDao.updateByPrimaryKey(order);
    }


    private MyOrder getOrderByOutNo(String outOrderNo) {
        MyOrderExample example = new MyOrderExample();
        example.createCriteria().andOutOrderNoEqualTo(outOrderNo);
        List<MyOrder> myOrders = myOrderDao.selectByExample(example);
        return myOrders.isEmpty() ? null : myOrders.get(0);
    }

}
