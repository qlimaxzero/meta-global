package com.shitouren.core.service.processor;

import com.shitouren.core.autogenerate.bean.HideRecord;
import com.shitouren.core.autogenerate.bean.HideRecordExample;
import com.shitouren.core.autogenerate.dao.HideRecordDao;
import com.shitouren.core.bean.eums.NftTransferEnum;
import com.shitouren.core.bean.eums.RedDotEnum;
import com.shitouren.core.model.dto.RedDotEventDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 资金流水管理器
 */
@Slf4j
@Component
public class HideRecordProcessor {

    @Resource
    private HideRecordDao hideRecordDao;

    @Resource
    private ApplicationEventPublisher eventPublisher;;

    public Integer add(Integer uid, Integer grantId, NftTransferEnum type, Integer collId, Integer orderId) {
        HideRecord hideRecord = new HideRecord();
        hideRecord.setUserid(uid);
        hideRecord.setType(type.getType());
        hideRecord.setCollid(collId);
        hideRecord.setUserGrantId(grantId);
        hideRecord.setOrderid(orderId);
        hideRecord.setCreateTime(new Date());
        hideRecordDao.insertSelective(hideRecord);

        eventPublisher.publishEvent(new RedDotEventDTO(uid, RedDotEnum.NFT));
        return hideRecord.getId();
    }

    public HideRecord getLastHideRecord(Integer uid) {
        HideRecordExample example = new HideRecordExample();
        example.createCriteria().andUseridEqualTo(uid);
        example.or().andGetuidEqualTo(uid);
        example.setOrderByClause("id desc");
        example.setPageSize(1);
        List<HideRecord> hideRecords = hideRecordDao.selectByExample(example);
        return hideRecords.isEmpty() ? null : hideRecords.get(0);
    }

    public HideRecord getHideRecord(Integer id) {
        return hideRecordDao.selectByPrimaryKey(id);
    }

    public List<HideRecord> selectRecords(Integer uid) {
        HideRecordExample example = new HideRecordExample();
        example.createCriteria().andUseridEqualTo(uid);
        example.setOrderByClause("id desc");
        return hideRecordDao.selectByExample(example);
    }
}
