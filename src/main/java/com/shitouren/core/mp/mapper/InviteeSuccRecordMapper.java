package com.shitouren.core.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shitouren.core.mp.bo.InviteeSuccRecord;


public interface InviteeSuccRecordMapper extends BaseMapper<InviteeSuccRecord> {

    default Integer count(Integer inviterId, Integer inviteeId) {
        LambdaQueryWrapper<InviteeSuccRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(InviteeSuccRecord::getInviterId, inviterId);
        queryWrapper.eq(InviteeSuccRecord::getInviteeId, inviteeId);
        return this.selectCount(queryWrapper);
    }

}
