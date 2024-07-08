package com.shitouren.core.config.exception;

/**
 * @Autho： 王涛
 * @DATE： 2019/1/19 9:37
 */
@SuppressWarnings("ALL")
public enum ExceptionConstant {


    处理成功("10000", "Successfully"),
    数据异常("399", "Data anomaly"),
    参数异常("400", "Parameter abnormality"),
    登录失效("401", "Login failed or login token is empty"),
    签名错误("402", "Signature error"),
    请求不存在("404", "Request does not exist"),
    账号在其他设备登录("403", "Account login on other devices"),
    退出失败("405", "Exit failed"),
    余额不足("405", "Insufficient balance"),
    余额充足("405", "Sufficient balance, please choose balance payment"),
    不可使用购物券("405", "不可使用购物券！"),
    没有权限访问("407", "No permission to request resources, please contact the administrator"),
    上传失败("408", "Upload failed"),
    请求失败("00001", "Request failed"),
    服务器错误("00002", "Server error"),
    请求错误("00003", "Request error"),
    你已实名("00003", "You have your real name"),
    参数解析错误("00004", "Please check parameter format and type"),
    服务不可用("00006", "Service unavailable"),
    更新失败("00007", "Data has changed, please try again"),
    您的年龄不符合要求("00008", "您的年龄不符合要求"),
    您的身份证有误("00009", "您的身份证有误"),
    重复请求("00010", "Duplicate request, please try again later"),
    金额不对("00011", "Abnormal order amount"),
    网络异常("00012", "Network exception, please try again later"),


    订单号错误("12101", "Order number error"),
    流水号不存在("12102", "Serial number does not exist"),
    订单状态错误("12103", "Order status error"),
    余额更新失败("12104", "Balance update failed"),
    分账参数异常("12105", "Abnormal accounting parameters"),
    订单已存在("12106", "Order already exists"),
    暂无法开启("12107", "Temporarily unable to open"),
    频率太高请稍等("12110", "Access frequency is too high, please wait"),
    该账号正在审核("12107", "This account is currently under review"),
    该账号已实名("12108", "This account is already under real name"),
    资产不足("12109", "Insufficient assets"),
    无可用购物券("12109", "No available shopping vouchers"),
    请先进行实名认证("12004", "Please conduct real name authentication first"),
    缺失人脸验证TOKEN("12005", "Missing facial verification TOKEN, please perform facial verification first"),
    人脸TOKEN验证失败("12006", "Facial TOKEN verification failed, please verify again"),
    无效人脸TOKEN("12006", "Illegal or expired facial token, please revalidate"),
    人脸验证不通过("12006", "Facial verification failed, no orders allowed"),
    人脸验证回调结果为空("12006", "The callback result for facial verification is empty"),
    该账号已实名或正在审核中("12008", "The account is registered or under review"),
    设备IP占用("12013", "Your device IP is already occupied, please register with another device"),
    实名认证失败("12014", "Real name authentication failed"),
    已认证或认证中("12015", "Certified or under certification"),


    原密码错误("19001", "Original password is incorrect"),
    此身份已实名不可重复("13009", "This identity is already a real name and cannot be duplicated"),
    手机号有误("230026", "Wrong phone number"),
    邮箱有误("230052", "Email error"),
    邮件发送失败("230054", "Sending failed, please try again later"),
    抽奖次数不够("230026", "Not enough lucky draws"),
    对方不是你的下级("230027", "The other party is not your subordinate"),
    订单已过期("230028", "Order has expired"),
    订单已支付("230028", "Order paid"),
    订单不存在("230028", "Order does not exist"),
    生成失败("230029", "Generation failed"),
    数量有误("230030", "Quantity error"),
    请先设置操作密码("230031", "Please set the operation password first"),
    操作密码错误("230032", "Operation password error"),
    请先设置支付宝("230033", "Please set Alipay first"),
    条件不足("230033", "Insufficient conditions"),
    价格有误("230034", "Price error"),
    订单状态已改变("230034", "Order status has changed"),
    查无此人("230035", "No such person found"),
    地址错误("230035", "Address error"),
    价格错误("230035", "Price error"),
    此任务已做("230035", "This task has been completed"),
    关注码错误("230035", "Attention code error"),
    请先设置收货地址("230035", "Please set the shipping address first"),
    正在交易无法撤回("230036", "Unable to withdraw while trading"),
    已售完("230037", "Sold out"),
    有未支付订单("230038", "There are unpaid orders"),
    存在支付中订单("230055", "Orders in payment"),
    今日已签到("230038", "Checked in today"),
    认证失败("230039", "Authentication failed"),
    合成暂未开始("230040", "Synthesis has not started yet"),
    本轮合成已结束("230041", "This round of synthesis has ended"),
    碎片未集齐("230042", "Fragments not collected"),
    暂未开放("230043", "Not yet open"),
    暂未发放请稍等("230044", "Not yet distributed, please wait"),
    该藏品是你的无法购买("230045", "This collection is yours that cannot be purchased"),
    藏品已被交易("230046", "The collection has been traded"),
    该藏品已达限购量("230047", "The collection has reached the purchase limit"),
    目标合成物已不存在("230048", "Target compound no longer exists"),
    充值通道未开启("230048", "Recharge channel not open"),
    合成失败("230049", "Synthesis failed"),
    藏品已上架("230049", "Collection has been shelved"),
    一个身份信息只可实名一个账号("230049", "One identity information can only have one real name account"),
    库存不足("230049", "Insufficient inventory"),
    已达限购量("230050", "Purchase limit has been reached"),
    已达当前藏品合成上限("230050", "Reached the current collection synthesis limit"),
    你已经是创作者("230050", "You are already the creator"),
    元气丸不足("230050", "Insufficient Yuanqi Pill"),
    你还未拥有该藏品("230070", "You haven't owned the collection yet"),
    该藏品不存在("230071", "This collection does not exist"),
    该藏品已被使用("230072", "The collection has been used"),
    藏品暂未发放成功("230051", "Collection has not been successfully distributed yet"),
    藏品未处于下架状态("230051", "The collection is not in the delisting state"),
    藏品已下架("230053", "Collection has been taken down"),
    满30提现("230051", "~~"),
    违规操作即将封号("230051", "Illegal operations are about to be banned"),
    白名单已经使用完毕("230051", "Whitelist has been used up"),
    已下架("230051", "Delisted"),
    编号有误订单生成失败("230051", "Order generation failed due to incorrect number"),
    活动已结束("230053", "Activity has ended"),
    暂时无法转赠("230051", "Temporarily unable to transfer"),
    服务器限流("600001", "Access too frequent, please try again later"),

    //---------------------------------账号相关---------------------------------------------//
    获取token失败("110001", "Failed to obtain token, please log in again"),
    发送短信失败("11002", "Sending SMS failed"),
    手机验证码错误("11003", "Verification code error"),
    更换账号("11004", "Change account, please log in again"),
    账号不存在("11005", "Account does not exist"),
    账号或密码错误("11006", "Account or password error"),
    两次密码不一致("11006", "Two passwords are inconsistent"),
    账号已被禁用("11007", "Account Disabled"),
    账号已存在("11008", "Account already exists, can be logged in directly"),
    请前往APP注册或实名("11009", "Please go to the APP to register or use your real name"),
    点赞任务已做完("11010", "This task has already been completed by someone else"),
    密码不能为空("11012", "Password cannot be empty"),
    密码必须包含大小写字母_数字_特殊字符("11013", "Passwords must contain uppercase and lowercase letters, numbers, and special characters"),
    密码错误次数("11014", "You still have %d login opportunities. If you make 5 consecutive errors, your account will be locked for one hour"),
    账户锁定N小时("11015", "Password error 5 times in a row, the account has been locked for one hour"),
    验证码发送次数超限("11016", "The number of times the verification code is sent exceeds the limit, please try again tomorrow"),

    积分不足("10011", "Insufficient points"),
    积分扣除失败("10011", "Points deduction failed"),
    积分返回失败("10011", "Integral return failed"),
    该购物车持有数量已达上限("10012", "The number of holdings in this shopping cart has reached the upper limit"),
    购物车数量不足("10013", "Insufficient number of shopping carts"),
    今日任务已完成("10014", "Today's task has been completed"),
    任务不存在("10015", "This task does not exist"),
    酒滴不足("10016", "酒滴不足"),
    未实名不能兑换("10030", "Unrecognized name cannot be exchanged"),
    该酒坊采购已达上限("10017", "该酒坊采购已达上限"),
    直推活跃未达到("10019", "直推活跃未达到"),
    求购单价区间不符合("10016", "求购单价应符合单价区间"),
    交易密码错误("10018", "交易密码错误"),
    实名未通过("10025", "实名审核未通过"),
    手机号与账号绑定手机号不一致("20026", "手机号与账号绑定手机号不一致"),
    账户名不存在("21005", "Account name does not exist"),
    分账账户名不存在("21006", "Ledger account name does not exist"),
    账户名已存在("21008", "Account name already exists"),
    不能重复签到("20025", "Cannot have duplicate check-in"),
    不能重复关注("20027", "Cannot follow repeatedly"),
    请输入邀请码("10026", "Please enter the invitation code"),
    邀请码不存在("10027", "Invitation code does not exist"),
    已存在邀请人("10028", "Inviting person already exists"),
    邀请码是自己的("10029", "The invitation code belongs to oneself"),

    元宇宙ID已存在("30008", "元宇宙ID已存在"),
    元宇宙ID不是您的("30009", "元宇宙ID不是您的"),
    元宇宙ID不需要拆分("30010", "元宇宙ID不需要拆分"),
    元宇宙ID没有符合合并需求("30011", "元宇宙ID没有符合合并需求"),
    元宇宙ID失败("30012", "元宇宙ID失败"),
    元宇宙ID拆分之后才能转增("30013", "元宇宙场景拆分之后才能转增"),
    元宇宙场景不存在("30014", "元宇宙场景不存在"),
    模块使用位置重复("30015", "模块使用位置重复"),
    请在自己店铺中操作("30016", "请在自己店铺中操作"),
    模块正在元宇宙中使用("30017", "模块正在元宇宙中使用"),

    // pi相关
    授权失败("39001", "授权失败, 请稍后再试"),
    订单完成错误("39002", "订单完成错误"),
    订单确认失败("39003", "订单确认失败"),
    当前π账户已被绑定("39004", "当前π账户已被绑定"),

    MINING_ALREAY("400000", "Signed in"),

    GUILD_ALREAY_EXIST("401000", "The guild already exists"),
    GUILD_NOT_EXIST("401001", "The guild does not exist"),
    GUILD_JOIN_ALREAY("401002", "Joined the guild"),

    NFT_NOT_EXIST("402000", "NFT does not exist"),
    NFT_IN_USE("402001", "NFT in use"),
    NFT_HEALTH_LACK("402002", "Insufficient NFT health value"),
    NFT_IS_OWN("402003", "NFT is own"),
    NFT_DELISTED("402004", "The NFT has been delisted"),
    NFT_CANNOT_UP("402005", "The gifted NFT cannot be listed"),
    NFT_HEALTH_FULL("402006", "No need to restore health values"),

    ASSET_SOLD_OUT("403000", "Sold out"),
    ASSET_INSUFFICIENT("403001", "Insufficient exchange quantity"),
    ASSET_QUOTA_LIMIT("403002", "The exchange quantity is too large"),
    ASSET_TRANSFER_SELF("403003", "Cannot transfer to oneself"),

    EXCHANGE_ADJUST_AMT("404000", "Please adjust the exchange amount"),
    ;


    ExceptionConstant(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String code;
    private String msg;

}
