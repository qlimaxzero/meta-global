package com.shitouren.core.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shitouren.core.mp.bo.Files;

import java.time.LocalDateTime;

public interface FilesMapper extends BaseMapper<Files> {

    default int insert(Integer userId, Integer type, String path) {
        Files entity = new Files();
        entity.setUserid(userId);
        entity.setType(type);
        entity.setUrl(path);
        entity.setCreatetime(LocalDateTime.now());
        return this.insert(entity);
    }

}
