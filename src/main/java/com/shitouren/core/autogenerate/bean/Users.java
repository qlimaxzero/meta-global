package com.shitouren.core.autogenerate.bean;

import java.math.BigDecimal;
import java.util.Date;

public class Users {
    /**
     * 用户ID从10001自增长
     */
    private Integer userId;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 邮箱
     */
    private String mailbox;

    /**
     * 头像
     */
    private String headPrtraits;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 密码
     */
    private String passwd;

    /**
     * 操作密码
     */
    private String tradePassWord;

    /**
     * 个性签名
     */
    private String autograph;

    /**
     * 我的资产
     */
    private BigDecimal balance;

    /**
     * 支付宝账号
     */
    private String alipay;

    /**
     * 支付宝姓名
     */
    private String alipayname;

    /**
     * 0.未实名1.待审核2.已通过
     */
    private Integer realnametype;

    /**
     * 账号创建时间
     */
    private Date createTime;

    /**
     * 状态：0启用，1禁用
     */
    private String statusId;

    /**
     * 以太坊地址
     */
    private String address;

    /**
     * 以太坊私钥
     */
    private String privatekey;

    /**
     * 优先购券数量
     */
    private Integer whitelist;

    /**
     *
     */
    private String realname;

    /**
     *
     */
    private String realno;

    /**
     *
     */
    private Integer invitationId;

    /**
     *
     */
    private Integer invitationcount;

    /**
     *
     */
    private Integer szcount;

    /**
     *
     */
    private Date sztime;

    /**
     * 0不是创作者1是创作者
     */
    private Integer iscreater;

    /**
     * 元气币
     */
    private BigDecimal money;

    /**
     *
     */
    private String email;

    /**
     *
     */
    private String invitationCode;

    /**
     * 抽奖次数
     */
    private Integer lotterytimes;

    /**
     * 购物券数量
     */
    private Integer buycard;

    /**
     * 0.手机号注册1.邮箱注册
     */
    private Integer ustype;

    /**
     * 元宇宙券数量
     */
    private Integer tudicard;

    /**
     * 健康值
     */
    private BigDecimal health;

    /**
     * 1-有效用户
     */
    private Byte valid;

    private Integer intervalInvitationCount;

    public Integer getIntervalInvitationCount() {
        return intervalInvitationCount;
    }

    public void setIntervalInvitationCount(Integer intervalInvitationCount) {
        this.intervalInvitationCount = intervalInvitationCount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox == null ? null : mailbox.trim();
    }

    public String getHeadPrtraits() {
        return headPrtraits;
    }

    public void setHeadPrtraits(String headPrtraits) {
        this.headPrtraits = headPrtraits == null ? null : headPrtraits.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    public String getTradePassWord() {
        return tradePassWord;
    }

    public void setTradePassWord(String tradePassWord) {
        this.tradePassWord = tradePassWord == null ? null : tradePassWord.trim();
    }

    public String getAutograph() {
        return autograph;
    }

    public void setAutograph(String autograph) {
        this.autograph = autograph == null ? null : autograph.trim();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay == null ? null : alipay.trim();
    }

    public String getAlipayname() {
        return alipayname;
    }

    public void setAlipayname(String alipayname) {
        this.alipayname = alipayname == null ? null : alipayname.trim();
    }

    public Integer getRealnametype() {
        return realnametype;
    }

    public void setRealnametype(Integer realnametype) {
        this.realnametype = realnametype;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId == null ? null : statusId.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPrivatekey() {
        return privatekey;
    }

    public void setPrivatekey(String privatekey) {
        this.privatekey = privatekey == null ? null : privatekey.trim();
    }

    public Integer getWhitelist() {
        return whitelist;
    }

    public void setWhitelist(Integer whitelist) {
        this.whitelist = whitelist;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getRealno() {
        return realno;
    }

    public void setRealno(String realno) {
        this.realno = realno == null ? null : realno.trim();
    }

    public Integer getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(Integer invitationId) {
        this.invitationId = invitationId;
    }

    public Integer getInvitationcount() {
        return invitationcount;
    }

    public void setInvitationcount(Integer invitationcount) {
        this.invitationcount = invitationcount;
    }

    public Integer getSzcount() {
        return szcount;
    }

    public void setSzcount(Integer szcount) {
        this.szcount = szcount;
    }

    public Date getSztime() {
        return sztime;
    }

    public void setSztime(Date sztime) {
        this.sztime = sztime;
    }

    public Integer getIscreater() {
        return iscreater;
    }

    public void setIscreater(Integer iscreater) {
        this.iscreater = iscreater;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode == null ? null : invitationCode.trim();
    }

    public Integer getLotterytimes() {
        return lotterytimes;
    }

    public void setLotterytimes(Integer lotterytimes) {
        this.lotterytimes = lotterytimes;
    }

    public Integer getBuycard() {
        return buycard;
    }

    public void setBuycard(Integer buycard) {
        this.buycard = buycard;
    }

    public Integer getUstype() {
        return ustype;
    }

    public void setUstype(Integer ustype) {
        this.ustype = ustype;
    }

    public Integer getTudicard() {
        return tudicard;
    }

    public void setTudicard(Integer tudicard) {
        this.tudicard = tudicard;
    }

    public BigDecimal getHealth() {
        return health;
    }

    public void setHealth(BigDecimal health) {
        this.health = health;
    }

    public Byte getValid() {
        return valid;
    }

    public void setValid(Byte valid) {
        this.valid = valid;
    }
}
