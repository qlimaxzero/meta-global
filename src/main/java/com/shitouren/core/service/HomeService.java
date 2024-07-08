package com.shitouren.core.service;

import cn.hutool.json.JSONObject;
import com.alipay.api.AlipayApiException;
import com.shitouren.core.model.vo.BlindBoxVO;

import java.util.List;
import java.util.Map;

public interface HomeService {

    String bannerdetails(int id);

    Map show();

    Map getHomePageDetail(int userid, int type);

    List calendarl();

    List calendarls(int type, String neir);

    Map details(int userid, int id);

    Map viewall(int userid, int id, int StartRow);

    int Confirmorder(int userid, int id, String orderno, String faceToken);

    Map Confirmorderdetails(int userid, int id);

    int ordertype(int id);

    Map Payorder(int userid, int id, int type, String pass, int buycard, String outOrderNo) throws AlipayApiException;

    void cancelorder(int userid, int id);

    void mintnft(int collectionid, int user_grantid);

    void adduser(int uid, String address, String privateKey);

    Map hqaddress();

    String agreementss(int id);

    void upmintnfts();

    Map goodsdetails(int logUserPid, int id);

    String lotteryDraw(int logUserPid);

    List goods();

    Map lottery(int logUserPid);

    void upmintnft1();

    Map sign(int logUserPid);

    void followAccount(int logUserPid, String followaccount);

    void daySign(int logUserPid);

    Map nftgood(Integer logUserPid);

    Map creatergood(Integer logUserPid);

    List moneylist(int logUserPid);

    List balancelist(int logUserPid);

    Map version();

    List ranklist();

    List<Map<String, Object>> getBanners();

    List<BlindBoxVO> blindbox(int logUserPid);

    List classification();

    void exchange(int logUserPid, int number);

    List data();

    void test();

    Map yuanyuzhou(int userid);

    void exchangeWhiteList(int logUserPid, int number);

    String check(String phone, String xmno);

    JSONObject getActivityData(String val);

}
