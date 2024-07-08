package com.shitouren.core.service;

import com.shitouren.core.autogenerate.bean.Blindbox;
import com.shitouren.core.autogenerate.bean.MyOrder;
import com.shitouren.core.bean.eums.OrderStatusEnum;

import java.math.BigDecimal;

public interface OrderService {
    MyOrder createBlindBoxOrder(Integer uid, OrderStatusEnum statusEnum, BigDecimal price,
                                Integer collId, Integer issueId, Integer payType, Integer num);

    MyOrder getById(Integer id);

    Blindbox getByIssueId(Integer issueId);

    Blindbox getBlindBoxById(Integer orderId);

}
