package com.shitouren.core.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdcardUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alipay.api.AlipayApiException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.shitouren.core.common.Constants;
import com.shitouren.core.autogenerate.bean.Collection;
import com.shitouren.core.autogenerate.bean.*;
import com.shitouren.core.autogenerate.dao.*;
import com.shitouren.core.bean.eums.*;
import com.shitouren.core.bean.param.RealNameParam;
import com.shitouren.core.bean.param.SysUserParam;
import com.shitouren.core.config.TransactionProcessor;
import com.shitouren.core.config.pay.PayProperties;
import com.shitouren.core.config.exception.CloudException;
import com.shitouren.core.config.exception.ExceptionConstant;
import com.shitouren.core.config.redis.CloudRedisTemplate;
import com.shitouren.core.constant.BusinessConstant;
import com.shitouren.core.model.valueobject.FragmentVO;
import com.shitouren.core.model.valueobject.WrappedFragmentVO;
import com.shitouren.core.mp.mapper.FaceRecordMapper;
import com.shitouren.core.mp.mapper.UserGrantMapper;
import com.shitouren.core.service.processor.BalanceRecordProcessor;
import com.shitouren.core.service.processor.TaskProcessor;
import com.shitouren.core.utils.*;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import net.sf.json.JSONArray;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.http.entity.ContentType;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.jetbrains.annotations.NotNull;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Log4j2
@Service
@DS("master")
public class MineServiceImpl implements MineService {

    @Autowired(required = false)
    UsersDao usersDao;
    @Autowired(required = false)
    MessageDao messageDao;
    @Autowired(required = false)
    WithdrawalDao withdrawalDao;
    @Autowired(required = false)
    CollectionDao collectionDao;
    @Autowired(required = false)
    BlindboxDao blindboxDao;
    @Autowired(required = false)
    UserGrantDao userGrantDao;
    @Autowired(required = false)
    UserGrantMapper userGrantMapper;
    @Autowired(required = false)
    IssueDao issueDao;
    @Autowired(required = false)
    MyboxDao myboxDao;
    @Autowired(required = false)
    FragmentDao fragmentDao;
    @Autowired(required = false)
    BalanceRecordDao balanceRecordDao;
    @Autowired
    BalanceRecordProcessor balanceRecordProcessor;
    @Autowired(required = false)
    HideRecordDao hideRecordDao;
    @Autowired(required = false)
    CancelRecordDao cancelRecordDao;
    @Autowired(required = false)
    ChatDao chatDao;
    @Autowired(required = false)
    MyOrderDao myOrderDao;
    @Autowired(required = false)
    SynthesisDao synthesisDao;
    @Autowired(required = false)
    SynthesisRecordDao synthesisRecordDao;
    @Autowired
    DictService dictService;
    @Autowired
    HomeService homeService;
    @Autowired
    private CloudRedisTemplate cloudRedisTemplate;
    @Autowired(required = false)
    SignupDao signupDao;
    @Autowired(required = false)
    RechargeDao rechargeDao;
    @Autowired(required = false)
    RechargeRecordDao rechargeRecordDao;
    @Autowired(required = false)
    OpenboxrecordDao openboxrecordDao;
    @Autowired(required = false)
    AddressDao addressDao;
    @Autowired(required = false)
    OrdersDao ordersDao;
    @Autowired(required = false)
    CommodityDao commodityDao;
    @Autowired(required = false)
    CreaterDao createrDao;
    @Autowired(required = false)
    TaskDao taskDao;
    @Autowired(required = false)
    MoneyrecordDao moneyrecordDao;
    @Autowired(required = false)
    PullnewDao pullnewDao;
    @Autowired(required = false)
    InvitelistDao invitelistDao;
    @Autowired(required = false)
    YuanyuzhouDao yuanyuzhouDao;
    @Autowired
    OssService            ossService;
    @Resource
    private TransactionProcessor transactionProcessor;
    @Resource
    private FaceRecordMapper faceRecordMapper;
    @Resource
    private TaskProcessor taskProcessor;
    @Resource
    private UserService userService;
    @Resource
    private Environment environment;
    @Resource
    private PayProperties payChannelConfig;
    @Resource
    private UserGrantService userGrantService;
    @Resource
    private MiningService miningService;
    //邀请用户添加积分
    private static final String INVITE_USER_ADD_SCORE = "invite:user:";
    /**
     * 我的信息
     *
     * @param userId
     * @return
     */
    // //@Cacheable(value = "getMineInfo", key = "#userId")
    public Map<String, Object> getMineInfo(Integer userId) {
        Map<String, Object> map = new HashMap<>();
        Users user = usersDao.selectByPrimaryKey(userId);
        map.put("uid", user.getUserId());
        map.put("avatar", user.getHeadPrtraits());//头像
        map.put("nickname", user.getNickName());//昵称
        map.put("mail", mailMask(user.getMailbox()));
        map.put("address", user.getAddress());
        map.put("invitationCode", user.getInvitationCode());
        map.put("hasInviter", user.getInvitationId() == null ? 0 : 1);
        map.put("isInit", userGrantService.countInitNFT(userId) == 0 ? 0 : 1);
        map.put("isMining", miningService.countCurrentMiningRecord(userId) == 0 ? 0 : 1);
        return map;
    }

    private String mailMask(String mailbox) {
        String[] arr = mailbox.split("@");
        return arr[0].substring(0, 2) + "***@" + arr[1];
    }

    @DS("slave")
    @Override
    public Map<String, Object> Acsecurity(Integer userId) {
        Map<String, Object> map = new HashMap<>();
        Users user = usersDao.selectByPrimaryKey(userId);
        map.put("name", replaceNameX(user.getRealname()));
        map.put("no", idEncrypt(user.getRealno()));
        if (StringUtil.getLength(user.getAlipay()) > 0) {
            map.put("alipay", phoneMask(user.getAlipay()));//头像
        } else {
            map.put("alipay", "");//头像
        }
        if (user.getRealnametype() == 0) {
            map.put("isRealName", 0);//昵称
        } else {
            map.put("isRealName", 1);//昵称
        }
        return map;
    }

    @DS("slave")
    @Override
    public void usedPhone(Integer userId, String code) {
        Users user = usersDao.selectByPrimaryKey(userId);
        //验证码校验
        String loginCodekey = EumUser.CellVerifyCodeType.校验手机号.getValue() + user.getPhoneNumber();
        String str = cloudRedisTemplate.get(loginCodekey);
        if (!StringUtil.isValidStr(str) || !code.equals(str)) {
            throw new CloudException(ExceptionConstant.手机验证码错误);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updPhone(Integer userId, String phone, String code) {
        //验证码校验
        String loginCodekey = EumUser.CellVerifyCodeType.校验手机号.getValue() + phone;
        String str = cloudRedisTemplate.get(loginCodekey);
        if (!StringUtil.isValidStr(str) || !code.equals(str)) {
            throw new CloudException(ExceptionConstant.手机验证码错误);
        }
        Users users = new Users();
        users.setUserId(userId);
        users.setPhoneNumber(phone);
        usersDao.updateByPrimaryKeySelective(users);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void aplPhone(Integer userId, String phone, String name, String code) {
        Users users = usersDao.selectByPrimaryKey(userId);        //验证码校验
        String loginCodekey = EumUser.CellVerifyCodeType.支付宝验证.getValue() + users.getPhoneNumber();
        String str = cloudRedisTemplate.get(loginCodekey);
        if (!StringUtil.isValidStr(str) || !code.equals(str)) {
            throw new CloudException(ExceptionConstant.手机验证码错误);
        }
        users.setAlipay(phone);
        users.setAlipayname(name);
        usersDao.updateByPrimaryKeySelective(users);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addtradepassword(SysUserParam sysUserParam, String TradePassWord) {
        Users user = usersDao.selectByPrimaryKey(sysUserParam.getLogUserPid());
        user.setTradePassWord(MD5Util.MD5Encode(TradePassWord));
        usersDao.updateByPrimaryKeySelective(user);
    }

    /**
     * 修改交易密码
     *
     * @param sysUserParam
     * @param phone
     * @param code
     * @param newTradePassWord
     * @param newTradePassWord2
     */
    @Transactional(rollbackFor = Exception.class) public void updateTradePassWord(SysUserParam sysUserParam, String phone, String code, String newTradePassWord,
                                     String newTradePassWord2) {
        Users user = usersDao.selectByPrimaryKey(sysUserParam.getLogUserPid());
        UsersExample query = new UsersExample();
        query.createCriteria().andMailboxEqualTo(phone);
        List<Users> userList = usersDao.selectByExample(query);
        if (!ListUtil.isValidateList(userList)) {
            throw new CloudException(ExceptionConstant.账户名不存在);
        }
        if (!newTradePassWord.equals(newTradePassWord2)) {
            throw new CloudException(ExceptionConstant.两次密码不一致);
        }
        if (!phone.equals(user.getMailbox())) {
            throw new CloudException(ExceptionConstant.手机号与账号绑定手机号不一致);
        }
        //验证码校验
        String loginCodekey = EumUser.CellVerifyCodeType.交易密码.getValue() + phone;
        String str = cloudRedisTemplate.get(loginCodekey);
        if (!StringUtil.isValidStr(str) || !code.equals(str)) {
            throw new CloudException(ExceptionConstant.手机验证码错误);
        }
        user.setTradePassWord(MD5Util.MD5Encode(newTradePassWord));
        usersDao.updateByPrimaryKeySelective(user);
    }

    @DS("slave")
    @Override
    public String setup(Integer userId) {
        String a = "0";
        Users user = usersDao.selectByPrimaryKey(userId);
        if (!StringUtil.isEmpty(user.getTradePassWord())) {
            a = "1";
        }
        return a;
    }

    /**
     * 用户电话号码的打码隐藏加星号加*
     *
     * @return 处理完成的身份证
     */
    public static String phoneMask(String phone) {
        String res = phone;
        if (!StringUtil.isEmpty(phone)) {
            if (StringUtil.getLength(phone) > 10) {
                StringBuilder stringBuilder = new StringBuilder(phone);
                res = stringBuilder.replace(3, 7, "****").toString();
            }
        }
        return res;
    }

    @DS("slave")
    @Override
    public Map personal(Integer userId) {
        Map<String, Object> map = new HashMap<>();
        Users user = usersDao.selectByPrimaryKey(userId);
        map.put("avatar", ImgEnum.img.getUrl() + user.getHeadPrtraits());//头像
        map.put("nickname", user.getNickName());//昵称
        map.put("autograph", user.getAutograph());//个性签名
        map.put("phone", phoneMask(user.getPhoneNumber()));//电话
        map.put("phone1", user.getPhoneNumber());//电话
        map.put("address", user.getAddress());//电话
        map.put("email", user.getMailbox());//邮箱
        if (user.getRealnametype() == 0) {
            map.put("isRealName", 0);//昵称
        } else {
            map.put("isRealName", 1);//昵称
        }
        return map;
    }

    @Override
    @Transactional(rollbackFor = Exception.class) public void updateUserInfo(Integer userId, String avatar, String nickname, String autograph, String email,
                                String phone, String invitationCode) {
        Users user = usersDao.selectByPrimaryKey(userId);

        if (!StringUtil.isEmpty(phone)) {
            UsersExample query = new UsersExample();
            query.createCriteria().andPhoneNumberEqualTo(phone);
            // List<Users> userList = usersDao.selectByExample(query);
            if (usersDao.countByExample(query) != 0) {
                throw new CloudException(ExceptionConstant.账户名已存在);
            }
        }
        if (!StringUtil.isEmpty(email)) {
            UsersExample query = new UsersExample();
            query.createCriteria().andMailboxEqualTo(email);
            // List<Users> userList = usersDao.selectByExample(query);
            if (usersDao.countByExample(query) != 0) {
                throw new CloudException(ExceptionConstant.账户名已存在);
            }
        }

        if (!StringUtil.isEmpty(avatar)) {
            user.setHeadPrtraits(avatar);
        }
        if (!StringUtil.isEmpty(nickname)) {
            user.setNickName(nickname);
        }
        if (!StringUtil.isEmpty(autograph)) {
            user.setAutograph(autograph);
        }
        if (!StringUtil.isEmpty(email)) {
            user.setMailbox(email);
        }
        if (!StringUtil.isEmpty(phone)) {
            user.setPhoneNumber(phone);
        }
        if (!StringUtil.isEmpty(invitationCode)) {
            AssertUtil.isFalse(Objects.equals(user.getInvitationCode(), invitationCode.trim()),
                    ExceptionConstant.邀请码是自己的);
            AssertUtil.isNull(user.getInvitationId(), ExceptionConstant.已存在邀请人);

            UsersExample example = new UsersExample();
            example.createCriteria().andInvitationCodeEqualTo(invitationCode.trim());
            List<Users> list = usersDao.selectByExample(example);
            AssertUtil.isFalse(list.isEmpty(), ExceptionConstant.邀请码不存在);

            Integer inviterId = list.get(0).getUserId();
            user.setInvitationId(inviterId);
            usersDao.addInvitationRewardsForOneTime(BigDecimal.ZERO, inviterId);
        }
        usersDao.updateByPrimaryKeySelective(user);
    }

    @DS("slave")
    @Override
    public List message() {
        List list = new ArrayList();
        MessageExample messageExample = new MessageExample();
        messageExample.setOrderByClause("createtime desc");
        List<Message> messages = messageDao.selectByExample(messageExample);
        for (Message message : messages) {
            Map map = new HashMap();
            map.put("id", message.getId());
            map.put("title", message.getTitle());
            map.put("createtime", DateUtils.getDateToStr(message.getCreatetime(), DateUtils.TIME_FORMAT1));
            map.put("introduce", message.getIntroduce());
            list.add(map);
        }
        return list;
    }

    @DS("slave")
    @Override
    public Map<String, Object> messagedetails(Integer id) {
        Message message = messageDao.selectByPrimaryKey(id);
        Map map = new HashMap();
        map.put("id", message.getId());
        map.put("title", message.getTitle());
        map.put("createtime", DateUtils.getDateToStr(message.getCreatetime(), DateUtils.TIME_FORMAT1));
        map.put("notice", message.getNotice());
        return map;
    }

    @DS("slave")
    @Override
    public Map Myassets(Integer userId) {
        Map map = new HashMap();
        Users user = usersDao.selectByPrimaryKey(userId);
        map.put("balance", user.getBalance());
        map.put("money", user.getMoney());
        if (StringUtil.getLength(user.getAlipay()) > 0) {
            map.put("alipay", phoneMask(user.getAlipay()));//头像
        } else {
            map.put("alipay", "");//头像
        }
        return map;

    }

    @DS("slave")
    @Override
    public Map realname(int userid) {
        Users user = usersDao.selectByPrimaryKey(userid);
        Map map = new HashMap();
        map.put("name", replaceNameX(user.getRealname()));
        map.put("no", idEncrypt(user.getRealno()));
        if (StringUtil.getLength(user.getAlipay()) > 0) {
            map.put("alipay", phoneMask(user.getAlipay()));//头像
        } else {
            map.put("alipay", "");//头像
        }
        if (user.getRealnametype() == 0) {
            map.put("isRealName", 0);//昵称
        } else {
            map.put("isRealName", 1);//昵称
        }
        return map;
    }

    //身份证前三后四脱敏
    public static String idEncrypt(String idcard) {
        if (StringUtil.isEmpty(idcard) || (idcard.length() < 8)) {
            return idcard;
        }
        return idcard.replaceAll("(?<=\\w{1})\\w(?=\\w{1})", "*");
    }

    public static String replaceNameX(String str) {
        // 获取姓名长度
        String custName = str;
        int length = custName.length();
        String reg = ".{1}";
        StringBuffer sb = new StringBuffer();
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(str);
        int i = 0;
        while (m.find()) {
            i++;
            if (i == length)
                continue;
            m.appendReplacement(sb, "*");
        }
        m.appendTail(sb);
        return sb.toString();
    }

    @Transactional(rollbackFor = Exception.class)
    @DS("master")
    @Override
    public void withdrawal(int userId, BigDecimal count, String pass) {

        final String lockKey = "withdrawal#" + userId;
        final String lockValue = UUID.randomUUID().toString();
        final boolean isLocked = cloudRedisTemplate.getLock(lockKey, lockValue, 3);
        if (!isLocked) {
            throw new CloudException(ExceptionConstant.频率太高请稍等);
        }
        Users user = usersDao.getUserById(userId);
        if (count.compareTo(new BigDecimal(0)) < 1) {
            throw new CloudException(ExceptionConstant.数量有误);
        }
        if (count.compareTo(new BigDecimal(30)) < 0) {
            throw new CloudException(ExceptionConstant.满30提现);
        }
        if (user.getBalance().compareTo(count) < 0) {
            throw new CloudException(ExceptionConstant.资产不足);
        }
        if (StringUtil.getLength(user.getTradePassWord()) == 0) {
            throw new CloudException(ExceptionConstant.请先设置操作密码);
        }
        if (!user.getTradePassWord().equals(MD5Util.MD5Encode(pass))) {
            throw new CloudException(ExceptionConstant.操作密码错误);
        }
        if (StringUtil.isEmpty(user.getAlipay()) || StringUtil.isEmpty(user.getAlipayname())) {
            throw new CloudException(ExceptionConstant.请先设置支付宝);
        }
        BigDecimal balance = user.getBalance().subtract(count);
        if (balance.compareTo(new BigDecimal(0)) < 0) {
            balance = new BigDecimal(0);
        }
        user.setBalance(balance);
        // usersDao.updateByPrimaryKeySelective(user);
        balanceRecordProcessor.add(userId, count.negate(), BalanceTypeEnum.CASHOUT, null, balance);
        //余额扣除记录
        Withdrawal withdrawal = new Withdrawal();
        withdrawal.setUserid(userId);
        withdrawal.setCount(count);
        withdrawal.setCreatetime(new Date());
        withdrawal.setAlipay(user.getAlipay());
        withdrawal.setAlipayname(user.getAlipayname());
        // withdrawalDao.insertSelective(withdrawal);
        usersDao.updateByPrimaryKeySelective(user);
        withdrawalDao.insertSelective(withdrawal);
        final boolean isUnLocked = cloudRedisTemplate.releaseLock(lockKey, lockValue);
        if (!isUnLocked) {
            throw new CloudException(ExceptionConstant.频率太高请稍等);
        }

    }

    @DS("slave")
    @Override
    public List withdrawalrecode(int userId) {

        WithdrawalExample withdrawalExample = new WithdrawalExample();
        withdrawalExample.createCriteria().andUseridEqualTo(userId);
        withdrawalExample.setOrderByClause("createtime desc");
        List<Withdrawal> withdrawals = withdrawalDao.selectByExample(withdrawalExample);

        return withdrawals.stream()
                          .map(withdrawal -> {
                              Map<String, Object> map = new HashMap<>();
                              map.put("name", "提现");
                              map.put("state", withdrawal.getState());
                              map.put("count", withdrawal.getCount());
                              map.put("time", DateUtils.getDateToStr(withdrawal.getCreatetime(),
                                                                     DateUtils.DATETIME_FORMAT));
                              return map;
                          })
                          .collect(Collectors.toList());
    }

    @DS("slave")
    @Override
    public List<Map<String,Object>> getBalanceRecords(int userId) {
        BalanceRecordExample withdrawalExample = new BalanceRecordExample();
        withdrawalExample.createCriteria().andUseridEqualTo(userId);
        withdrawalExample.setOrderByClause("id desc");
        List<BalanceRecord> withdrawals = balanceRecordDao.selectByExample(withdrawalExample);
        return withdrawals.stream()
                          .map(withdrawal -> {
                              Map<String, Object> map = new HashMap<>();
                              map.put("name", withdrawal.getName());
                              map.put("count", withdrawal.getCount().toString());
                              map.put("time", DateUtils.getDateToStr(withdrawal.getCreatetime(),
                                                                     DateUtils.DATETIME_FORMAT));
                              return map;
                          })
                          .collect(Collectors.toList());

    }

    @DS("slave")
    @Override
    public List<Map<String,Object>> collectionRecords(int userId) {
        HideRecordExample hideRecordExample = new HideRecordExample();
        hideRecordExample.createCriteria().andUseridEqualTo(userId);
        hideRecordExample.setOrderByClause("createtime desc");
        List<HideRecord> withdrawals = hideRecordDao.selectByExample(hideRecordExample);

        Map<Integer, Users> uid2UserMap = getUserId2UserMap(withdrawals);
        return withdrawals.stream()
                          .map(hideRecord -> {
                              Map<String, Object> map = new HashMap<>();
                              map.put("name", hideRecord.getName());
                              map.put("img", ImgEnum.img.getUrl() + hideRecord.getImg());
                              map.put("no", hideRecord.getNo());
                              map.put("type", hideRecord.getType());
                              map.put("ms", hideRecord.getMs());
                              map.put("time", DateUtils.getDateToStr(hideRecord.getCreateTime(),
                                                                     DateUtils.DATETIME_FORMAT));
                              Users users = uid2UserMap.get(hideRecord.getGetuid());
                              if (users != null) {
                                  map.put("username", users.getNickName());
                              }
                              switch (hideRecord.getType()) {//0.黄的1.绿2.红
                                  case 0:
                                      map.put("describe", DateUtils.getDateToStr(hideRecord.getCreateTime(),
                                       DateUtils.DATETIME_FORMAT));
                                      break;
                                  case 1:
                                      map.put("describe", "￥" + hideRecord.getPrice());
                                      break;
                                  case 2:
                                      map.put("describe", "￥" + hideRecord.getPrice());
                                      break;
                              }
                              return map;
                          })
                          .collect(Collectors.toList());

    }
    @DS("slave")
    @NotNull
    private Map<Integer, Users> getUserId2UserMap(List<HideRecord> withdrawals) {
        if (CollectionUtils.isEmpty(withdrawals)) {
            return Collections.emptyMap();
        }
        List<Integer> getUIds = withdrawals.stream().map(HideRecord::getGetuid).collect(Collectors.toList());
        UsersExample ex = new UsersExample();
        ex.createCriteria().andUserIdIn(getUIds);
        List<Users> u = usersDao.selectByExample(ex);
        Map<Integer, Users> uid2UserMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(u)) {
            Map<Integer, Users> m = u.stream().collect(Collectors.toMap(Users::getUserId, Function.identity(), (k1, k2) -> k1));
            uid2UserMap.putAll(m);
        }
        return uid2UserMap;
    }


    @Transactional(rollbackFor = Exception.class)
    public void addRealName(RealNameParam realNameParam, Integer userId, boolean defaultSucc) {
        if (!IdcardUtil.isValidCard(realNameParam.getIdCardNum())) {
            throw new CloudException(ExceptionConstant.您的身份证有误);
        }

        int age = IdcardUtil.getAgeByIdCard(realNameParam.getIdCardNum());
        if (age < 18) {
            throw new CloudException(ExceptionConstant.您的年龄不符合要求);
        }

        //check user's status
        Users user = usersDao.selectByPrimaryKey(userId);//当前用户信息
        if (user.getRealnametype() == 2) {
            throw new CloudException(ExceptionConstant.你已实名);
        }

        checkIfIdExisted(realNameParam);


        //fetch realname service api
        val isSuccess = true;

        if (isSuccess) {
            val registerPrice = getPriceByActionType("register");
            //1. 更新用户实名信息,有并发问题,需要加乐观锁
            saveUserRealName(realNameParam, user, registerPrice);
            //2. 保存奖励任务,无并发问题
            taskProcessor.addTask(userId, registerPrice, PointsTypeEnum.REGISTER, user.getMoney());
            //3.保存资金流水,无并发问题
            saveMoneyRecord(user, registerPrice, "注册实名");
            /*
             *  更新邀请人奖励
             */
            Users inviter = usersDao.selectByPrimaryKey(user.getInvitationId());
            if (inviter != null) {
                //每日邀请好友奖励元气丸上限
                int maxInvitePerDay = Integer.parseInt(dictService.getValue("maxInvitePerDay"));
                //该日通过邀请已获取的积分数量
                Integer existScore = cloudRedisTemplate.get(INVITE_USER_ADD_SCORE + inviter.getUserId());
                BigDecimal inviteFriendPrice = getPriceByActionType("inviteFriend");
                //邀请人id
                final Integer inviterUserId = inviter.getUserId();
                if (existScore == null) {
                    //给邀请人添加一条奖励任务
                    taskProcessor.addTask(inviterUserId, inviteFriendPrice, PointsTypeEnum.INVITE, inviter.getMoney().add(inviteFriendPrice));
                    //给邀请人添加资金流水
                    saveMoneyRecord(inviter, inviteFriendPrice, "邀请好友");
                    //给邀请人加奖励
                    incInviterMoneyRewardForOneTime(inviter, inviteFriendPrice);
                    //redis设置邀请人当日已获取积分数量
                    //当前时间换算成秒
                    Calendar calendar = Calendar.getInstance();
                    long currentTime = calendar.get(Calendar.HOUR_OF_DAY) * 3600 + calendar.get(Calendar.MINUTE) * 60 + calendar.get(Calendar.SECOND);
                    cloudRedisTemplate.set(INVITE_USER_ADD_SCORE + inviter.getUserId(), inviteFriendPrice.intValue(), 24*60*60 - currentTime);
                }else if (existScore < maxInvitePerDay && existScore + inviteFriendPrice.intValue() <= maxInvitePerDay) {
                    //给邀请人添加一条奖励任务
                    taskProcessor.addTask(inviterUserId, inviteFriendPrice, PointsTypeEnum.INVITE, inviter.getMoney().add(inviteFriendPrice));
                    //给邀请人添加资金流水
                    saveMoneyRecord(inviter, inviteFriendPrice, "邀请好友");
                    //给邀请人加奖励
                    incInviterMoneyRewardForOneTime(inviter, inviteFriendPrice);
                    //redis增加邀请人当日已获取积分数量
                    cloudRedisTemplate.incr(INVITE_USER_ADD_SCORE + inviter.getUserId(), inviteFriendPrice.intValue());
                }else {//邀请人当日已获取积分数量达到上限
                    //给邀请人添加一条奖励任务
                    taskProcessor.addTask(inviterUserId, BigDecimal.ZERO, PointsTypeEnum.INVITE, inviter.getMoney());
                    //给邀请人添加资金流水
                    saveMoneyRecord(inviter, new BigDecimal("0"), "邀请好友");
                    log.info("邀请人当日已获取积分数量超过上限, inviteeId {} inviterId {}", userId, inviterUserId);

                }

                //拉新活动
                Pullnew                 pullnew    = pullnewDao.selectByPrimaryKey(1);
                final Optional<Pullnew> pullNewOpt = Optional.ofNullable(pullnew);

                pullNewOpt.ifPresent(pullNew -> {
                    if (canTakeInPullNew(pullNew, user)) { //是否可能参与拉新活动
                        //查询是否保存
                        InvitelistExample invitelistExample = new InvitelistExample();
                        invitelistExample.createCriteria()
                                         .andUidEqualTo(inviterUserId);
                        List<Invitelist> inviteLists = invitelistDao.selectByExample(invitelistExample);
                        if (CollectionUtils.isNotEmpty(inviteLists)) {
                            Invitelist invitelist = inviteLists.get(0);
                            int        nownum     = invitelist.getInvitennum() + 1;
                            invitelist.setInvitennum(nownum);
                            invitelistDao.updateByPrimaryKeySelective(invitelist);

                        } else {
                            Invitelist invitelist = new Invitelist();
                            invitelist.setUid(inviterUserId);
                            invitelist.setInvitennum(1);
                            invitelistDao.insertSelective(invitelist);
                        }

                    }

                    if (pullNew.getOpenactivity() == 1) {
                        //自己的注册实名奖励
                        if (pullnew.getNewsend() == 1) {
                            if (pullnew.getIsrealname() == 1) {
                                //不限量
                                if (pullnew.getNumber() > 0) {
                                    String orderno =
                                            System.currentTimeMillis() + ((int) (Math.random() * 1000)) + "";
                                    if (pullnew.getIsendblind() == 1) {
                                        //赠送盲盒

                                        Mybox mybox = new Mybox();
                                        mybox.setUserid(user.getUserId());
                                        mybox.setNo(orderno);
                                        mybox.setBoxid(pullnew.getNewblindid());
                                        //mybox.setOrderid(myOrder.getId());
                                        myboxDao.insertSelective(mybox);
                                        int nownum = pullnew.getNumber() - 1;
                                        pullnew.setNumber(nownum);
                                        pullnewDao.updateByPrimaryKeySelective(pullnew);
                                    }
                                }
                                // Pullnew pullnew1 = pullnewDao.selectByPrimaryKey(pullnew.getId());
                                if (pullNew.getNumber() > 0) {
                                    String orderno =
                                            System.currentTimeMillis() + ((int) (Math.random() * 1000)) + "";
                                    if (pullNew.getIsendcol() == 1) {
                                        //赠送盲盒
                                        Collection collection =
                                                collectionDao.selectByPrimaryKey(pullNew.getNewcollectionid());
                                        if (collection != null) {
                                            UserGrant userGrant = new UserGrant();
                                            userGrant.setUserid(user.getUserId());
                                            userGrant.setCollid(pullNew.getNewcollectionid());
                                            userGrant.setTruenumber(orderno);
                                            userGrant.setTradeTime(new Date());
                                            userGrant.setCreatetime(new Date());
                                            userGrant.setBuyprice(collection.getPrice());
                                            userGrant.setBuytime(new Date());
                                            userGrant.setType(0);
                                            userGrant.setPaytype(0);
                                            userGrantDao.insertSelective(userGrant);
                                            int nownum = pullNew.getNumber() - 1;
                                            pullNew.setNumber(nownum);
                                            pullnewDao.updateByPrimaryKeySelective(pullNew);
                                        }

                                    }
                                }


                            }
                        }
                    }

                });

                int          topuid        = user.getInvitationId();
                UsersExample usersExample1 = new UsersExample();
                usersExample1.createCriteria()
                             .andInvitationIdEqualTo(topuid)
                             .andRealnametypeEqualTo(2);
                List<Users> usersList   = usersDao.selectByExample(usersExample1);
                int         downuidnum  = usersList.size();
                Pullnew     pullnew1111 = pullnewDao.selectByPrimaryKey(1);
                Date        nowday111   = new Date();
                if (pullnew1111.getOpenactivity() == 1) {
                    if (nowday111.compareTo(pullnew1111.getOpenactivitycreattime()) == 1 && nowday111.compareTo(pullnew1111.getOpenactivityendtime()) == -1) {
                        if (pullnew1111.getRealnameblind() == 1) {
                            if (pullnew1111.getOpenblind() == 1) {
                                //以逗号划分字符串
                                String   s  = pullnew1111.getLadderinfo();
                                String[] as = s.split(",");
                                for (String item : as) {
                                    int nowas = Integer.parseInt(item);
                                    if (nowas == downuidnum) {
                                        //奖励盲盒
                                        String orderno =
                                                System.currentTimeMillis() + ((int) (Math.random() * 1000)) + "";
                                        Mybox mybox = new Mybox();
                                        mybox.setUserid(topuid);
                                        mybox.setNo(orderno);
                                        mybox.setBoxid(pullnew1111.getBlindboxid());
                                        //mybox.setOrderid(myOrder.getId());
                                        myboxDao.insertSelective(mybox);

                                    }

                                }

                            }
                        }
                        //藏品
                        if (pullnew1111.getRealnamecollection() == 1) {
                            if (pullnew1111.getOpencollection() == 1) {
                                String   s  = pullnew1111.getLadderinfo();
                                String[] as = s.split(",");
                                for (String item : as) {
                                    int nowas = Integer.parseInt(item);
                                    if (nowas == downuidnum) {
                                        String orderno =
                                                System.currentTimeMillis() + ((int) (Math.random() * 1000)) + "";
                                        Collection collection =
                                                collectionDao.selectByPrimaryKey(pullnew1111.getCollectionid());
                                        //奖励盲盒
                                        UserGrant userGrant = new UserGrant();
                                        userGrant.setUserid(topuid);
                                        userGrant.setCollid(pullnew1111.getCollectionid());
                                        userGrant.setTruenumber(orderno);
                                        userGrant.setTradeTime(new Date());
                                        userGrant.setCreatetime(new Date());
                                        userGrant.setBuyprice(collection.getPrice());
                                        userGrant.setBuytime(new Date());
                                        userGrant.setType(0);
                                        userGrant.setPaytype(0);
                                        userGrantDao.insertSelective(userGrant);

                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            log.error("realname failed, params is {} userId {}", JSON.toJSONString(realNameParam), userId);
            throw new CloudException(ExceptionConstant.实名未通过);
        }
    }

    private boolean canTakeInPullNew(Pullnew pullNew,
                                     Users user) {
        if (pullNew.getOpenactivity() == 1) {
            Date    currentDay  = new Date();
            //活动是否进行中
            val pullNewActivityIsInProgress = DateUtil.isIn(currentDay,
                                                            pullNew.getOpenactivitycreattime(),
                                                            pullNew.getOpenactivityendtime());
            //用户注册时间是否合法
            val userCreatedTimeIsLegal = DateUtil.isIn(user.getCreateTime(),
                                                       pullNew.getOpenactivitycreattime(),
                                                       pullNew.getOpenactivityendtime());
            return pullNewActivityIsInProgress && userCreatedTimeIsLegal;

            }
        return false;
    }

    /**
     * to increase inviter's rewards for once
     * @param inviter
     * @param inviteFriendPrice
     */
    private void incInviterMoneyRewardForOneTime(Users inviter, BigDecimal inviteFriendPrice) {
        val successCnt = usersDao.addInvitationRewardsForOneTime(inviteFriendPrice, inviter.getUserId());
        if (successCnt == 0) {
            log.info("increase invitation rewards fail, inviter is: {}", JSON.toJSONString(inviter));
        } else {
            log.info("increase invitation rewards success, inviter is: {}", JSON.toJSONString(inviter));
        }

    }

    @NotNull
    private BigDecimal getPriceByActionType(String inviteFriendPrice) {
        return new BigDecimal(dictService.getValue(inviteFriendPrice));
    }



     private void saveMoneyRecord(Users user, BigDecimal registerPrice, String moneyName) {
        // todo 与task意义重叠, 可删
        Moneyrecord moneyrecord = new Moneyrecord();
        moneyrecord.setCreattime(new Date());
        moneyrecord.setUserid(user.getUserId());
        moneyrecord.setName(moneyName);
        moneyrecord.setCount(registerPrice);
        moneyrecordDao.insertSelective(moneyrecord);
    }

    /**
     * 保存用户实名信息
     * @param RealNameParam
     * @param user
     * @param registerPrice
     */
     private void saveUserRealName(RealNameParam RealNameParam,
                                  Users user,
                                  BigDecimal registerPrice) {
        user.setRealnametype(2);
        user.setRealname(RealNameParam.getName());
        user.setRealno(RealNameParam.getIdCardNum());
        final BigDecimal preMoney = user.getMoney();
        BigDecimal money    = user.getMoney()
                                  .add(registerPrice);
        user.setMoney(money);
        UsersExample ex = new UsersExample();
        ex.createCriteria()
          .andUserIdEqualTo(user.getUserId())
          .andMoneyEqualTo(preMoney)
          .andRealnametypeNotEqualTo(2)
          .andRealnameIsNull()
          .andRealnoIsNull();
        val count = usersDao.updateByExampleSelective(user, ex);
        if (count == 0) {
            log.info("用户已经实名, 不能重复实名");
            throw new CloudException(ExceptionConstant.你已实名);
        }
    }


    private void checkIfIdExisted(RealNameParam RealNameParam) {
        String number = RealNameParam.getIdCardNum();
        UsersExample ex = new UsersExample();
        ex.createCriteria().andRealnameEqualTo(RealNameParam.getName())
          .andRealnoEqualTo(number)
          .andRealnametypeEqualTo(2);
        long count = usersDao.countByExample(ex);
        if (count > 0L) {
            throw new CloudException(ExceptionConstant.一个身份信息只可实名一个账号);
        }
    }
    @DS("slave")
    @Override
    public List myblindboxs(Integer id) {
        List list = new ArrayList();
        MyboxExample myboxExample = new MyboxExample();
        myboxExample.createCriteria().andUseridEqualTo(id).andTypeEqualTo(0);
        List<Mybox> myboxes = myboxDao.selectByExample(myboxExample);
        for (Mybox mybox : myboxes) {
            Map map = new HashMap();
            map.put("id", mybox.getId());
            map.put("type", mybox.getType());
            Blindbox blindbox = blindboxDao.selectByPrimaryKey(mybox.getBoxid());
            if (blindbox != null) {
                map.put("name", blindbox.getName());
                map.put("img", ImgEnum.img.getUrl() + blindbox.getImg());
                map.put("no", mybox.getNo());
                map.put("cimg", ImgEnum.img.getUrl() + blindbox.getCreatorimg());//创作者头像
                map.put("publisher", blindbox.getPublisher());//发行者
                list.add(map);
            }
        }
        return list;
    }
    @DS("slave")
    @Override
    public Map openboxdetails(Integer id) {
        Map map = new HashMap();
        Mybox mybox = myboxDao.selectByPrimaryKey(id);
        Blindbox blindbox = blindboxDao.selectByPrimaryKey(mybox.getBoxid());
        map.put("id", id);//id
        map.put("img", blindbox.getImg1());//图片
        map.put("name", blindbox.getName());//名称
        map.put("no", mybox.getNo());
        map.put("type", mybox.getType());
        map.put("details", blindbox.getDetails());//详情
        map.put("cimg", blindbox.getCreatorimg());//创作者头像
        map.put("publisher", blindbox.getPublisher());//发行者
        List<Integer> idl = new ArrayList();
        String[] split = blindbox.getProbably().split(",");
        for (int i = 0; i < split.length; i++) {
            idl.add(Integer.parseInt(split[i]));
        }
        List list1 = new ArrayList();

        final Map<Integer, Collection> id2CollectionMap = getId2CollectMap(idl);


        for (Integer cid : idl) {
            Map map1 = new HashMap();
            final Collection collection = id2CollectionMap.get(cid);
            map1.put("name", collection.getName());
            map1.put("img", ImgEnum.img.getUrl() + collection.getImg());
            list1.add(map1);
        }
        map.put("probably", list1);
        return map;
    }


    @Override
    @Transactional(rollbackFor = Exception.class) public Map openbox(int userid, int id, String no) {


        final String lockKey = "openbox_" + userid;
        final String lockValue = UUID.randomUUID().toString();

        boolean  isLockSuccess  = cloudRedisTemplate.getLock(lockKey, lockValue, 5);

        if (!isLockSuccess) {
            throw new CloudException(ExceptionConstant.频率太高请稍等);
        }
        log.info("openbox 加锁成功，key = {}, value = {}", lockKey, lockValue);

        Map map = new HashMap();
        // Map<Integer, Integer> map_choujiang = new HashMap<>();
        Mybox mybox = myboxDao.selectByPrimaryKey(id);

        if (mybox.getType() != 0) {
            throw new CloudException(ExceptionConstant.暂无法开启);
        }

        Blindbox blindbox = blindboxDao.selectByPrimaryKey(mybox.getBoxid());
        String[] split = blindbox.getProbably().split(",");
        List<Integer> idl = new ArrayList();
        Collection collection_choujiang;

        final List<Integer> cids = Arrays.stream(split)
                                            .map(Integer::parseInt)
                                            .distinct()
                                            .collect(Collectors.toList());
        final Map<Integer, Collection> id2CollectMap = getId2CollectMap(cids);


        for (String s : split) {
            // collection_choujiang = collectionDao.selectByPrimaryKey(Integer.parseInt(s));
            collection_choujiang = id2CollectMap.get(Integer.parseInt(s));
            if (collection_choujiang.getLimits() - collection_choujiang.getSold() > 0) {
                for (int j = 0; j < collection_choujiang.getLimits() - collection_choujiang.getSold(); j++) {
                    idl.add(Integer.parseInt(s));
                }
            }
        }
        if (idl.size() == 0) {
            throw new CloudException(ExceptionConstant.暂无法开启);
        }
        //随机两个数区间(Math.random()*(最大数-最小数+1)+最小数)

        int x = new Random().nextInt(idl.size());

        int a = idl.get(x);


        Collection collection = collectionDao.selectByPrimaryKey(a);
        collection.setSold(collection.getSold() + 1);
        collectionDao.updateByPrimaryKeySelective(collection);

        map.put("name", collection.getName());
        map.put("img", ImgEnum.img.getUrl() + collection.getImg());


        FragmentExample fragmentExample = new FragmentExample();
        fragmentExample.createCriteria().andCollidEqualTo(a);
        List<Fragment> fragments = fragmentDao.selectByExample(fragmentExample);
        int type = 1;//1 要发行
        if (fragments.size() > 0) {
            Fragment fragment = fragments.get(0);
            fragmentDao.deleteByPrimaryKey(fragment.getId());
            type = 0;//0 不发行
        }
        //判断该碎片还有没有次数
        if (type == 1) {//要发行
            if (collection.getStockc() == 0) {
                int b = 0;
                for (int i = 1; i > 100; i++) {
                    b = idl.get(x - 1);
                    if (b != a) {
                        a = b;
                        break;
                    }
                }
                collection = collectionDao.selectByPrimaryKey(b);
                map.put("name", collection.getName());
                map.put("img", ImgEnum.img.getUrl() + collection.getImg());
                fragmentExample = new FragmentExample();
                fragmentExample.createCriteria().andCollidEqualTo(b);
                List<Fragment> fragmentss = fragmentDao.selectByExample(fragmentExample);
                type = 1;//1 要发行
                if (fragmentss.size() > 0) {
                    Fragment fragment = fragmentss.get(0);
                    fragmentDao.deleteByPrimaryKey(fragment.getId());
                    type = 0;//0 不发行
                }
            }
        }

        UserGrant userGrant = new UserGrant();
        //TODO 如果是土地盲盒给土地数据；
        if (a == Constants.YUANYUZHOU_COLL_ID) {
            YuanyuzhouExample yuanyuzhouExample = new YuanyuzhouExample();
            yuanyuzhouExample.createCriteria().andUserIdEqualTo(userid);
            yuanyuzhouExample.setOrderByClause("x+y asc");
            PageHelper.startPage(1, 1);
            List<Yuanyuzhou> yuanyuzhouList = yuanyuzhouDao.selectByExample(yuanyuzhouExample);
            int iszhaodao = 0;

            Users user = usersDao.selectByPrimaryKey(userid);


            if (yuanyuzhouList.size() > 0) {
                for (int i = 1; i < 6; ++i) {
                    for (int j = 0; j <= i; ++j) {
                        yuanyuzhouExample = new YuanyuzhouExample();
                        yuanyuzhouExample.createCriteria().andUserIdEqualTo(0).andXEqualTo(yuanyuzhouList.get(0).getX() + i).andYEqualTo(yuanyuzhouList.get(0).getY() + j).andOpenEqualTo(Constants.YUANYUZHOU_OPEN);
                        PageHelper.startPage(1, 1);
                        List<Yuanyuzhou> yuanyuzhous = yuanyuzhouDao.selectByExample(yuanyuzhouExample);
                        if (yuanyuzhous.size() == 0) {
                            yuanyuzhouExample = new YuanyuzhouExample();
                            yuanyuzhouExample.createCriteria().andUserIdEqualTo(0).andYEqualTo(yuanyuzhouList.get(0).getY() + i).andXEqualTo(yuanyuzhouList.get(0).getX() + j).andOpenEqualTo(Constants.YUANYUZHOU_OPEN);
                            PageHelper.startPage(1, 1);
                            yuanyuzhous = yuanyuzhouDao.selectByExample(yuanyuzhouExample);
                            if (yuanyuzhous.size() > 0) {
                                yuanyuzhous.get(0).setUserId(user.getUserId());
                                yuanyuzhous.get(0).setNickName(user.getNickName());
                                yuanyuzhous.get(0).setIsGoumai(0);
                                yuanyuzhous.get(0).setTime(new Date());
                                yuanyuzhouDao.updateByPrimaryKeySelective(yuanyuzhous.get(0));
                                userGrant.setYuanyuzhouid(yuanyuzhous.get(0).getId());
                                iszhaodao = 1;
                                break;
                            }
                        } else {
                            yuanyuzhous.get(0).setUserId(user.getUserId());
                            yuanyuzhous.get(0).setNickName(user.getNickName());
                            yuanyuzhous.get(0).setIsGoumai(0);
                            yuanyuzhous.get(0).setTime(new Date());
                            yuanyuzhouDao.updateByPrimaryKeySelective(yuanyuzhous.get(0));
                            userGrant.setYuanyuzhouid(yuanyuzhous.get(0).getId());
                            iszhaodao = 1;
                            break;
                        }
                    }
                    if (iszhaodao == 1) {
                        break;
                    }
                }
            }
            if (iszhaodao == 0) {

                yuanyuzhouExample = new YuanyuzhouExample();
                yuanyuzhouExample.createCriteria().andUserIdEqualTo(0).andYLessThan(45).andOpenEqualTo(Constants.YUANYUZHOU_OPEN);
                yuanyuzhouExample.setOrderByClause("RAND() asc");
                PageHelper.startPage(1, 1);
                yuanyuzhouList = yuanyuzhouDao.selectByExample(yuanyuzhouExample);
                if (yuanyuzhouList.size() == 0) {
                    throw new CloudException(ExceptionConstant.元宇宙ID失败);
                }
                yuanyuzhouList.get(0).setUserId(user.getUserId());
                yuanyuzhouList.get(0).setNickName(user.getNickName());
                yuanyuzhouList.get(0).setIsGoumai(0);
                yuanyuzhouList.get(0).setTime(new Date());
                yuanyuzhouDao.updateByPrimaryKeySelective(yuanyuzhouList.get(0));
                userGrant.setYuanyuzhouid(yuanyuzhouList.get(0).getId());
            }
        }


        mybox.setSpid(a);
        mybox.setType(1);
        myboxDao.updateByPrimaryKeySelective(mybox);
        Openboxrecord openboxrecord = new Openboxrecord();
        openboxrecord.setUserid(userid);
        openboxrecord.setCreatetime(new Date());
        openboxrecord.setName(collection.getName());
        openboxrecord.setNo(no);
        openboxrecord.setImg(collection.getImg());
        openboxrecordDao.insertSelective(openboxrecord);
        Collection collections = collectionDao.selectByPrimaryKey(a);


        userGrant.setUserid(userid);
        userGrant.setCollid(a);

        userGrant.setTruenumber("0");
        userGrant.setTradeTime(new Date());
        userGrant.setCreatetime(new Date());
        userGrant.setBuyprice(collections.getPrice());
        userGrantDao.insertSelective(userGrant);


        map.put("collectionid", collection.getId());
        map.put("user_grantid", userGrant.getId());
        map.put("type", type);

        boolean isReleaseLockSuccess = cloudRedisTemplate.releaseLock(lockKey, lockValue);
        if (!isReleaseLockSuccess) {
            log.info("openbox releaseLock failed, transaction rollback.");
            throw new CloudException(ExceptionConstant.频率太高请稍等);
        }
        return map;
    }
    @DS("slave")
    @NotNull
    private Map<Integer, Collection> getId2CollectMap(List<Integer> cids) {
        final Map<Integer, Collection> id2CollectMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(cids)) {
            CollectionExample ex = new CollectionExample();
            ex.createCriteria()
              .andIdIn(cids);
            final List<Collection> collections = collectionDao.selectByExampleWithBLOBs(ex);
            final Map<Integer, Collection> m = collections.stream()
                                                          .collect(Collectors.toMap(Collection::getId,
            Function.identity(), (k1, k2) -> k1));
            id2CollectMap.putAll(m);

        }
        return id2CollectMap;
    }
    @DS("slave")
    @Override
    public List<Map<String,Object>> openBoxRecord(int userid) {
        OpenboxrecordExample openboxrecordExample = new OpenboxrecordExample();
        openboxrecordExample.createCriteria()
                            .andUseridEqualTo(userid);
        List<Openboxrecord> openboxrecords = openboxrecordDao.selectByExample(openboxrecordExample);
        return openboxrecords.stream()
                             .map(openboxrecord -> {
                                 Map<String, Object> map = new HashMap<>();
                                 map.put("name", openboxrecord.getName());
                                 map.put("img", ImgEnum.img.getUrl() + openboxrecord.getImg());
                                 map.put("no", openboxrecord.getNo());
                                 map.put("date", DateUtils.getDateToStr(openboxrecord.getCreatetime(),
                                                                        DateUtils.DATETIME_FORMAT));
                                 return map;
                             })
                             .collect(Collectors.toList());

    }
    @DS("slave")
    @Override
    public List<Map<String, Object>> SyntheticCollection(int userid) {
        return synthesisDao.selectByExample(new SynthesisExample())
                           .stream()
                           .map(synthesis -> {
                               Map<String, Object> map = new HashMap<>();
                               map.put("id", synthesis.getId());
                               map.put("img", ImgEnum.img.getUrl() + synthesis.getImg());
                               map.put("name", synthesis.getName());
                               map.put("count", "限量 " + synthesis.getLimitcount());
                               map.put("date", DateUtils.getDateToStr(synthesis.getSisbigtime(),
                                                                      DateUtils.DATE_FORMAT) + " 至 "
                                               + DateUtils.getDateToStr(synthesis.getSisendtime(),
                                                                        DateUtils.DATE_FORMAT));
                               return map;
                           })
                           .collect(Collectors.toList());
    }

    @DS("slave")
    @Override
    public Map synthesis(int userid, int id) {
        Synthesis synthesis = synthesisDao.selectByPrimaryKey(id);
        Map map = new HashMap();
        map.put("id", synthesis.getId());
        map.put("img", ImgEnum.img.getUrl() + synthesis.getImg());
        map.put("name", synthesis.getName());
        //map.put("count", "限量 " + synthesis.getLimitcount() + "|" + "剩余 " + (synthesis.getLimitcount() - synthesis.getSynthesized()));
        map.put("count", "限量 " + synthesis.getLimitcount());
        map.put("date", DateUtils.getDateToStr(synthesis.getSisbigtime(), DateUtils.DATE_FORMAT) + " 至 " + DateUtils.getDateToStr(synthesis.getSisendtime(), DateUtils.DATE_FORMAT));
        map.put("limit", "每人限购" + synthesis.getLimitcount() + "份");
        List list1 = new ArrayList();
        JSONArray jsonObject = JSONArray.fromObject(synthesis.getMatsci());

        final List<@Nullable Integer> cids = Lists.newArrayList();

        for (int i = 0; i < jsonObject.size(); i++) {
            net.sf.json.JSONObject job        = jsonObject.getJSONObject(i);
            int                    fragment   = Integer.parseInt(job.get("fragment")
                                                                    .toString());
            cids.add(fragment);

        }

        final Map<Integer, Collection> id2CollectMap = getId2CollectMap(cids);


        for (int i = 0; i < jsonObject.size(); i++) {
            net.sf.json.JSONObject job        = jsonObject.getJSONObject(i);
            Map                    map1       = new HashMap();
            int                    fragment   = Integer.parseInt(job.get("fragment")
                                                                    .toString());
            // Collection             collection = collectionDao.selectByPrimaryKey(fragment);
            final Collection collection = id2CollectMap.get(fragment);
            map1.put("name", collection.getName());
            map1.put("count", job.get("count"));
            map1.put("img", ImgEnum.img.getUrl() + collection.getImg());
            list1.add(map1);
        }
        map.put("fragment", list1);
        //比较两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
        if (DateUtils.compareTo(new Date(), synthesis.getSisbigtime(), DateUtils.DATETIME_FORMAT) == 1) {
            map.put("type", 0);
            map.put("describe", "活动未开始");
        } else {
            if (DateUtils.compareTo(new Date(), synthesis.getSisendtime(), DateUtils.DATETIME_FORMAT) == 1) {
                map.put("type", 1);
                map.put("describe", "合成将于" + DateUtils.getDateToStr(synthesis.getSisendtime(), DateUtils.TIME_FORMAT1) + " 结束");
            }
            if (DateUtils.compareTo(new Date(), synthesis.getSisendtime(), DateUtils.DATETIME_FORMAT) == -1) {
                map.put("type", 2);
                map.put("describe", "活动已结束");
            }
        }
        return map;
    }

    @Override
   @Transactional(rollbackFor = Exception.class) public Map synthesisprize(int userid, int id, String no) {
        // if (userid != 488) {
        //     throw new CloudException(ExceptionConstant.频率太高请稍等);
        // }

        String lockKey = "synthesisprize#" + userid + "#" + id;
        String lockValue = UUID.randomUUID().toString();
        boolean isLockSuccess = cloudRedisTemplate.getLock(lockKey, lockValue, 5);
        if (!isLockSuccess) {
            throw new CloudException(ExceptionConstant.频率太高请稍等);
        }
        log.info("synthesisprize lock successfully, userid={}", userid);

        Synthesis synthesis = synthesisDao.selectByPrimaryKey(id);
        //比较两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
        if (DateUtils.compareTo(new Date(), synthesis.getSisbigtime(), DateUtils.DATETIME_FORMAT) == 1) {
            throw new CloudException(ExceptionConstant.合成暂未开始);
        }
        if (DateUtils.compareTo(new Date(), synthesis.getSisendtime(), DateUtils.DATETIME_FORMAT) == -1) {
            throw new CloudException(ExceptionConstant.本轮合成已结束);
        }
        if (synthesis.getSynthesized() >= synthesis.getLimitcount()) {
            throw new CloudException(ExceptionConstant.本轮合成已结束);
        }
        List<FragmentVO> fragmentVOS = toFragmentVOS(synthesis.getMatsci());
        assert fragmentVOS != null;
        List<Integer>          allCollIds      = getCollIds(fragmentVOS);
        ImmutableList<Integer> fragmentCollIds = ImmutableList.copyOf(allCollIds);
        allCollIds.add(synthesis.getCollid());
        Map<Integer, Collection> id2CollectMap = getId2CollectMap(allCollIds);

        List<WrappedFragmentVO> wrappedFragmentVOS = toWrappedFragmentVOS(fragmentVOS, id2CollectMap);
        Collection collections = id2CollectMap.get(synthesis.getCollid());
        if (null == collections) {
            throw new CloudException(ExceptionConstant.目标合成物已不存在);
        }
        checkCountLimit(userid, synthesis, collections);

        UserGrantExample userGrantExample = new UserGrantExample();
        userGrantExample.createCriteria().andUseridEqualTo(userid).andCollidIn(fragmentCollIds).andTypeEqualTo(0);
        List<UserGrant> userGrants = userGrantDao.selectByExample(userGrantExample);

        Map<Integer, List<UserGrant>> collId2UserGrantListMap = userGrants.stream()
                .collect(Collectors.groupingBy(UserGrant::getCollid));

        Map<Integer, Long> collId2CountMap = userGrants.stream()
                .collect(Collectors.groupingBy(UserGrant::getCollid, Collectors.counting()));
        final Map<Integer, Integer> id2LimitMap = wrappedFragmentVOS.stream()
                                                                .collect(Collectors.toMap(WrappedFragmentVO::getId,
                                                                        WrappedFragmentVO::getCount,
                                                                        (k1, k2) -> k1));
        boolean isNotEnough = wrappedFragmentVOS.stream().anyMatch( x -> {
            int collId = x.getId();
            Long userGrantCount = collId2CountMap.get(collId);
            if (userGrantCount == null) {
                return true;
            }
            int fragmentNeededCount = x.getCount();
            return userGrantCount < fragmentNeededCount;//user's grant count is not enough
        });
        if (isNotEnough) {
            throw new CloudException(ExceptionConstant.碎片未集齐);
        }

        batchBurnFragments(collId2UserGrantListMap, id2LimitMap);//burn fragments
        createNewCollection(userid, collections);//synthesis new Collection
        createNewSynthesisRecord(userid, synthesis);

        int updateCount = synthesisDao.updateSynthesizedSafelyByPrimaryKey(id);
        if (0 == updateCount) {
            log.info("达到合成上限");
            throw new CloudException(ExceptionConstant.已达当前藏品合成上限);
        }

        boolean isReleaseLockSuccess = cloudRedisTemplate.releaseLock(lockKey, lockValue);
        if (!isReleaseLockSuccess) {
            log.info("synthesisprize unlock failed, transaction rollback");
            throw new CloudException(ExceptionConstant.频率太高请稍等);
        }
        return ImmutableMap.of("name", collections.getName(),
                               "img", ImgEnum.img.getUrl() + collections.getImg());
    }

    private void createNewSynthesisRecord(int userid, Synthesis synthesis) {
        SynthesisRecord synthesisRecord = new SynthesisRecord();
        synthesisRecord.setUserid(userid);
        synthesisRecord.setName(synthesis.getName());
        synthesisRecord.setCreatetime(new Date());
        synthesisRecordDao.insert(synthesisRecord);
    }

    private void createNewCollection(int userid, Collection collections) {
        UserGrant userGrant = new UserGrant();
        userGrant.setUserid(userid);
        userGrant.setCollid(collections.getId());
        userGrant.setTruenumber("0");
        userGrant.setTradeTime(new Date());
        userGrant.setCreatetime(new Date());
        userGrant.setBuyprice(new BigDecimal(0.01));
        userGrantDao.insertSelective(userGrant);
    }


    /**
     * 批量销毁碎片
     * @param collId2UserGrantListMap
     * @param collId2LimitCountMap
     */
    private void batchBurnFragments(Map<Integer, List<UserGrant>> collId2UserGrantListMap,
     Map<Integer, Integer> collId2LimitCountMap) {

        final List<UserGrant> toUpdateUserGrantList = collId2UserGrantListMap.entrySet()
                                                               .stream()
                                                               .map(entry -> {
                                                                   final Integer         key   = entry.getKey();
                                                                   final List<UserGrant> value = entry.getValue();
                                                                   final int needAmount =
                                                                           collId2LimitCountMap.get(key);
                                                                   return value.stream()
                                                                               .filter(Objects::nonNull)
                                                                               .limit(needAmount)
                                                                               .peek(x -> x.setType(4))
                                                                               .collect(Collectors.toList());
                                                               })
                                                               .flatMap(List::stream)
                                                               .collect(Collectors.toList());


        List<Fragment> fragments = toUpdateUserGrantList.stream().filter(Objects::nonNull).map(x -> {
            Fragment fragment = new Fragment();
            fragment.setCollid(x.getCollid());
            fragment.setHashs(x.getHashs());
            return fragment;
        }).collect(Collectors.toList());
        // turn into fragments
        if (CollectionUtils.isNotEmpty(fragments)) {
            fragmentDao.insertBatchSelective(fragments);
        }

        log.info("销毁销毁碎片详情: {}", JSON.toJSONString(toUpdateUserGrantList));
        //batch ruin userGrants
        if (CollectionUtils.isNotEmpty(toUpdateUserGrantList)) {
            userGrantDao.updateBatchByPrimaryKeySelective(toUpdateUserGrantList);
        }
    }

    @NotNull
    private static List<Integer> getCollIds(List<FragmentVO> fragmentVOS) {
        return fragmentVOS.stream().map(FragmentVO::getFragment).distinct().collect(Collectors.toList());
    }

    @NotNull
    private static List<WrappedFragmentVO> toWrappedFragmentVOS(List<FragmentVO> fragmentVOS,
                                                                Map<Integer, Collection> id2CollectMap) {
        return fragmentVOS.stream().map(x -> {
            Integer collId = x.getFragment();
            Collection collection = id2CollectMap.get(collId);
            WrappedFragmentVO wrappedFragmentVO = new WrappedFragmentVO();
            wrappedFragmentVO.setId(collId);
            wrappedFragmentVO.setName(collection.getName());
            wrappedFragmentVO.setCount(x.getCount());
            wrappedFragmentVO.setImg(ImgEnum.img.getUrl() + collection.getImg());
            return wrappedFragmentVO;
        }).collect(Collectors.toList());
    }

    @org.jetbrains.annotations.Nullable
    public static List<FragmentVO> toFragmentVOS(String matsci) {
        return JSON.parseObject(matsci, new TypeReference<List<FragmentVO>>() {
        }.getType());
    }

    /**
     * 检查是否超限
     * @param userid
     * @param synthesis
     * @param collections
     */
    private void checkCountLimit(int userid, Synthesis synthesis, Collection collections) {
        SynthesisRecordExample synthesisRecordExample = new SynthesisRecordExample();
        synthesisRecordExample.createCriteria().andUseridEqualTo(userid).andNameEqualTo(collections.getName())
                .andCreatetimeBetween(synthesis.getSisbigtime(), synthesis.getSisendtime());
        long myCount = synthesisRecordDao.countByExample(synthesisRecordExample);
        if (myCount >= synthesis.getUserlimit()) {
            throw new CloudException(ExceptionConstant.已达限购量);
        }
    }

    @DS("slave")
    @Override
    public List synthesisrecord(int userid) {
        List list = new ArrayList();
        SynthesisRecordExample synthesisRecordExample = new SynthesisRecordExample();
        synthesisRecordExample.createCriteria().andUseridEqualTo(userid);
        List<SynthesisRecord> synthesisRecords = synthesisRecordDao.selectByExample(synthesisRecordExample);
        for (SynthesisRecord synthesisRecord : synthesisRecords) {
            Map map = new HashMap();
            map.put("name", synthesisRecord.getName());
            map.put("date", DateUtils.getDateToStr(synthesisRecord.getCreatetime(), DateUtils.DATE_FORMAT));
            list.add(map);
        }
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List myorder(int userid, int type) {
        List list = new ArrayList();
        MyOrderExample myOrderExample = new MyOrderExample();
        //0.全部1.待付款2.已付款3.已取消
        if (type == 1) {
            List<Integer> integers = new ArrayList<>();
            integers.add(0);
            integers.add(-1);
            myOrderExample.createCriteria().andUseridEqualTo(userid).andOrdertypeIn(integers);
        } else if (type == 2) {
            myOrderExample.createCriteria().andUseridEqualTo(userid).andOrdertypeEqualTo(1);
        } else if (type == 3) {
            myOrderExample.createCriteria().andUseridEqualTo(userid).andOrdertypeEqualTo(2);
            myOrderExample.setPageNo(1);
            myOrderExample.setPageSize(50);
        } else {
            myOrderExample.createCriteria().andUseridEqualTo(userid);
            myOrderExample.setPageNo(1);
            myOrderExample.setPageSize(100);
        }
        myOrderExample.setOrderByClause("createtime desc");
        List<MyOrder> myOrders = myOrderDao.selectByExample(myOrderExample);

        final List<Integer> ids = myOrders.stream()
                                          .filter(x -> x.getIstype() == 1)
                                          .map(MyOrder::getCollid)
                                          .collect(Collectors.toList());
        final Map<Integer, Collection> id2CollectMap = getId2CollectMap(ids);

        final List<Integer> blindCids = myOrders.stream()
                                          .filter(x -> x.getIstype() == 2)
                                          .map(MyOrder::getCollid)
                                          .collect(Collectors.toList());
        final Map<Integer, Blindbox> id2BlindMap = getId2BlindMap(blindCids);

        myOrders.forEach(x -> list.add(convert2view(id2CollectMap, id2BlindMap, x)));

        // todo 没看懂CancelRecord的意义, 先注掉试试
        /*if (type == 0) {
            CancelRecordExample cancelRecordExample = new CancelRecordExample();
            cancelRecordExample.createCriteria().andUseridEqualTo(userid);
            cancelRecordExample.setStartRow(1);
            cancelRecordExample.setPageSize(10);
            cancelRecordExample.setOrderByClause("id asc");
            List<CancelRecord> cancelRecords = cancelRecordDao.selectByExample(cancelRecordExample);
            for (CancelRecord cancelRecord : cancelRecords) {
                Map map1 = new HashMap();
                map1.put("name", cancelRecord.getName());
                map1.put("img", ImgEnum.img.getUrl() + cancelRecord.getImg());
                map1.put("no", cancelRecord.getNo());
                map1.put("price", cancelRecord.getPrice());
                map1.put("type", 4);
                list.add(map1);
            }
        }*/
        return list;
    }

    private Map<String, Object> convert2view(Map<Integer, Collection> id2CollectMap, Map<Integer, Blindbox> id2BlindMap,
                                             MyOrder myOrder) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", myOrder.getId());
        map.put("no", myOrder.getOrderno());
        map.put("price", myOrder.getPrice());
        map.put("paytype", myOrder.getPaytype());
        switch (myOrder.getOrdertype()) {
            case 1:
                map.put("type", 3);
                break;
            case 2:
                map.put("type", 4);
                break;
            default:
                map.put("type", 2);
        }

        if (myOrder.getIstype() == 1) {
            Collection collection = id2CollectMap.get(myOrder.getCollid());
            if (collection != null) {
                map.put("collPaytype", collection.getPaytype());
                map.put("name", collection.getName());
                map.put("img", ImgEnum.img.getUrl() + collection.getImg());
                map.put("state", 1);
            }
        } else {
            Blindbox blindbox = id2BlindMap.get(myOrder.getCollid());
            if (blindbox != null) {
                map.put("collPaytype", blindbox.getPaytype());
                map.put("name", blindbox.getName());
                map.put("img", ImgEnum.img.getUrl() + blindbox.getImg());
                map.put("state", 0);
            }
        }
        return map;
    }

    private Map<Integer, Blindbox> getId2BlindMap(List<Integer> blindCids) {
        final Map<Integer, Blindbox> id2BlindMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(blindCids)) {
            BlindboxExample ex = new BlindboxExample();
            ex.createCriteria().andIdIn(blindCids);
            final List<Blindbox> blindBoxes = blindboxDao.selectByExampleWithBLOBs(ex);
            final Map<Integer, Blindbox> m = blindBoxes.stream()
                                                          .collect(Collectors.toMap(Blindbox::getId,
                                                                  Function.identity(), (k1, k2) -> k1));
            id2BlindMap.putAll(m);

        }
        return id2BlindMap;
    }

    @Override
    @DS("slave")
    public List scorder(int userid, int type) {
        List list = new ArrayList();
        if (type != 3) {
            UserGrantExample userGrantExample = new UserGrantExample();
            userGrantExample.setOrderByClause("id desc");
            if (type == 0) {//0.全部1.待付款2.已付款3.已取消
                List<Integer> integers = new ArrayList<>();
                integers.add(2);
                integers.add(3);
                userGrantExample.createCriteria().andOppositeuserEqualTo(userid).andTypeIn(integers);
            } else if (type == 1) {//0.全部1.待付款2.已付款3.已取消
                userGrantExample.createCriteria().andOppositeuserEqualTo(userid).andTypeEqualTo(2);
            } else if (type == 2) {
                userGrantExample.createCriteria().andOppositeuserEqualTo(userid).andTypeEqualTo(3);
            }
            List<UserGrant> userGrants = userGrantDao.selectByExample(userGrantExample);
            final List<Integer> cids = userGrants.stream()
                                                    .map(UserGrant::getCollid)
                                                    .collect(Collectors.toList());
            final Map<Integer, Collection> id2CollectMap = getId2CollectMap(cids);
            for (UserGrant userGrant : userGrants) {
                Map map = new HashMap();
                // Collection collection = c?ollectionDao.selectByPrimaryKey(userGrant.getCollid());
                final Collection collection = id2CollectMap.get(userGrant.getCollid());
                if (collection != null) {
                    map.put("id", userGrant.getId());
                    map.put("name", collection.getName());
                    map.put("img", ImgEnum.img.getUrl() + collection.getImg());
                    map.put("no", userGrant.getTruenumber());
                    map.put("type", userGrant.getType());
                    map.put("price", userGrant.getSellprice());
                    list.add(map);
                }
            }
        } else {
            CancelRecordExample userGrantExample = new CancelRecordExample();
            userGrantExample.createCriteria().andUseridEqualTo(userid);
            userGrantExample.setStartRow(1);
            userGrantExample.setPageSize(50);
            List<CancelRecord> userGrants = cancelRecordDao.selectByExample(userGrantExample);
            for (CancelRecord userGrant : userGrants) {
                Map map = new HashMap();
                map.put("name", userGrant.getName());
                map.put("img", ImgEnum.img.getUrl() + userGrant.getImg());
                map.put("no", userGrant.getNo());
                map.put("price", userGrant.getPrice());
                list.add(map);
            }
        }
        return list;
    }

    @Override
    public void checkTradeStatus() {
        //获取当前时间
        int a = Integer.parseInt(dictService.getValue("djs"));
        Date date = new Date();
        date.setTime(date.getTime() - a * 60 * 1000);
        UserGrantExample userGrantExample = new UserGrantExample();
        userGrantExample.createCriteria().andTypeEqualTo(2).andBuytimeLessThan(date);
        List<UserGrant> userGrants = userGrantDao.selectByExample(userGrantExample);
        userGrants.forEach(x -> {
            x.setType(1);
            x.setOppositeuser(0);
        });
        if (CollectionUtils.isNotEmpty(userGrants)) {
            userGrantDao.updateBatchByPrimaryKeySelective(userGrants);
        }
    }

    @Override
    public void checkqian() {
        List<Integer> listSS = new ArrayList();
        List<Integer> list = new ArrayList();
        IssueExample issueExample = new IssueExample();
        issueExample.createCriteria().andGinsengtypeEqualTo(2).andCheckoutEqualTo(0).andEndtimeLessThan(new Date());
        List<Issue> issues = issueDao.selectByExample(issueExample);
        for (Issue issue : issues) {
            SignupExample signupExample = new SignupExample();
            signupExample.createCriteria().andIsidEqualTo(issue.getId()).andBegintimeEqualTo(issue.getReleasetime()).andTypeEqualTo(0);
            List<Signup> signups = signupDao.selectByExample(signupExample);
            for (Signup signup : signups) {
                signup.setType(1);
                signupDao.updateByPrimaryKeySelective(signup);
            }
            int randomSeriesLength = issue.getGinscount();//预售数量
            //把集合随机排序
            Collections.shuffle(signups);
            //判断 大小
            if (signups.size() < randomSeriesLength) {
                randomSeriesLength = signups.size();
            }
            //取出randomSeriesLength个
            List<Signup> randomSeries = signups.subList(0, randomSeriesLength);

            for (Signup randomSery : randomSeries) {
                listSS.add(randomSery.getId());
                randomSery.setType(2);
                signupDao.updateByPrimaryKeySelective(randomSery);
                //获取指定订单 给予中签结果
                MyOrder myOrder = myOrderDao.selectByPrimaryKey(randomSery.getMyorderid());
                if (myOrder != null) {
                    if (myOrder.getIstype() == 1) {
                        list.add(myOrder.getId());
                        //发行
                    } else if (myOrder.getIstype() == 2) {
                        Mybox mybox = new Mybox();
                        mybox.setNo(myOrder.getOrderno());
                        mybox.setUserid(myOrder.getUserid());
                        mybox.setBoxid(myOrder.getCollid());
                        mybox.setOrderid(myOrder.getId());
                        myboxDao.insertSelective(mybox);
                    }
                }
            }
            SignupExample signupExample1 = new SignupExample();
            signupExample1.createCriteria().andIsidEqualTo(issue.getId()).andBegintimeEqualTo(issue.getReleasetime()).andIdNotIn(listSS);
            List<Signup> signupss = signupDao.selectByExample(signupExample1);
            for (Signup signup : signupss) {
                Users users = usersDao.selectByPrimaryKey(signup.getUserid());
                if (users != null) {
                    Collection collection = collectionDao.selectByPrimaryKey(issue.getCollid());
                    BigDecimal bigDecimal = users.getBalance().add(collection.getPrice());
                    users.setBalance(bigDecimal);
                    usersDao.updateByPrimaryKeySelective(users);
                    balanceRecordProcessor.add(users.getUserId(), collection.getPrice(), BalanceTypeEnum.LOTTERY, null,
                            collection.getName() + "未中奖", bigDecimal);
                }
            }
            issue.setCheckout(1);
            issueDao.updateByPrimaryKeySelective(issue);
        }
        // List<Integer> integers = new ArrayList<>();
        final Map<Integer, MyOrder> id2MyOrderMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(list)) {
            MyOrderExample ex = new MyOrderExample();
            ex.createCriteria()
              .andIdIn(list);
            final List<MyOrder> myOrders = myOrderDao.selectByExample(ex);
            final Map<Integer, MyOrder> m = myOrders.stream()
                                                          .collect(Collectors.toMap(MyOrder::getId, Function.identity(), (k1, k2) -> k1));
            id2MyOrderMap.putAll(m);
        }


        final List<UserGrant> userGrants2Update = list.stream()
                                                      .map(id2MyOrderMap::get)
                                                      .filter(Objects::nonNull)
                                                      .map(x -> {
                                                          UserGrant userGrant = new UserGrant();
                                                          userGrant.setUserid(x.getUserid());
                                                          userGrant.setCollid(x.getCollid());
                                                          userGrant.setTruenumber("0");
                                                          userGrant.setCreatetime(new Date());
                                                          userGrant.setTradeTime(new Date());
                                                          userGrant.setBuyprice(x.getPrice());
                                                          userGrant.setType(-1);
                                                          return userGrant;
                                                      })
                                                      .collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(userGrants2Update)) {
            userGrantDao.insertBatchSelective(userGrants2Update);
        }

        // for (Integer myOrderId : list) {
        //     // MyOrder myOrder = myOrderDao.selectByPrimaryKey(myOrderId);
        //     final MyOrder myOrder = id2MyOrderMap.get(myOrderId);
        //     UserGrant userGrant = new UserGrant();
        //     userGrant.setUserid(myOrder.getUserid());
        //     userGrant.setCollid(myOrder.getCollid());
        //     userGrant.setTruenumber("0");
        //     userGrant.setCreatetime(new Date());
        //     userGrant.setBuyprice(myOrder.getPrice());
        //     userGrant.setType(-1);
        //     // userGrantDao.insertSelective(userGrant);
        //     // integers.add(userGrant.getId());
        // }


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelorder(int userid, int id) {
        String lockValue = UUID.randomUUID().toString();
        String lockKey = "MineServiceImpl.cancelorder" + "#" + userid + "#" + id;
        final boolean lock = cloudRedisTemplate.getLock(lockKey, lockValue, 3);
        if (!lock) {
            throw new CloudException(ExceptionConstant.频率太高请稍等);
        }
        MyOrder myOrder = myOrderDao.selectByPrimaryKey(id);
        if (myOrder.getOrdertype() == 2) {
            throw new CloudException(ExceptionConstant.订单状态已改变);
        }
        myOrder.setOrdertype(2);
        myOrderDao.updateByPrimaryKeySelective(myOrder);
        final Integer issueId = myOrder.getIssueId();
        if (userid == myOrder.getUserid()) {
            if (myOrder.getIstype() == 1) {
                //藏品还未发放 删除 退次数
                Collection collections = collectionDao.selectByPrimaryKey(myOrder.getCollid());
                if (collections != null) {
                    IssueExample issueExample = new IssueExample();
                    issueExample.createCriteria().andCollidEqualTo(myOrder.getCollid());
                    List<Issue> issues = issueDao.selectByExample(issueExample);
                    if (CollectionUtils.isNotEmpty(issues)) {
                        final int updateSuccessCnt = issueDao.increaseSoldByPrimaryKey(issueId, -1);
                        if (0 ==  updateSuccessCnt) {
                            throw new CloudException(ExceptionConstant.库存不足);
                        }
                        CancelRecord cancelRecord = new CancelRecord();
                        cancelRecord.setUserid(userid);
                        cancelRecord.setImg(collections.getImg());
                        cancelRecord.setName(collections.getName());
                        cancelRecord.setNo(myOrder.getOrderno());
                        cancelRecord.setPrice(myOrder.getPrice());
                        cancelRecordDao.insertSelective(cancelRecord);
                    }
                }
            } else {
                //藏品还未发放 删除 退次数
                Blindbox collection = blindboxDao.selectByPrimaryKey(myOrder.getCollid());
                if (collection != null) {
                    IssueExample issueExample = new IssueExample();
                    issueExample.createCriteria().andCollidEqualTo(myOrder.getCollid());
                    List<Issue> issues = issueDao.selectByExample(issueExample);
                    if (CollectionUtils.isNotEmpty(issues)) {
                        final int updateSuccessCnt = issueDao.increaseSoldByPrimaryKey(issueId, -1);
                        if (0 ==  updateSuccessCnt) {
                            throw new CloudException(ExceptionConstant.库存不足);
                        }

                        CancelRecord cancelRecord = new CancelRecord();
                        cancelRecord.setUserid(userid);
                        cancelRecord.setImg(collection.getImg());
                        cancelRecord.setName(collection.getName());
                        cancelRecord.setNo(myOrder.getOrderno());
                        cancelRecord.setPrice(myOrder.getPrice());
                        cancelRecordDao.insertSelective(cancelRecord);
                    }

                }
            }

            //返还用户5积分
            userService.returnPoints(userid);

            final boolean isUnLocked = cloudRedisTemplate.releaseLock(lockKey, lockValue);
            if (!isUnLocked) {
                throw new CloudException(ExceptionConstant.频率太高请稍等);
            }
            cloudRedisTemplate.delete("OrderExpireImpl" + BusinessConstant.ORDER_EXPIRE_KEY + id);
            //redis乐观锁控制预售数量
            // cloudRedisTemplate.watchLock(BusinessConstant.ISSUE_KEY + issueId, 1, false);

        }
    }

    @Override
    public void cancelscorder(int userid, int id) {
        UserGrant userGrant = userGrantDao.selectByPrimaryKey(id);
        if (userGrant.getType() == 2) {
            Collection collection = collectionDao.selectByPrimaryKey(userGrant.getCollid());
            userGrant.setType(1);
            userGrant.setOppositeuser(null);
            userGrantDao.updateByPrimaryKeySelective(userGrant);
            CancelRecord cancelRecord = new CancelRecord();
            cancelRecord.setUserid(userid);
            cancelRecord.setImg(collection.getImg());
            cancelRecord.setName(collection.getName());
            cancelRecord.setNo(userGrant.getTruenumber());
            cancelRecord.setPrice(userGrant.getSellprice());
            cancelRecordDao.insertSelective(cancelRecord);
        }
    }
    @DS("slave")

    public Map<String, Object> getInviteInfo(SysUserParam param) {
        Map<String, Object> map = new HashMap<>();
        Users user = usersDao.selectByPrimaryKey(param.getLogUserPid());
        map.put("code", user.getInvitationCode());
        List<Chat> chats = chatDao.selectByExample(null);
        map.put("img", chats.isEmpty() ? "" : chats.get(0).getImg());
        //String img = createUserShareImg(user.getInvitationCode());
        //图片展示地址 生成存储地址 生成链接路径
        //map.put("QrCode", img);
        //map.put("url", net + "/#/pages/login/register/?uid=" + user.getInvitationCode());
        return map;
    }


    @Value("${app.net:https://www.dopaimeta.net}")
    private String net;
    /**
     * 生成邀请二维码
     *
     * @param registerCode
     * @return
     */
    public String createUserShareImg(String registerCode) {
        String       fileName   = "InvQRCode" + registerCode + ".jpg";
        String       url        = net + "/#/pages/login/register/?uid=" + registerCode;
        final String pathname   = ImgEnum.QrCode.getTmp() + fileName;
        File         qrCodeFile = new File(pathname);
        boolean      isSuccess  = QRcode.createQRCode(qrCodeFile, 200, 200, url);
        if (isSuccess) {
            //上传文件
            MultipartFile multipartFile = null;
            try {
                FileInputStream inputStream = new FileInputStream(qrCodeFile);
                multipartFile = new MockMultipartFile(qrCodeFile.getName(), qrCodeFile.getName(),
                                                      ContentType.APPLICATION_OCTET_STREAM.toString(), inputStream);
            } catch (IOException e) {
                log.error("createUserShareImg IOException", e);
            }
            if (multipartFile != null) {
                uploadQrWithOss(multipartFile);
                //上传成功后删除本地文件
                final boolean delete = qrCodeFile.delete();
                if (delete) {
                    log.info("本地文件删除成功");
                } else {
                    log.info("本地文件删除失败");
                }

                return ImgEnum.QrCode.getUrl() + "/profile/" + fileName;
            }
        }
        return null;
    }


    @Override
    @DS("slave")
    public String inviteInfoimgs(Integer userId, int id) {
        Chat   chat    = chatDao.selectByPrimaryKey(id);
        String chatimg = chat.getImg()
                             .substring(9);

        Users        user               = usersDao.selectByPrimaryKey(userId);
        final String invitationCode     = user.getInvitationCode();
        final String tmp                = ImgEnum.img.getTmp();
        String       qrCodeImage        = ImgEnum.QrCode.getTmp() + createUserShareImg1(invitationCode);
        String       templatePath       = ImgEnum.img.getPath() + chatimg;
        String       targetComposedPath = "composed" + invitationCode + ".jpg";
        String       imgPath;
        try {
            imgPath = composePic(templatePath, qrCodeImage, targetComposedPath);


            File file = new File(tmp + File.separator + targetComposedPath);
            if (file.exists()) {
                //将file 文件 转换成multipartFile
                FileInputStream inputStream = null;
                try {
                    inputStream = new FileInputStream(file);
                } catch (FileNotFoundException e) {
                    log.error("FileNotFoundException e", e);
                    throw new CloudException(ExceptionConstant.生成失败);
                }
                MultipartFile multipartFile = null;
                try {
                    multipartFile = new MockMultipartFile(file.getName(), file.getName(),
                                                          ContentType.APPLICATION_OCTET_STREAM.toString(), inputStream);
                } catch (IOException e) {
                    log.error("MockMultipartFile e", e);
                    throw new CloudException(ExceptionConstant.生成失败);
                }

                //上传文件
                assert multipartFile != null;
                uploadQrWithOss(multipartFile);

            }
        } finally {
            final File f = new File(qrCodeImage);
            if (f.exists()) {
                f.delete();
            }

            File file = new File(tmp + File.separator + targetComposedPath);
            if (file.exists()) {
                file.delete();
            }

        }

        return imgPath;
    }


    private void uploadQrWithOss(MultipartFile file ) {
        //工具类获取值：分别是：地域节点、id、秘钥、项目名称
        String endpoint = OssConstantPropertiesUtil.END_POINT;
        String accessKeyId = OssConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = OssConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = OssConstantPropertiesUtil.BUCKET_NAME;
        try {
            // 创建OSS实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            //获取上传文件输入流
            InputStream inputStream = file.getInputStream();
            //获取文件名称
            String fileName = file.getOriginalFilename();

            assert fileName != null;

            String newName = "profile/" + fileName;

            //调用oss方法实现上传
            //参数一：Bucket名称  参数二：上传到oss文件路径和文件名称  比如 /aa/bb/1.jpg 或者直接使用文件名称  参数三：上传文件的流
            ossClient.putObject(bucketName, newName, inputStream);
            ossClient.shutdown();

            //把上传之后的文件路径返回
            //需要把上传到阿里云路径返回    https://edu-guli-eric.oss-cn-beijing.aliyuncs.com/1.jpg
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String chat() {
        return ImgEnum.img.getUrl() + chatDao.selectByPrimaryKey(1).getImg();
    }

    public static String composePic(String templatePath, String seedPath, String targetComposedPath) {
        try {
            //合成文件路径
            String path = ImgEnum.img.getTmp() + File.separator + targetComposedPath;
            final File file = new File(path);
            if (!file.exists()) {
                try {
                    File parentFile = file.getParentFile();
                    if (!parentFile.exists()) {
                        parentFile.mkdirs();
                    }
                    file.createNewFile();
                } catch (IOException e) {
                   log.error("composePic-createNewFile error", e);
                }
            }

            //---------------------------------合成图片步骤-----------------------------
            //背景
            // templatePath = "/Users/huruipeng/IdeaProjects/dopai_-system/src/main/resources/img.png";//todo 测试插桩
            log.info("templatePath = {}", templatePath);
            File templateFlie = new File(templatePath);
            BufferedImage bg = ImageIO.read(templateFlie);//读取背景图片
            int height = bg.getHeight();//背景图片的高
            int width = bg.getWidth();  //背景图片的宽
            BufferedImage qcCode = ImageIO.read(new File(seedPath));  //读取图片
            BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);//创建画布
            Graphics g = img.getGraphics();//生成画笔 开启画图
            final int qrWidth = qcCode.getWidth();
            final int qrHeight = qcCode.getHeight();
            // 绘制背景图片
            g.drawImage(bg.getScaledInstance(width, height, Image.SCALE_DEFAULT), 0, 0, null); // 绘制缩小后的图
            //绘制二维码图片  定位到背景图的右下角
            g.drawImage(qcCode.getScaledInstance(qrWidth, qrHeight, Image.SCALE_DEFAULT), width-qrWidth, height -qrHeight,
                        null); //
            // 绘制缩小后的图
            //最后一个参数用来设置字体的大小
            //关掉画笔
            g.dispose();
            ImageIO.write(img, "jpg", file);
            //返回合成图片的路径
            return ImgEnum.img.getUrl() + "/profile/" + targetComposedPath;
        } catch (Exception e) {
            log.error("composePic error", e);
            throw new CloudException(ExceptionConstant.生成失败);
        }
    }


    /**
     * 生成邀请二维码
     *
     * @param registerCode
     * @return
     */
    public String createUserShareImg1(String registerCode) {
        String fileName = "InvQRCode" + registerCode + ".jpg";
        File QrCodeFile = new File(ImgEnum.QrCode.getTmp() + fileName);//生成图片位置
        String url = net + "/#/pages/login/register/?uid=" + registerCode;
        boolean flag = QRcode.createQRCode(QrCodeFile, 100, 100, url);
        if (flag) {
            return fileName;
        } else {
            return null;
        }
    }
    @DS("slave")
    @Override
    public List tixian(int userId) {
        List list = new ArrayList();
        List<Recharge> tixian = rechargeDao.selectByExample(new RechargeExample());
        for (Recharge recharge : tixian) {
            Map map = new HashMap();
            map.put("count", recharge.getCount().stripTrailingZeros().toPlainString());
            list.add(map);
        }
        return list;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String Recharge(int userId, BigDecimal count, int type) throws AlipayApiException {
        int phb = Integer.parseInt(dictService.getValue("czkg"));
        if (phb == 0) {
            throw new CloudException(ExceptionConstant.充值通道未开启);
        }
        Users user = usersDao.selectByPrimaryKey(userId);
        if (user.getRealnametype() == 0) {
            throw new CloudException(ExceptionConstant.请先进行实名认证);
        }
        if (environment.acceptsProfiles(Profiles.of("test"))) {
            count = new BigDecimal("0.01");
        } else {
            if (count.compareTo(new BigDecimal(0)) < 1) {
                throw new CloudException(ExceptionConstant.数量有误);
            }
        }
        RechargeRecord rechargeRecord = new RechargeRecord();
        rechargeRecord.setUserid(userId);
        rechargeRecord.setCount(count);
        rechargeRecord.setCreatetime(new Date());
        rechargeRecordDao.insertSelective(rechargeRecord);
        String orderStr = "";
        if (type == 1) {
        } else if (type == 2) {
        } else if (type == 3) {
        } else if (type == 4) {
        } else if (type == 15) {
        } else if (type == 16 || type == 17) {//富有支付
        }
        return orderStr;
    }

    @Override
    @Cacheable(value = "Ranking")
    @DS("slave")
    public List Ranking(int rankType) {
        List list = new ArrayList();
        List<Users> usersList = new ArrayList<>();
        int count = Integer.parseInt(dictService.getValue("ranking"));
        UsersExample usersExample = new UsersExample();
        if (rankType == 0) {
            usersExample.setOrderByClause("invitationcount desc");
            usersExample.setPageNo(1);
            usersExample.setPageSize(count);
            usersList = usersDao.selectByExample(usersExample);
        } else if (rankType == 1) {
            //获取当前周时间范围
            Date startDayOfWeek = DateUtils.getBeginDayOfWeek();
            Date endDayOfWeek = DateUtils.getEndDayOfWeek();
            usersList = usersDao.selectRankingList(2, startDayOfWeek, endDayOfWeek, count);
        } else if (rankType == 2) {
            //获取当前月份开始时间
            Date startDayOfMonth = DateUtils.getBeginDayOfMonth();
            Date endDayOfMonth = DateUtils.getEndDayOfMonth();
            usersList = usersDao.selectRankingList(2, startDayOfMonth, endDayOfMonth, count);
        }

        for (Users user : usersList) {
            Map map = new HashMap();
            map.put("avatar", ImgEnum.img.getUrl() + user.getHeadPrtraits());//头像
            map.put("nickname", user.getNickName());//昵称
            map.put("phone", phoneMask(user.getPhoneNumber()));//电话
            map.put("number", user.getIntervalInvitationCount() != null ? user.getIntervalInvitationCount() : user.getInvitationcount());//邀请人数
            list.add(map);
        }
        return list;
    }
    @DS("slave")
    @Override
    public List address(Integer userId) {
        AddressExample addressExample = new AddressExample();
        addressExample.createCriteria().andUidEqualTo(userId);
        return addressDao.selectByExample(addressExample);
    }
    @Override
   @Transactional(rollbackFor = Exception.class) public void newaddress(Integer userId,
                           String name,
                           String phone,
                           String address,
                           String addressdetals,
                           int state) {
        if (state == 1) {//修改?
            AddressExample addressExample = new AddressExample();
            addressExample.createCriteria().andUidEqualTo(userId);
            List<Address> addressList = addressDao.selectByExample(addressExample);
            final List<Address> address2Update = addressList.stream()
                                                     .peek(x -> x.setState(0))
                                                     .collect(Collectors.toList());

            if (CollectionUtils.isNotEmpty(address2Update)) {
                addressDao.updateBatchByPrimaryKeySelective(address2Update);
            }

            // for (Address address1 : addressList) {
            //     address1.setState(0);
            //     addressDao.updateByPrimaryKeySelective(address1);
            // }
            // return;
        }

        //新增?

        Address address1 = new Address();
        address1.setState(state);
        address1.setName(name);
        address1.setPhone(phone);
        address1.setAddress(address);
        address1.setAddressdetals(addressdetals);
        address1.setUid(userId);
        addressDao.insertSelective(address1);


    }

    @Override
  @Transactional(rollbackFor = Exception.class)  public void defaultaddress(Integer userId, int id) {
        Address address = addressDao.selectByPrimaryKey(id);
        if (address != null) {

            List<Address> addressList = addressDao.selectByExample(new AddressExample());
            if (addressList.size() > 0) {
                for (Address address1 : addressList) {
                    address1.setState(0);
                    addressDao.updateByPrimaryKeySelective(address1);
                }
            }
            address.setState(1);
            addressDao.updateByPrimaryKeySelective(address);

        }

    }


    @Override
   @Transactional(rollbackFor = Exception.class) public void deladdress(Integer userId, int id) {
        addressDao.deleteByPrimaryKey(id);
        // return null;
    }

    @Override
  @Transactional(rollbackFor = Exception.class)  public void editaddress(Integer userId, String name, String phone, String address, String addressdetals,
                             int state, int id) {
        if (state == 1) {
            AddressExample addressExample = new AddressExample();
            addressExample.createCriteria().andUidEqualTo(userId);
            List<Address> addressList = addressDao.selectByExample(addressExample);
            for (Address address1 : addressList) {
                address1.setState(0);
                addressDao.updateByPrimaryKeySelective(address1);
            }
        }

        Address address1 = addressDao.selectByPrimaryKey(id);
        if (address1 != null) {
            address1.setState(state);
            address1.setName(name);
            address1.setPhone(phone);
            address1.setAddress(address);
            address1.setAddressdetals(addressdetals);
            address1.setUid(userId);
            addressDao.updateByPrimaryKeySelective(address1);
        }

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Map goodsmyorder(int uid) {
        Map mapover = new HashMap();
        List list1 = new ArrayList();
        List list2 = new ArrayList();
        List list3 = new ArrayList();
        List list4 = new ArrayList();
        //订单id 订单编号 发货状态 商品展示图 商品名称 规格 数量 支付金额
        OrdersExample orderExample = new OrdersExample();
        orderExample.createCriteria().andUidEqualTo(uid);
        orderExample.setOrderByClause("id desc");
        List<Orders> orderList = ordersDao.selectByExample(orderExample);
        for (Orders order : orderList) {
            //订单状态(1.待付款,2.待发货,3.待收货,4.已收货)
            if (order.getType() == 1) {
                //判断未支付过期订单
                Long sec = DateUtils.dateDiff(order.getCreattime(), new Date()) / 1000;
                if (sec > 86400) {
                    //删除该订单
                    ordersDao.deleteByPrimaryKey(order.getId());
                } else {
                    Map map = new HashMap();

                    map.put("price", order.getPrice());
                    map.put("img", ImgEnum.img.getUrl() + order.getImg());
                    map.put("name", order.getGoodsname());
                    map.put("id", order.getId());//订单id
                    map.put("orderNo", order.getOrderno());//订单编号

                    map.put("ordertype", "待付款");
                    list1.add(map);
                }
            } else if (order.getType() == 2) {
                Map map = new HashMap();


                map.put("price", order.getPrice());
                map.put("img", ImgEnum.img.getUrl() + order.getImg());
                map.put("name", order.getGoodsname());
                map.put("id", order.getId());//订单id
                map.put("orderNo", order.getOrderno());//订单编号
                //map.put("img", ImgEnum.img.getUrl() + order.getImg());//展示图
                //map.put("name", order.getName());//商品名称
                //map.put("guige", order.getGuige());//规格
                // map.put("price", order.getPrice().stripTrailingZeros().toPlainString());//总金额
                //map.put("count", order.getShopcount());//购买数量
                //订单状态(1.待付款,2.待发货,3.待收货,4.已收货)
                map.put("ordertype", "待发货");
                list2.add(map);
            } else if (order.getType() == 3) {
                Map map = new HashMap();
                map.put("price", order.getPrice());
                map.put("img", ImgEnum.img.getUrl() + order.getImg());
                map.put("name", order.getGoodsname());
                map.put("id", order.getId());//订单id
                map.put("orderNo", order.getOrderno());//订单编号
                //map.put("img", ImgEnum.img.getUrl() + order.getImg());//展示图
                //map.put("name", order.getName());//商品名称
                //订单状态(1.待付款,2.待发货,3.待收货,4.已收货)
                map.put("ordertype", "待收货");
                list3.add(map);
            } else if (order.getType() == 4) {
                Map map = new HashMap();

                map.put("price", order.getPrice());
                map.put("img", ImgEnum.img.getUrl() + order.getImg());
                map.put("name", order.getGoodsname());
                map.put("id", order.getId());//订单id
                map.put("orderNo", order.getOrderno());//订单编号
                //map.put("img", ImgEnum.img.getUrl() + order.getImg());//展示图
                //map.put("name", order.getName());//商品名称
                //订单状态(1.待付款,2.待发货,3.待收货,4.已收货)
                map.put("ordertype", "已完成");
                list4.add(map);
            }
        }
        mapover.put("list1", list1);
        mapover.put("list2", list2);
        mapover.put("list3", list3);
        mapover.put("list4", list4);
        return mapover;


    }

    @DS("slave")
    @Override
    public Map orderxq(int orderid) {

        Map map = new HashMap();
        Orders order = ordersDao.selectByPrimaryKey(orderid);
        map.put("id", order.getId());
        map.put("orderNo", order.getOrderno());
        //订单状态(1.待付款,2.待发货,3.待收货,4.已完成)
        map.put("ordertype", order.getType());
        if (order.getType() == 1) {
            Date date = order.getCreattime();
            date.setTime(date.getTime() + 24 * 60 * 60 * 1000);
            //支付倒计时
            map.put("second", DateUtils.formatDateTime(DateUtils.dateDiff(new Date(), date) / 1000));
        }
        //收货地址
        map.put("contacts", order.getName());
        map.put("phone", order.getPhone());
        map.put("address", order.getAddress());

        map.put("delivery", order.getDelivery());
        map.put("deliverynum", order.getDeliverynum());

        List list111 = new ArrayList();
        map.put("price", order.getPrice());
        map.put("img", ImgEnum.img.getUrl() + order.getImg());
        map.put("name", order.getGoodsname());
        map.put("commodity", list111);


        map.put("xiadantime", DateUtils.getDateToStr(order.getCreattime(), "yyyy-MM-dd HH:mm:ss"));
        if (order.getPaymenttime() == null) {
            map.put("paymenttime", "");
        } else {
            map.put("paymenttime", DateUtils.getDateToStr(order.getPaymenttime(), "yyyy-MM-dd HH:mm:ss"));
        }
        //map.put("express", order.getExpress());//配送方式
        return map;

    }

    @DS("slave")
    @Override
    public Map<String, Object> myTeam(SysUserParam param) {
        Users user = usersDao.selectByPrimaryKey(param.getLogUserPid());
        Map map = new HashMap();
        int ysmrs = 0;
        int wsmrs = 0;
        //获取该用户直推好友
        List list = new ArrayList();
        UsersExample usersExample = new UsersExample();
        usersExample.createCriteria().andInvitationIdEqualTo(user.getUserId());
        usersExample.setOrderByClause("user_id desc");
        List<Users> usersList = usersDao.selectByExample(usersExample);
        for (Users users : usersList) {
            Map map1 = new HashMap();
            if (users.getRealnametype() == 2) {
                //0.未实名,1.待审核,2已实名
                ysmrs++;
                map1.put("realname", 1);
            } else {
                wsmrs++;
                map1.put("realname", 0);
            }
            map1.put("avatar", ImgEnum.img.getUrl() + users.getHeadPrtraits());//头像
            map1.put("nickname", users.getNickName());//昵称
            map1.put("phone", phoneMask(users.getPhoneNumber()));//昵称
            list.add(map1);
        }
        map.put("tdzrs", list.size());
        map.put("ysmrs", ysmrs);
        map.put("wsmrs", wsmrs);
        map.put("team", list);
        return map;
    }

    @DS("slave")
    @Override
    public List sharelist() {
        List list = new ArrayList();

        List<Chat> chatList = chatDao.selectByExample(new ChatExample());
        for (Chat chat : chatList) {
            Map map = new HashMap();
            map.put("id", chat.getId());
            map.put("img", chat.getImg());
            list.add(map);
        }
        return list;
    }

    @Override
   @Transactional(rollbackFor = Exception.class) public void cancellation(int userId) {
        usersDao.deleteByPrimaryKey(userId);
    }

    /**
     * 检查过期订单并进行处理，将过期未支付的订单状态设置为已取消
     *
     * @param
     * @return 无
     */
    @Override
    public void checkOverDueOrders() {
        log.info("检查过期订单任务开始");
        // 查待付款且已经超过发售截止时间的库内订单
        MyOrderExample ex = new MyOrderExample();
        ex.createCriteria()
          .andIssueIdGreaterThan(0)
          .andOrdertypeEqualTo(0)
          .andEndtimeLessThan(new Date());
        final List<MyOrder> myOrders = myOrderDao.selectByExample(ex);
        log.info("orders are {}", JSON.toJSONString(myOrders));
        log.info("orders are {}", new Date());
        final List<Integer> colIds = myOrders.stream()
                                             .filter(x -> x.getIstype() == 1)
                                             .map(MyOrder::getCollid)
                                             .distinct()
                                             .collect(Collectors.toList());
        final List<Integer> blindBoxIds = myOrders.stream()
                                                  .filter(x -> x.getIstype() != 1)
                                                  .map(MyOrder::getCollid)
                                                  .distinct()
                                                  .collect(Collectors.toList());
        final List<Integer> issueIds = myOrders.stream()
                                               .map(MyOrder::getIssueId)
                                               .filter(issueId -> issueId > 0)
                                               .distinct()
                                               .collect(Collectors.toList());

        final Map<Integer, Collection> id2CollectMap  = getId2CollectMap(colIds);
        final Map<Integer, Blindbox>   id2BlindBoxMap = getId2BlindMap(blindBoxIds);
        final Map<Integer, Issue>      id2IssueMap    = getId2IssueMap(issueIds);


        log.info("需要处理的订单: {}", JSON.toJSONString(myOrders));

        myOrders.forEach(myOrder -> {
                    final Integer issueId = myOrder.getIssueId();
                    final Integer collid  = myOrder.getCollid();
                    final Integer userid  = myOrder.getUserid();
                    if (myOrder.getIstype() == 1) {//collections
                        final Collection collection = id2CollectMap.get(collid);
                        final Issue      issue      = id2IssueMap.get(issueId);
                        if (null == issue) {
                            return;
                        }
                        transactionProcessor.doTransaction(() -> {
                            final int successCount = issueDao.increaseSoldByPrimaryKey(issueId, -1);//返回库存
                            if (successCount == 0) {
                                log.error("更新失败, myOrder = {}", myOrder.getId());
                                throw new CloudException(ExceptionConstant.更新失败);
                            }
                            myOrder.setOrdertype(2);//已取消
                            int i1 = myOrderDao.updateByPrimaryKeySelective(myOrder);
                            if (i1 == 0) {
                                log.error("更新失败, myOrder = {}", myOrder.getId());
                                throw new CloudException(ExceptionConstant.更新失败);
                            }

                            CancelRecord cancelRecord = new CancelRecord();
                            cancelRecord.setUserid(myOrder.getUserid());
                            cancelRecord.setImg(collection.getImg());
                            cancelRecord.setName(collection.getName());
                            cancelRecord.setNo(myOrder.getOrderno());
                            cancelRecord.setPrice(myOrder.getPrice());
                            cancelRecordDao.insertSelective(cancelRecord);

                            //返还用户5积分
                            userService.returnPoints(userid);
                        });

                    } else {// blindboxes

                        final Blindbox blindbox = id2BlindBoxMap.get(collid);
                        final Issue    issue    = id2IssueMap.get(issueId);
                        if (null == issue) {
                            return;
                        }
                        transactionProcessor.doTransaction(() -> {
                            final int successCount = issueDao.increaseSoldByPrimaryKey(issueId, -1);//返回库存
                            if (successCount == 0) {
                                log.error("更新失败, myOrder = {}", myOrder.getId());
                                throw new CloudException(ExceptionConstant.更新失败);
                            }
                            myOrder.setOrdertype(2);//已取消
                            int i1 = myOrderDao.updateByPrimaryKeySelective(myOrder);
                            if (i1 == 0) {
                                log.error("更新失败, myOrder = {}", myOrder.getId());
                                throw new CloudException(ExceptionConstant.更新失败);
                            }
                            CancelRecord cancelRecord = new CancelRecord();
                            cancelRecord.setUserid(myOrder.getUserid());
                            cancelRecord.setImg(blindbox.getImg());
                            cancelRecord.setName(blindbox.getName());
                            cancelRecord.setNo(myOrder.getOrderno());
                            cancelRecord.setPrice(myOrder.getPrice());
                            cancelRecordDao.insertSelective(cancelRecord);

                            //返还用户5积分
                            userService.returnPoints(userid);
                        });

                    }
                });
        log.info("检查过期订单任务结束");

    }

    private Map<Integer, Issue> getId2IssueMap(List<Integer> issueIds) {
        final Map<Integer, Issue> id2IssueMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(issueIds)) {
            IssueExample ex = new IssueExample();
            ex.createCriteria().andIdIn(issueIds);
            final List<Issue> issuese = issueDao.selectByExample(ex);
            final Map<Integer, Issue> m = issuese.stream()
                                                       .collect(Collectors.toMap(Issue::getId,
                                                               Function.identity(), (k1, k2) -> k1));
            id2IssueMap.putAll(m);

        }
        return id2IssueMap;
    }


    // if (myOrder.getOrdertype() == 0) {
            //     if (DateUtils.compareTo(new Date(), myOrder.getEndtime(), DateUtils.DATETIME_FORMAT) == -1) {
            //     //     if (myOrder.getIstype() == 1) {
            //     //         // final Collection collections = id2CollectMap.get(myOrder.getCollid());
            //     //         // if (collections != null) {
            //     //         //     // IssueExample issueExample = new IssueExample();
            //     //         //     // issueExample.createCriteria().andCollidEqualTo(myOrder.getCollid());
            //     //         //     // List<Issue> issues = issueDao.selectByExample(issueExample);
            //     //         //     // if (issues.size() > 0) {
            //     //         //     //     Issue issue = issues.get(0);
            //     //         //     //     int a = 0;
            //     //         //     //     if (issue.getSold() != 0) {
            //     //         //     //         a = issue.getSold() - 1;
            //     //         //     //         if (a > -1) {
            //     //         //     //             issue.setSold(a);
            //     //         //     //             // issueDao.updateByPrimaryKeySelective(issue);
            //     //         //     //             issues2Update.add(issue);
            //     //         //     //         }
            //     //         //     //     }
            //     //         //     // }
            //     //         //     myOrder.setOrdertype(2);
            //     //         //     // myOrderDao.updateByPrimaryKeySelective(myOrder);
            //     //         //     myOrder2Update.add(myOrder);
            //     //         //     CancelRecord cancelRecord = new CancelRecord();
            //     //         //     cancelRecord.setUserid(myOrder.getUserid());
            //     //         //     cancelRecord.setImg(collections.getImg());
            //     //         //     cancelRecord.setName(collections.getName());
            //     //         //     cancelRecord.setNo(myOrder.getOrderno());
            //     //         //     cancelRecord.setPrice(myOrder.getPrice());
            //     //         //     cancelRecord2Update.add(cancelRecord);
            //     //         //     // cancelRecordDao.insertSelective(cancelRecord);
            //     //         // }
    // }


    @Override
   @Transactional(rollbackFor = Exception.class) public void confirmorder(Integer userId, int id) {
        Orders orders = ordersDao.selectByPrimaryKey(id);
        if (userId.equals(orders.getUid())) {
            if (orders.getType() == 3) {
                orders.setType(4);
                ordersDao.updateByPrimaryKeySelective(orders);
            }
        }
    }

    @Override
    public void addPointsByHoldingDays() {
        Date beginDate = DateUtils.getDayBegin();
        List<Collection> collections = this.getCollectionList();
        log.info("collections.size={}", collections.size());
        MineService mineService = (MineService) AopContext.currentProxy();
        for (Collection collection : collections) {
            mineService.handlerAddPointsByCollection(collection, beginDate);
        }
        log.info("end~");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void handlerAddPointsByCollection(Collection collection, Date beginDate) {
        Integer pointsDays = collection.getPointsDays();
        BigDecimal pointsNum = BigDecimal.valueOf(collection.getPointsNum());
        List<UserGrant> userGrants = this.getUserGrantList(collection.getId(), beginDate, pointsDays,
                collection.getId() == Constants.YUANYUZHOU_COLL_ID ? Constants.PLATFORM_USER_ID : null);
        log.info("collectionId {} name {} userGrants.size={}", collection.getId(), collection.getName(), userGrants.size());
        for (UserGrant userGrant : userGrants) {
            Date taskTradeTime = null;
            long roundNum = this.calcRounds(userGrant.getTradeTime(), beginDate, pointsDays);
            if (roundNum > 0) {
                Date tempDate = taskProcessor.getLastTaskTradeTimeByHOLD(userGrant);
                // 防止交易出又交易回后时间变更的情况
                if (tempDate != null && tempDate.after(userGrant.getTradeTime())) {
                    // 根据task存的上次添加积分后的时间(tradeTime + pointsDays), 重新计算添加积分轮数
                    taskTradeTime = tempDate;
                    roundNum = this.calcRounds(taskTradeTime, beginDate, pointsDays);
                }
            }

            if (roundNum <= 0) {
                log.info("userId {} userGrantId {} continue", userGrant.getUserid(), userGrant.getId());
                continue;
            }

            this.addPoints(userGrant, pointsNum, roundNum, pointsDays, taskTradeTime);
        }
        log.info("collectionId {} name {} userGrants.size={} end~", collection.getId(), collection.getName(), userGrants.size());
    }

    /**
     * 获配置了天数和积分的藏品列表
     */
    private List<Collection> getCollectionList() {
        CollectionExample example = new CollectionExample();
        example.createCriteria().andPointsDaysGreaterThan(0).andPointsNumGreaterThan(0);
        return collectionDao.selectByExample(example);
    }

    /**
     * 获取某个藏品下所有需进行处理的用户藏品记录
     * excludeUid: 平台持有场景需排除
     */
    private List<UserGrant> getUserGrantList(Integer collectionId, Date beginDate, Integer pointsDays, Integer excludeUid) {
        LambdaQueryWrapper<UserGrant> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserGrant::getCollid, collectionId);
        queryWrapper.eq(UserGrant::getType, 0);
        queryWrapper.le(UserGrant::getTradeTime, DateUtil.offsetDay(beginDate, -pointsDays));
        if (excludeUid != null) {
            queryWrapper.ne(UserGrant::getUserid, excludeUid);
        }
        return userGrantMapper.selectList(queryWrapper);
    }

    /**
     * 时间差值除以持有天数计算出需添加积分次数
     * @param pointsDays 持有天数
     */
    private long calcRounds(Date tradeTime, Date date, Integer pointsDays) {
        return DateUtil.betweenDay(tradeTime, date, false) / pointsDays;
    }

    /**
     * 添加积分并批量存入记录
     */
    private void addPoints(UserGrant userGrant, BigDecimal pointsNum, long roundNum, Integer pointsDays,
                           Date taskTradeTime) {
        Users user = usersDao.selectByPrimaryKey(userGrant.getUserid());
        log.info("userId {} points {} roundNum {} pointsNum {} taskTradeTime {} collectionId {} userGrantId {}",
                userGrant.getUserid(), user.getMoney(), roundNum, pointsNum, taskTradeTime, userGrant.getCollid(),
                userGrant.getId());
        usersDao.addMoneySafely(pointsNum.multiply(BigDecimal.valueOf(roundNum)), user.getUserId(), user.getMoney());
        taskTradeTime = taskTradeTime == null ? userGrant.getTradeTime() : taskTradeTime;
        BigDecimal currentPoints = user.getMoney();
        for (int i = 1; i <= roundNum; i++) {
            taskTradeTime = DateUtil.offsetDay(taskTradeTime, pointsDays); // 滚动累计
            currentPoints = currentPoints.add(pointsNum);
            taskProcessor.addTask(userGrant.getUserid(),
                    pointsNum,
                    PointsTypeEnum.HOLD,
                    pointsDays, // 设置为配置天数, 滚动天数有歧义且需对照上下文
                    String.valueOf(userGrant.getId()),
                    DateUtil.formatDateTime(taskTradeTime),
                    currentPoints
            );
        }
    }

}
