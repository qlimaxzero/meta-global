package com.shitouren.core.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CollectionService {
    List showColl(int userid);

    List showinfo(int userid,int collid);

    List search(int userid, String search);

    Map details(int userid,int id);

    String Copylink(int userid);

    Map certificate(int userid, int id);

    void marketUp(Integer userid, Integer id, BigDecimal price, String pass);

    void Passon(int userid, int id, String phone, String pass);

    void marketDown(Integer userid, Integer id);

    Map canshu();

    BigDecimal sellmoney(int logUserPid, BigDecimal money);
}
