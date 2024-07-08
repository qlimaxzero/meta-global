package com.shitouren.core.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shitouren.core.mp.bo.Guild;

import java.time.LocalDateTime;

public interface GuildMapper extends BaseMapper<Guild> {

    default Guild getGuildByUid(Integer uid) {
        LambdaQueryWrapper<Guild> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Guild::getUserid, uid);
        return selectOne(queryWrapper);
    }

    default Guild getGuildByCode(String code) {
        LambdaQueryWrapper<Guild> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Guild::getCode, code);
        return selectOne(queryWrapper);
    }

    default Guild getGuildByName(String name) {
        LambdaQueryWrapper<Guild> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Guild::getName, name);
        return selectOne(queryWrapper);
    }

    default Guild save(Integer uid, String name, String code) {
        LocalDateTime now = LocalDateTime.now();
        Guild o = Guild.builder().userid(uid).name(name).code(code).updateTime(now).createTime(now).build();
        return insert(o) == 1 ? o : null;
    }

}
