package com.shitouren.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shitouren.core.mp.bo.ThirdUser;

public interface ThirdUserService extends IService<ThirdUser> {

    ThirdUser getByOpenId(Byte type, String openid);

}
