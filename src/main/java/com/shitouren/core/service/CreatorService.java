package com.shitouren.core.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 */
public interface CreatorService {


    void creator(int logUserPid,String name,String content);

    List classification(int logUserPid);

    void addcreator(int logUserPid, String name, String img, String contentimg, String content, String copyright, BigDecimal price, int number, int classid,int id);

    void cancelcreat(int logUserPid, int id);

    Map editcreat(int logUserPid, int id);

    List creatlist(int logUserPid);

    Map puton(int logUserPid, int id);

    Map Payorder(int logUserPid, int id, int type);
}
