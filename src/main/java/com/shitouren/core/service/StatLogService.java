package com.shitouren.core.service;

import com.shitouren.core.model.vo.StatLogVO;

public interface StatLogService {

    void log(StatLogVO vo, String ipAddr, Integer uid);

}
