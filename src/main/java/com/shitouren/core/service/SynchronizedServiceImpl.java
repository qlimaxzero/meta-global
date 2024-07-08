package com.shitouren.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class SynchronizedServiceImpl implements SynchronizedService {
    @Autowired
    private HomeService homeService;
    @Autowired
    private MineService mineService;

    @Override
    public  int Confirmorder(int userid, int id, String orderno, String faceToken) {
        return homeService.Confirmorder(userid, id, orderno, faceToken);
    }

    @Override
    public  Map openbox(int userid, int id, String no) {
        return mineService.openbox(userid, id, no);
    }

    @Override
    public  Map synthesis(int userid, int id) {
        return mineService.synthesis(userid, id);
    }

    @Override
    public  void withdrawal(int userId, BigDecimal count, String pass) {
        mineService.withdrawal(userId, count, pass);
    }
}
