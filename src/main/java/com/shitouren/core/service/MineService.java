package com.shitouren.core.service;

import com.alipay.api.AlipayApiException;
import com.shitouren.core.autogenerate.bean.Collection;
import com.shitouren.core.bean.param.RealNameParam;
import com.shitouren.core.bean.param.SysUserParam;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface MineService {

    Map<String, Object> getMineInfo(Integer userId);

    Map<String, Object> Acsecurity(Integer userId);

    void usedPhone(Integer userId, String code);

    void updPhone(Integer userId, String phone, String code);

    void aplPhone(Integer userId, String phone, String name, String code);

    void addtradepassword(SysUserParam sysUserParam, String TradePassWord);

    void updateTradePassWord(SysUserParam sysUserParam, String phone, String code, String newTradePassWord, String newTradePassWord2);

    String setup(Integer userId);

    //个人信息
    Map personal(Integer userId);

    //修改个人信息
    void updateUserInfo(Integer userId, String avatar, String nickname, String autograph, String email, String phone, String invitationCode);

    List message();

    Map<String, Object> messagedetails(Integer id);

    Map Myassets(Integer userId);

    Map realname(int userid);

    //提现
    void withdrawal(int userId, BigDecimal count, String pass);

    List withdrawalrecode(int userId);

    List getBalanceRecords(int userId);

    List collectionRecords(int userId);

    void addRealName(RealNameParam RealNameParam, Integer userId, boolean defaultSucc);

    List myblindboxs(Integer id);

    Map openboxdetails(Integer id);

    Map openbox(int userid, int id, String no);

    List openBoxRecord(int userid);

    List SyntheticCollection(int userid);

    Map synthesis(int userid, int id);

    Map synthesisprize(int userid, int id, String no);

    List synthesisrecord(int userid);

    List myorder(int userid, int type);

    List scorder(int userid, int type);

    void checkTradeStatus();

    void checkqian();

    void cancelorder(int userid, int id);

    void cancelscorder(int userid, int id);

    Map<String, Object> getInviteInfo(SysUserParam param);

    String inviteInfoimgs(Integer param, int id);

    String chat();

    List tixian(int userId);

    String Recharge(int userId, BigDecimal count, int type) throws AlipayApiException;

    List Ranking(int rankType);

    List address(Integer userId);


    void newaddress(Integer userId, String name, String phone, String address, String addressdetals, int state);

    void defaultaddress(Integer userId, int id);

    void deladdress(Integer userId, int id);

    void editaddress(Integer userId, String name, String phone, String address, String addressdetals, int state, int id);

    void confirmorder(Integer userId, int id);

    Map goodsmyorder(int logUserPid);

    Map orderxq(int orderid);

    Map<String, Object> myTeam(SysUserParam param);

    List sharelist();

    void cancellation(int userId);

    void checkOverDueOrders();

    void addPointsByHoldingDays();

    /**
     * 根据藏品处理持有积分添加
     * @param collection
     * @param beginDate
     */
    void handlerAddPointsByCollection(Collection collection, Date beginDate);

}
