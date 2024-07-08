package com.shitouren.core.service.processor;

import com.alibaba.fastjson.JSON;
import com.shitouren.core.autogenerate.bean.BalanceRecord;
import com.shitouren.core.autogenerate.dao.BalanceRecordDao;
import com.shitouren.core.bean.eums.BalanceTypeEnum;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 余额流水记录
 */
@Slf4j
@Component
public class BalanceRecordProcessor {

    @Resource
    private BalanceRecordDao balanceRecordDao;

    /**
     * 添加一条余额流水
     **/
    @Transactional(rollbackFor = Exception.class)
    public void add(int userId, BigDecimal amt, BalanceTypeEnum typeEnum, Integer extra, BigDecimal currentBalance) {
        add(userId, amt, typeEnum, extra, null, currentBalance);
    }

    @Transactional(rollbackFor = Exception.class)
    public void add(int userId, BigDecimal amt, BalanceTypeEnum typeEnum, Integer extra, String desc, BigDecimal currentBalance) {
        val record = new BalanceRecord();
        record.setCreatetime(new Date());
        record.setUserid(userId);
        record.setCount(amt);
        record.setType(typeEnum.getType());
        record.setName(desc);
        record.setExtra(extra == null ? null : String.valueOf(extra));
        record.setCurrentBalance(currentBalance);
        balanceRecordDao.insertSelective(record);
        log.info("余额流水添加成功, record = {}", JSON.toJSONString(record));
    }

}
