package com.shitouren.core.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shitouren.core.mp.bo.ThirdUser;
import com.shitouren.core.mp.mapper.ThirdUserMapper;
import org.springframework.stereotype.Service;

@Service
public class ThirdUserServiceImpl extends ServiceImpl<ThirdUserMapper, ThirdUser> implements ThirdUserService {

    @Override
    public ThirdUser getByOpenId(Byte type, String openid) {
        LambdaQueryWrapper<ThirdUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ThirdUser::getType, type);
        queryWrapper.eq(ThirdUser::getOpenid, openid);
        return this.getOne(queryWrapper);
    }

}
