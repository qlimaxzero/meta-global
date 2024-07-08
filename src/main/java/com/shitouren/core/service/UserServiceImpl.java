package com.shitouren.core.service;


import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.StrUtil;
import com.shitouren.core.autogenerate.bean.Users;
import com.shitouren.core.autogenerate.bean.UsersExample;
import com.shitouren.core.autogenerate.dao.*;
import com.shitouren.core.bean.ContractServiceBean;
import com.shitouren.core.bean.eums.EumUser;
import com.shitouren.core.bean.eums.ImgEnum;
import com.shitouren.core.bean.eums.PointsTypeEnum;
import com.shitouren.core.bean.param.SysUserParam;
import com.shitouren.core.bean.param.user.NewUserLoginParam;
import com.shitouren.core.bean.param.user.UserLoginParam;
import com.shitouren.core.bean.param.user.UserRegisterParam;
import com.shitouren.core.bean.param.user.UserRestPwdParam;
import com.shitouren.core.common.Constants;
import com.shitouren.core.config.exception.CloudException;
import com.shitouren.core.config.exception.ExceptionConstant;
import com.shitouren.core.config.redis.CloudRedisTemplate;
import com.shitouren.core.constant.BusinessConstant;
import com.shitouren.core.model.vo.InviteeVO;
import com.shitouren.core.model.vo.UserVO;
import com.shitouren.core.mp.mapper.UserGrantMapper;
import com.shitouren.core.service.processor.TaskProcessor;
import com.shitouren.core.utils.*;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Autho： 王涛
 * @DATE： 2020/8/1 21:53
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    UsersDao usersDao;
    @Autowired(required = false)
    GiveUserDao giveUserDao;
    @Autowired(required = false)
    BlindboxDao blindboxDao;
    @Autowired(required = false)
    CollectionDao collectionDao;
    @Autowired(required = false)
    MyboxDao myboxDao;
    @Autowired(required = false)
    UserGrantDao userGrantDao;
    @Autowired(required = false)
    PullnewDao pullnewDao;
    @Autowired
    HomeService homeService;
    @Autowired
    DictService dictService;
    @Autowired
    private CloudRedisTemplate cloudRedisTemplate;
    @Resource
    private ContractServiceBean contractServiceBean;
    @Resource
    private TaskProcessor taskProcessor;
    @Resource
    private UserGrantMapper userGrantMapper;

    @Override
    public Users userLogin(UserLoginParam param) {
        Users user = this.getUser(param.getUserAccount());
        if (user == null) {
            throw new CloudException(ExceptionConstant.账号不存在);
        }
        if (cloudRedisTemplate.get(BusinessConstant.LOGIN_TIME_KEY + param.getUserAccount()) != null && (int) cloudRedisTemplate.get(BusinessConstant.LOGIN_TIME_KEY + param.getUserAccount()) == 5) {
            throw new CloudException(ExceptionConstant.账户锁定N小时);
        }

        if (!user.getPasswd().equals(MD5Util.MD5Encode(param.getUserPassword()))) {
            //限制密码输入错误次数
            if (cloudRedisTemplate.get(BusinessConstant.LOGIN_TIME_KEY + param.getUserAccount()) == null) {
                cloudRedisTemplate.set(BusinessConstant.LOGIN_TIME_KEY + param.getUserAccount(), 1, 60 * 60);

            } else {
                cloudRedisTemplate.incr(BusinessConstant.LOGIN_TIME_KEY + param.getUserAccount(), 1);
                cloudRedisTemplate.expire(BusinessConstant.LOGIN_TIME_KEY + param.getUserAccount(), 60 * 60);
            }
            int loginTime = cloudRedisTemplate.get(BusinessConstant.LOGIN_TIME_KEY + param.getUserAccount());
            throw new CloudException(ExceptionConstant.密码错误次数.getCode(), String.format(ExceptionConstant.密码错误次数.getMsg(), 5 - loginTime));
        } else {
            cloudRedisTemplate.delete(BusinessConstant.LOGIN_TIME_KEY + param.getUserAccount());
        }

        if (EumUser.UserStatus.禁用.getValue() == Integer.parseInt(user.getStatusId())) {
            throw new CloudException(ExceptionConstant.账号已被禁用);
        }

        return user;
    }

    @Override
    public Users getUserById(int UserId) {
        Users user = usersDao.selectByPrimaryKey(UserId);
        if (user == null) {
            throw new CloudException(ExceptionConstant.账号不存在);
        }
        if (Integer.parseInt(user.getStatusId()) == 1) {
            throw new CloudException(ExceptionConstant.账号已被禁用);
        }
        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void userRegister(UserRegisterParam param) {
        if (param.getState() == 0) {
            //校验手机号是否有效
            if (!StringUtil.isEmpty(param.getPhone()) && !param.getPhone().matches("^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$")) {
                throw new CloudException(ExceptionConstant.手机号有误);
            }
        } else {
            //校验邮箱是否有效
            if (!StringUtil.isEmpty(param.getPhone()) && !param.getPhone().matches("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+$")) {
                throw new CloudException(ExceptionConstant.邮箱有误);
            }
        }

        //校验密码是否为空
        if (!StringUtil.isValidStr(param.getPassword()) || !StringUtil.isValidStr(param.getPassword2())) {
            throw new CloudException(ExceptionConstant.密码不能为空);
        }

        //校验密码
        if (!param.getPassword().equals(param.getPassword2())) {
            throw new CloudException(ExceptionConstant.两次密码不一致);
        }

        //校验密码是否包含大小写字母、数字、特殊字符
        if (!param.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[~!@#$%^&*()_+`\\-={}\\[\\]:\";'<>?,.\\/]).{8,16}$")) {
            throw new CloudException(ExceptionConstant.密码必须包含大小写字母_数字_特殊字符);
        }


        //验证码校验
        String loginCodekey = EumUser.CellVerifyCodeType.注册.getValue() + param.getPhone();
        String str = cloudRedisTemplate.get(loginCodekey);
        if (!StringUtil.isValidStr(str) || !param.getCode().equals(str)) {
            throw new CloudException(ExceptionConstant.手机验证码错误);
        }

        //删除已用验证码
        cloudRedisTemplate.delete(loginCodekey);

        AssertUtil.isNull(this.getUser(param.getPhone()), ExceptionConstant.账号已存在);
        /**
         * 注册用户
         */
        Users user = new Users();
        String filename = RandomStringUtils.randomAlphanumeric(6);
        user.setNickName(filename);//昵称
        if (param.getState() == 0) {
            user.setPhoneNumber(param.getPhone());//手机号
        } else {
            user.setMailbox(param.getPhone());//邮箱
        }
        user.setUstype(param.getState());
        user.setPasswd(MD5Util.MD5Encode(param.getPassword()));//密码
        user.setCreateTime(new Date());//创建时间
        user.setHeadPrtraits(Constants.AVATAR_DEFAULT);
        //user.setHealth(new BigDecimal(dictService.getValue(DictConst.HEALTH_DEFAULT)));
        String code = createCode();
//        String stringdata = "name=" + code;
//        String data = HttpRequest.sendPost(contractServiceBean.getAddress(), stringdata);
//        if (StrUtil.isNotBlank(data)) {
//            JSONObject jsonObject = JSONObject.fromObject(data);
//            String account = jsonObject.get("account").toString();
//            user.setAddress(account);
//        }
        user.setAddress(param.getAddress());
        user.setInvitationCode(code);
        if (StringUtil.isValidStr(param.getUid())) {
            UsersExample usersExample1 = new UsersExample();
            usersExample1.createCriteria().andInvitationCodeEqualTo(param.getUid());
            List<Users> users = usersDao.selectByExample(usersExample1);
            if (!users.isEmpty()) {
                Integer inviterId = users.get(0).getUserId();
                user.setInvitationId(inviterId);
                usersDao.addInvitationRewardsForOneTime(BigDecimal.ZERO, inviterId);
            }
        }
        usersDao.insertSelective(user);//创建用户
    }

    public static String getRandomString(int length) {
        String str = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(10);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void userRestPwd(UserRestPwdParam param) {
        //校验密码是否为空
        if (!StringUtil.isValidStr(param.getPassword()) || !StringUtil.isValidStr(param.getPassword2())) {
            throw new CloudException(ExceptionConstant.密码不能为空);
        }

        //校验密码
        if (!param.getPassword().equals(param.getPassword2())) {
            throw new CloudException(ExceptionConstant.两次密码不一致);
        }

        //校验密码是否包含大小写字母、数字、特殊字符
        if (!param.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[~!@#$%^&*()_+`\\-={}\\[\\]:\";'<>?,.\\/]).{8,16}$")) {
            throw new CloudException(ExceptionConstant.密码必须包含大小写字母_数字_特殊字符);
        }

        //验证码校验
        String loginCodekey = EumUser.CellVerifyCodeType.忘记密码.getValue() + param.getPhone();
        String str = cloudRedisTemplate.get(loginCodekey);
        if (!StringUtil.isValidStr(str) || !param.getCode().equals(str)) {
            throw new CloudException(ExceptionConstant.手机验证码错误);
        }
        //删除已用验证码
        cloudRedisTemplate.delete(loginCodekey);
        UsersExample query = new UsersExample();
        UsersExample.Criteria criteria = query.createCriteria();
        if (param.getState() == 1) {
            criteria.andMailboxEqualTo(param.getPhone());
        } else {
            criteria.andPhoneNumberEqualTo(param.getPhone());
        }
        List<Users> userList = usersDao.selectByExample(query);
        if (userList.isEmpty()) {
            throw new CloudException(ExceptionConstant.账号不存在);
        }
        Users user = userList.get(0);
        user.setPasswd(MD5Util.MD5Encode(param.getPassword()));
        usersDao.updateByPrimaryKeySelective(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateAvatar(Integer userId, String avatar) {
        Users user = usersDao.selectByPrimaryKey(userId);
        user.setHeadPrtraits(avatar);
        usersDao.updateByPrimaryKeySelective(user);
    }

    @Override
    public void createimg() {
        for (int i = 10000001; i < 10000121; i++) {
            String fileName = "InvQRCode" + i + ".jpg";
            File QrCodeFile = new File(ImgEnum.QrCode.getPath() + fileName);//生成图片位置
            String url = "http:///#/?registerCode=" + i;
            boolean falg = QRcode.createQRCode(QrCodeFile, 200, 200, url);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Users codeuserLogin(NewUserLoginParam param) {
        UsersExample query = new UsersExample();
        query.createCriteria().andPhoneNumberEqualTo(param.getUserAccount());
        List<Users> userList = usersDao.selectByExample(query);
        //验证码校验
        String loginCodekey = EumUser.CellVerifyCodeType.注册.getValue() + param.getUserAccount();
        String str = cloudRedisTemplate.get(loginCodekey);
        if (!StringUtil.isValidStr(str) || !param.getCode().equals(str)) {
            throw new CloudException(ExceptionConstant.手机验证码错误);
        }
        //删除已用验证码
        cloudRedisTemplate.delete(loginCodekey);

        if (userList.isEmpty()) {
            //throw new CloudException(ExceptionConstant.账号不存在);
            if (StringUtil.getLength(param.getUserAccount()) < 11) {
                throw new CloudException(ExceptionConstant.手机号有误);
            }
            Users user = new Users();
            String filename = RandomStringUtils.randomAlphanumeric(6);
            user.setNickName("藏家" + filename);//昵称
            user.setPhoneNumber(param.getUserAccount());//手机号
            user.setCreateTime(new Date());//创建时间

            String code = createCode();
            //Map map=homeService.hqaddress();
            String stringdata = "name=" + code;
            String data = HttpRequest.sendPost(contractServiceBean.getAddress(), stringdata);
            JSONObject jsonObject = JSONObject.fromObject(data);
            String account = jsonObject.get("account").toString();
            user.setAddress(account);
            //dictService.getValue("privateKey")
            //user.setPrivatekey(map.get("privateKey").toString());//密码
            // user.setAddress(map.get("address").toString());//创建时间
            usersDao.insertSelective(user);//创建用户
            userList = usersDao.selectByExample(query);
            return userList.get(0);
        } else {
            Users user = userList.get(0);
            return user;
        }
    }

    @Override
    public Users getUser(String username) {
        if (StrUtil.isBlank(username)) {
            return null;
        }
        UsersExample example = new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();
        if (username.contains(StrUtil.AT)) {
            criteria.andMailboxEqualTo(username);
        } else {
            criteria.andPhoneNumberEqualTo(username);
        }
        List<Users> users = usersDao.selectByExample(example);
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public int updateUserByCAS(Users users, BigDecimal oldMoney, BigDecimal oldBalance) {
        UsersExample example = new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria().andUserIdEqualTo(users.getUserId());
        if (oldMoney != null) {
            criteria.andMoneyEqualTo(oldMoney);
        }
        if (oldBalance != null) {
            criteria.andBalanceEqualTo(oldBalance);
        }
        return usersDao.updateByExampleSelective(users, example);
    }

    @Override
    public List<Map<String, Object>> selectColumnsByUidList(String columns, List<Integer> uidList) {
        return usersDao.selectColumnsByUidList(columns, uidList);
    }

    @Override
    public int updateHealthByCAS(Integer userid, BigDecimal newHealth, BigDecimal oldHealth) {
        Users users = new Users();
        users.setUserId(userid);
        users.setHealth(newHealth);

        UsersExample example = new UsersExample();
        example.createCriteria().andUserIdEqualTo(userid).andHealthEqualTo(oldHealth);
        return usersDao.updateByExampleSelective(users, example);
    }

    @Override
    public List<Integer> selectInviteeByUidList(List<Integer> uidList, List<Integer> existsUidList) {
        if (uidList == null || uidList.isEmpty() || existsUidList == null || existsUidList.isEmpty()) {
            return ListUtil.empty();
        }
        UsersExample example = new UsersExample();
        example.createCriteria().andInvitationIdIn(uidList).andUserIdIn(existsUidList);
        List<Users> users = usersDao.selectByExample(example);
        return users.stream().map(Users::getUserId).collect(Collectors.toList());
    }

    @Override
    public List<Integer> selectInviteeByUidList(List<Integer> uidList) {
        if (uidList == null || uidList.isEmpty()) {
            return ListUtil.empty();
        }
        UsersExample example = new UsersExample();
        example.createCriteria().andInvitationIdIn(uidList);
        List<Users> users = usersDao.selectByExample(example);
        return users.stream().map(Users::getUserId).collect(Collectors.toList());
    }

    @Override
    public List<Integer> filterValidUid(List<Integer> uidList) {
        if (uidList == null || uidList.isEmpty()) {
            return ListUtil.empty();
        }
        UsersExample example = new UsersExample();
        example.createCriteria().andUserIdIn(uidList).andValidEqualTo(Constants.USER_VALID);
        List<Users> users = usersDao.selectByExample(example);
        return users.stream().map(Users::getUserId).collect(Collectors.toList());
    }

    public String createCode() {
        String filename = "";
        for (int i = 0; i < 100000; i++) {
            //随机字符串
            filename = RandomStringUtils.randomAlphanumeric(8);
            UsersExample usersExample = new UsersExample();
            usersExample.createCriteria().andInvitationCodeEqualTo(filename);
            long count = usersDao.countByExample(usersExample);
            if (count == 0L) {
                break;
            }
        }
        return filename;
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
    @Transactional(rollbackFor = Exception.class)
    public void updateTradePassWord(SysUserParam sysUserParam, String userAccount, String phone, String code, String newTradePassWord, String newTradePassWord2) {
        Users user = usersDao.selectByPrimaryKey(sysUserParam.getLogUserPid());
        UsersExample query = new UsersExample();
        query.createCriteria().andPhoneNumberEqualTo(userAccount);
        List<Users> userList = usersDao.selectByExample(query);
        if (userList.isEmpty()) {
            throw new CloudException(ExceptionConstant.账户名不存在);
        }
        if (!newTradePassWord.equals(newTradePassWord2)) {
            throw new CloudException(ExceptionConstant.两次密码不一致);
        }
        if (!phone.equals(user.getPhoneNumber())) {
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

    @Transactional(rollbackFor = Exception.class)
    public void updateNickname(Integer userId, String nickname) {
        if (StrUtil.isBlank(nickname)) {
            return;
        }
        Users user = usersDao.selectByPrimaryKey(userId);
        user.setNickName(nickname.trim());
        usersDao.updateByPrimaryKeySelective(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public void returnPoints(Integer userId) {
        int neededScore = Integer.parseInt(dictService.getValue("NEEDED_SCORE"));
        if (neededScore > 0) {
            Users users = usersDao.selectByPrimaryKey(userId);
            BigDecimal returnScore = new BigDecimal(dictService.getValue("RETURN_SCORE"));
            int i = usersDao.updateScoreByPrimaryKey(userId, returnScore);
            taskProcessor.addTask(userId, returnScore, PointsTypeEnum.BUY_COLL, users.getMoney().add(returnScore));
            if (i == 0) {
                throw new CloudException(ExceptionConstant.积分返回失败);
            }
        }
    }

    @Override
    public InviteeVO getInviteeByUid(Integer uid) {
        InviteeVO vo = new InviteeVO();
        List<Users> firstUsers = selectListByInvitationId(ListUtil.of(uid));
        if (firstUsers.isEmpty()) {
            return vo;
        }

        Map<Byte, List<Users>> firstValidMap = firstUsers.stream().collect(Collectors.groupingBy(Users::getValid));
        vo.setFirstLevels(firstValidMap.getOrDefault(Constants.USER_VALID, ListUtil.empty())
                .stream()
                .map(x -> UserVO.builder().uid(x.getUserId()).nickname(x.getNickName()).power(userGrantMapper.sumTotalPower(x.getUserId())).build())
                .collect(Collectors.toList()));

        List<Integer> firstUidList = ListUtil.toList(firstUsers.stream().map(Users::getUserId).collect(Collectors.toList()));
        List<Users> secondUsers = selectListByInvitationId(firstUidList);
        Map<Byte, List<Users>> secondValidMap = secondUsers.stream().collect(Collectors.groupingBy(Users::getValid));
        vo.setSecondLevels(secondValidMap.getOrDefault(Constants.USER_VALID, ListUtil.empty())
                .stream()
                .map(x -> UserVO.builder().uid(x.getUserId()).nickname(x.getNickName()).power(userGrantMapper.sumTotalPower(x.getUserId())).build())
                .collect(Collectors.toList()));

        List<Users> invalids = firstValidMap.getOrDefault(Constants.USER_INVALID, new ArrayList<>());
        invalids.addAll(secondValidMap.getOrDefault(Constants.USER_INVALID, ListUtil.empty()));
        vo.setInvalids(invalids.stream()
                .map(x -> UserVO.builder().uid(x.getUserId()).nickname(x.getNickName()).power(BigDecimal.ZERO).build())
                .collect(Collectors.toList()));
        return vo;
    }

    @Override
    public void updateValidById(Integer uid, Byte userValid) {
        Users users = getUserById(uid);
        if (Objects.equals(userValid, users.getValid())) {
            return;
        }
        users.setValid(userValid);
        usersDao.updateByPrimaryKey(users);
    }

    private List<Users> selectListByInvitationId(List<Integer> uidList) {
        UsersExample example = new UsersExample();
        example.createCriteria().andInvitationIdIn(uidList);
        return usersDao.selectByExample(example);
    }

}
