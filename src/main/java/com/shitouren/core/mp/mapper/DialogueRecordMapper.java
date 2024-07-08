package com.shitouren.core.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shitouren.core.autogenerate.bean.UserGrant;
import com.shitouren.core.common.Constants;
import com.shitouren.core.mp.bo.DialogueRecord;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface DialogueRecordMapper extends BaseMapper<DialogueRecord> {

    default void save(Integer uid, UserGrant grant, String question, String answer, BigDecimal useHealth) {
        DialogueRecord entity = new DialogueRecord();
        entity.setUserid(uid);
        entity.setNftId(grant.getId());
        entity.setDialogueId(grant.getDialogueId());
        entity.setQ(question);
        entity.setA(answer);
        entity.setUseHealth(useHealth);
        entity.setCreateTime(LocalDateTime.now());
        entity.setDeleted(false);
        insert(entity);
    }

    default List<DialogueRecord> selectLast(Integer uid, Integer nftId) {
        LambdaQueryWrapper<DialogueRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DialogueRecord::getUserid, uid);
        queryWrapper.eq(DialogueRecord::getNftId, nftId);
        queryWrapper.orderByDesc(DialogueRecord::getId);
        queryWrapper.last("limit " + Constants.DIALOGUE_RECORD_LIMIT);
        return selectList(queryWrapper);
    }

    default void deleteList(Integer uid, Integer nftId) {
        LambdaUpdateWrapper<DialogueRecord> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(DialogueRecord::getUserid, uid);
        updateWrapper.eq(DialogueRecord::getNftId, nftId);
        updateWrapper.set(DialogueRecord::isDeleted, true);
        update(null, updateWrapper);
    }

}
