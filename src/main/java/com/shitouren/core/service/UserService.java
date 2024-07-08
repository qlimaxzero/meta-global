package com.shitouren.core.service;


import com.shitouren.core.autogenerate.bean.Users;
import com.shitouren.core.bean.param.SysUserParam;
import com.shitouren.core.bean.param.user.NewUserLoginParam;
import com.shitouren.core.bean.param.user.UserLoginParam;
import com.shitouren.core.bean.param.user.UserRegisterParam;
import com.shitouren.core.bean.param.user.UserRestPwdParam;
import com.shitouren.core.model.vo.InviteeVO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


public interface UserService {

    /**
     * 账户名和密码登录
     *
     * @param param
     * @return
     */
    Users userLogin(UserLoginParam param);

    /**
     * 切换账号
     *
     * @return
     */
    Users getUserById(int UserId);

    /**
     * 用户注册
     *
     * @param param
     */
    void userRegister(UserRegisterParam param);

    /**
     * 忘记密码
     *
     * @param param
     */
    void userRestPwd(UserRestPwdParam param);

    void updateAvatar(Integer userId, String avatar);

    void updateTradePassWord(SysUserParam sysUserParam, String userAccount, String phone, String code, String newTradePassWord, String newTradePassWord2);

    void updateNickname(Integer userId, String nickname);

    /**
     * 返还积分
     */
    void returnPoints(Integer userId);

    void createimg();

    Users codeuserLogin(NewUserLoginParam param);

    /**
     * 根据账户名(手机号&邮箱)获取用户
     */
    Users getUser(String username);

    int updateUserByCAS(Users users, BigDecimal oldMoney, BigDecimal oldBalance);

    List<Map<String, Object>> selectColumnsByUidList(String columns, List<Integer> uidList);

    int updateHealthByCAS(Integer userid, BigDecimal newHealth, BigDecimal oldHealth);

    /**
     * 根据uidList获取存在于existsUidList中的被邀请用户id
     */
    List<Integer> selectInviteeByUidList(List<Integer> uidList, List<Integer> existsUidList);

    /**
     * 根据uidList获取存在于existsUidList中的用户id
     */
    List<Integer> selectInviteeByUidList(List<Integer> uidList);

    List<Integer> filterValidUid(List<Integer> miningUidList);

    InviteeVO getInviteeByUid(Integer uid);

    void updateValidById(Integer uid, Byte userValid);

}
