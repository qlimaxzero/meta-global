package com.shitouren.core.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shitouren.core.mp.bo.GuildMember;

import java.time.LocalDateTime;
import java.util.List;

public interface GuildMemberMapper extends BaseMapper<GuildMember> {

    default int save(Integer uid, Integer guildId) {
        GuildMember o = GuildMember.builder().guildId(guildId).userid(uid).createTime(LocalDateTime.now()).build();
        return insert(o);
    }

    default GuildMember getGuildByMember(Integer uid) {
        LambdaQueryWrapper<GuildMember> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(GuildMember::getUserid, uid);
        return selectOne(queryWrapper);
    }

    default List<GuildMember> selectMembers(Integer guildId) {
        LambdaQueryWrapper<GuildMember> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(guildId != null, GuildMember::getGuildId, guildId);
        return selectList(queryWrapper);
    }

}
