package com.shitouren.core.service;

import java.math.BigDecimal;
import java.util.Map;

public interface SynchronizedService {
    int Confirmorder(int userid, int id, String orderno, String faceTok);

    Map openbox(int userid, int id, String no);

    Map synthesis(int userid, int id);

    void withdrawal(int userId, BigDecimal count, String pass);
}
