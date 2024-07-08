package com.shitouren.core.controller;

import com.alipay.api.AlipayApiException;
import com.shitouren.core.aop.Access;
import com.shitouren.core.aop.GetLoginUser;
import com.shitouren.core.bean.eums.EumUser;
import com.shitouren.core.bean.param.RealNameParam;
import com.shitouren.core.bean.param.SysUserParam;
import com.shitouren.core.config.exception.CloudException;
import com.shitouren.core.config.exception.ExceptionConstant;
import com.shitouren.core.config.redis.CloudRedisTemplate;
import com.shitouren.core.service.DictService;
import com.shitouren.core.service.MineService;
import com.shitouren.core.service.SynchronizedService;
import com.shitouren.core.utils.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@Api(value = "我的", tags = "我的")
public class MineController {
    @Autowired
    MineService mineService;
    @Autowired
    DictService dictService;
    @Autowired
    SynchronizedService synchronizedService;
    @Autowired
    private CloudRedisTemplate cloudRedisTemplate;

    @PostMapping("/mine/mine")
    @ApiOperation(value = "我的信息", notes = "\n" +
            "avatar: 头像\n" +
            "nickname: 昵称\n" +
            "money: 元气丸,\n" +
            "isInit: 是否获得初始化nft 1-已获得;0-未获得,\n" +
            "isMining: 是否挖掘中:1-正在挖,\n" +
            "")
    @GetLoginUser
    @Access
    public Map<String, Object> mine(SysUserParam sysUserParam) {
        Integer userId = sysUserParam.getLogUserPid();
        return mineService.getMineInfo(userId);
    }

    @PostMapping("/Home/message")
    @GetLoginUser
    //@ApiOperation("消息")
    //@Cacheable("message")
    public List message() {
        return mineService.message();
    }

    @PostMapping("/Home/messagedetails")
    @GetLoginUser
    //@ApiOperation("消息详情")
    public Map messagedetails(int id) {
        return mineService.messagedetails(id);
    }

    @PostMapping("/mine/realname")
    //@ApiOperation("实名认证")
    @GetLoginUser
    @Access
    public Map addRealName(SysUserParam userParam) {
        return mineService.realname(userParam.getLogUserPid());
    }

//    @PostMapping("/mine/addrealname")
//    @ApiOperation("实名认证")
//    @GetLoginUser
//    @Access
    public void addRealName(SysUserParam userParam, RealNameParam realNameParam) {
        Integer userId = userParam.getLogUserPid();
        mineService.addRealName(realNameParam, userId, false);
    }

    @PostMapping("/mine/Acsecurity")
    //@ApiOperation(value = "账号安全", notes = "\n" +
//            "phone: 手机号,\n" +
//            "alipay: 支付宝账号\n" +
//            "isRealName: 0.未认证1.已认证\n" +
//            "")
    @GetLoginUser
    @Access
    public Map<String, Object> Acsecurity(SysUserParam sysUserParam) {
        return mineService.Acsecurity(sysUserParam.getLogUserPid());
    }

    @PostMapping("/mine/usedPhone")
    //@ApiOperation(value = "旧手机号验证", notes = "")
    @GetLoginUser
    @Access
    //@ApiImplicitParam(name = "code", value = "验证码")
    public void usedPhone(SysUserParam sysUserParam, String code) {
        mineService.usedPhone(sysUserParam.getLogUserPid(), code);
    }

    @PostMapping("/mine/updPhone")
//    @ApiOperation(value = "更换新手机号", notes = "")
    @GetLoginUser
    @Access
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "新手机号"),
            @ApiImplicitParam(name = "code", value = "验证码")
    })
    public void updPhone(SysUserParam sysUserParam, String phone, String code) {
        mineService.updPhone(sysUserParam.getLogUserPid(), phone, code);
    }

    @PostMapping("/mine/aplPhone")
    //@ApiOperation(value = "更换支付宝", notes = "")
    @GetLoginUser
    @Access
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "支付宝账号"),
            @ApiImplicitParam(name = "name", value = "支付宝姓名"),
            @ApiImplicitParam(name = "code", value = "验证码")
    })
    public void aplPhone(SysUserParam sysUserParam, String phone, String name, String code) {
        mineService.aplPhone(sysUserParam.getLogUserPid(), phone, name, code);
    }

    @PostMapping("/mine/addtradepassword")
    //@ApiOperation("设置操作密码")
    @GetLoginUser
    @Access
    public void addtradepassword(SysUserParam sysUserParam, String TradePassWord) {
        mineService.addtradepassword(sysUserParam, TradePassWord);
    }

    @PostMapping("/mine/updatetradepassword")
    //@ApiOperation("修改操作密码")
    @GetLoginUser
    @Access
    public void updateTradePassWord(SysUserParam sysUserParam, String phone, String code, String newTradePassWord, String newTradePassWord2) {
        mineService.updateTradePassWord(sysUserParam, phone, code, newTradePassWord, newTradePassWord2);
    }

    @PostMapping("/mine/setup")
    //@ApiOperation(value = "设置", notes = "进入提示操作密码：0.未设置1.已设置")
    @GetLoginUser
    @Access
    public String setup(SysUserParam sysUserParam) {
        return mineService.setup(sysUserParam.getLogUserPid());
    }

    @PostMapping("/mine/personal")
//    @ApiO/*peration(value = "个人信息", notes = "" +
//            "avatar: 头像\n" +
//            "nickname: 昵称,\n" +
//            "autograph: 个性签名,\n" +
//            "email: 个性签名,\n" +
//            "")*/
    @GetLoginUser
    @Access
    public Map personal(SysUserParam sysUserParam) {
        return mineService.personal(sysUserParam.getLogUserPid());
    }

    @PostMapping("/mine/updateUserInfo")
    @ApiOperation("修改个人信息")
    @GetLoginUser
    @Access
    @ApiImplicitParams({
            @ApiImplicitParam(name = "avatar", value = "头像"),
            @ApiImplicitParam(name = "nickname", value = "昵称"),
            @ApiImplicitParam(name = "autograph", value = "个性签名"),
            @ApiImplicitParam(name = "email", value = "邮箱"),
            @ApiImplicitParam(name = "code", value = "验证码"),
            @ApiImplicitParam(name = "invitationCode", value = "邀请码"),
    })
    public void updateUserInfo(SysUserParam sysUserParam, String avatar, String nickname, String autograph,
                               String email, String code, String invitationCode) {
        //验证码校验
        String phone = null;
        if (!StringUtil.isEmpty(phone)) {
            String loginCodekey = EumUser.CellVerifyCodeType.校验手机号.getValue() + phone;
            String str = cloudRedisTemplate.get(loginCodekey);
            if (!StringUtil.isValidStr(str) || !code.equals(str)) {
                throw new CloudException(ExceptionConstant.手机验证码错误);
            }
            //删除已用验证码
            cloudRedisTemplate.delete(loginCodekey);
        }
        //验证码校验
        if (!StringUtil.isEmpty(email)) {
            String loginCodekey = EumUser.CellVerifyCodeType.校验手机号.getValue() + email;
            String str = cloudRedisTemplate.get(loginCodekey);
            if (!StringUtil.isValidStr(str) || !code.equals(str)) {
                throw new CloudException(ExceptionConstant.手机验证码错误);
            }
            //删除已用验证码
            cloudRedisTemplate.delete(loginCodekey);
        }
        mineService.updateUserInfo(sysUserParam.getLogUserPid(), avatar, nickname, autograph, email, phone, invitationCode);
    }

    @PostMapping("/mine/Myassets")
    @Access
    //@ApiOperation(value = "我的资产", notes = "")
    @GetLoginUser
    public Map Myassets(SysUserParam userParam) {
        return mineService.Myassets(userParam.getLogUserPid());
    }

    @PostMapping("/mine/withdrawal")
    @Access
    //@ApiOperation(value = "提现", notes = "")
    @GetLoginUser
    @ApiImplicitParams({
            @ApiImplicitParam(name = "count", value = "提现金额"),
            @ApiImplicitParam(name = "pass", value = "操作密码"),
    })
    public void withdrawal(SysUserParam userParam, BigDecimal count, String pass) {
//        mineService.withdrawal(userParam.getLogUserPid(), count, pass);
        synchronizedService.withdrawal(userParam.getLogUserPid(), count, pass);
    }

    @PostMapping("/mine/withdrawalrecode")
    @Access
    //@ApiOperation(value = "提现记录", notes = "state0正在审核1审核通过2审核失败")
    @GetLoginUser
    public List withdrawalrecode(SysUserParam userParam) {
        return mineService.withdrawalrecode(userParam.getLogUserPid());
    }

    @PostMapping("/mine/balancerecords")
    @Access
   //@ApiOperation(value = "资金记录", notes = "")
    @GetLoginUser
    public List balancerecords(SysUserParam userParam) {
        return mineService.getBalanceRecords(userParam.getLogUserPid());
    }

    @PostMapping("/mine/Collectionrecords")
    @Access
    //@ApiOperation(value = "藏品记录", notes = "")
    @GetLoginUser
    public List Collectionrecords(SysUserParam userParam) {
        return mineService.collectionRecords(userParam.getLogUserPid());
    }

    @PostMapping("/mine/myblindboxs")
    @Access
//    @ApiOperation(value = "我的盲盒", notes = "")
    @GetLoginUser
    public List myblindboxs(SysUserParam userParam) {
        return mineService.myblindboxs(userParam.getLogUserPid());
    }

    @PostMapping("/mine/openboxdetails")
    @Access
    //@ApiOperation(value = "开盲盒详情", notes = "")
    @GetLoginUser
    public Map openboxdetails(SysUserParam userParam, int id) {
        return mineService.openboxdetails(id);
    }

    @PostMapping("/mine/openbox")
    @Access
    //@ApiOperation(value = "开盲盒", notes = "")
    @GetLoginUser
    public Map openbox(SysUserParam userParam, int id, String no) {
//        return mineService.openbox(userParam.getLogUserPid(), id, no);
        return synchronizedService.openbox(userParam.getLogUserPid(), id, no);
    }

    @PostMapping("/mine/openboxrecord")
    @Access
    //@ApiOperation(value = "开启记录", notes = "")
    @GetLoginUser
    public List openboxrecord(SysUserParam userParam) {
        return mineService.openBoxRecord(userParam.getLogUserPid());
    }

    @PostMapping("/mine/SyntheticCollection")
    @Access
    //@ApiOperation(value = "合成藏品", notes = "")
    @GetLoginUser
    public List SyntheticCollection(SysUserParam userParam) {
        return mineService.SyntheticCollection(userParam.getLogUserPid());
    }

    @PostMapping("/mine/synthesis")
    @Access
    //@ApiOperation(value = "合成", notes = "")
    @GetLoginUser
    public Map synthesis(SysUserParam userParam, int id) {
//        return mineService.synthesis(userParam.getLogUserPid(), id);
        return synchronizedService.synthesis(userParam.getLogUserPid(), id);
    }

    @PostMapping("/mine/synthesisprize")
    @Access
    //@ApiOperation(value = "开始合成", notes = "")
    @GetLoginUser
    public Map synthesisprize(SysUserParam userParam, int id, String no) {
        return mineService.synthesisprize(userParam.getLogUserPid(), id, no);
    }

    @PostMapping("/mine/synthesisrecord")
    @Access
    //@ApiOperation(value = "合成记录", notes = "")
    @GetLoginUser
    public List synthesisrecord(SysUserParam userParam) {
        return mineService.synthesisrecord(userParam.getLogUserPid());
    }

    @PostMapping("/mine/myorder")
    @Access
    //@ApiOperation(value = "我的订单", notes = "")
    @GetLoginUser
    @ApiImplicitParam(name = "type", value = "0.全部1.待付款2.已付款3.已取消")
    public List myorder(SysUserParam userParam, int type) {
        return mineService.myorder(userParam.getLogUserPid(), type);
    }

    @PostMapping("/mine/scorder")
    @Access
    //@ApiOperation(value = "市场订单", notes = "")
    @GetLoginUser
    @ApiImplicitParam(name = "type", value = "0.全部1.待付款2.已付款3.已取消")
    public List scorder(SysUserParam userParam, int type) {
        return mineService.scorder(userParam.getLogUserPid(), type);
    }

    @PostMapping("/mine/cancelorder")
    @Access
    //@ApiOperation(value = "取消我的订单", notes = "")
    @GetLoginUser
    public void cancelorder(SysUserParam userParam, int id) {
        mineService.cancelorder(userParam.getLogUserPid(), id);
    }

    @PostMapping("/mine/cancelscorder")
    @Access
    //@ApiOperation(value = "取消市场订单", notes = "")
    @GetLoginUser
    public void cancelscorder(SysUserParam userParam, int id) {
        mineService.cancelscorder(userParam.getLogUserPid(), id);
    }

    @PostMapping("/mine/inviteInfo")
    @ApiOperation("获取邀请信息")
    @GetLoginUser
    @Access
    public Map<String, Object> inviteInfo(SysUserParam userParam) {
        return mineService.getInviteInfo(userParam);
    }

    @PostMapping("/mine/inviteInfos")
    //@ApiOperation("获取邀请图")
    @GetLoginUser
    @Access
    public String inviteInfos(SysUserParam userParam, int id) {
        return mineService.inviteInfoimgs(userParam.getLogUserPid(), id);
    }

    @PostMapping("/mine/sharelist")
    @ApiOperation("分享图片")
    @GetLoginUser
    @Access
    public List sharelist() {
        return mineService.sharelist();
    }

    @PostMapping("/mine/chat")
    //@ApiOperation("客服")
    @GetLoginUser
    @Access
    public String chat(SysUserParam userParam) {
        return mineService.chat();
    }

    @PostMapping("/mine/quota")
    //@ApiOperation(value = "充值额度", notes = "")
    @GetLoginUser
    @Access
    public List quota(SysUserParam userParam) {
        return mineService.tixian(userParam.getLogUserPid());
    }

    @PostMapping("/mine/Recharge")
    @Access
    //@ApiOperation(value = "充值余额", notes = "")
    @GetLoginUser
    @ApiImplicitParams({
            @ApiImplicitParam(name = "count", value = "充值金额"),
            @ApiImplicitParam(name = "type", value = "类型1支付宝"),
    })
    public String Recharge(SysUserParam userParam, BigDecimal count, int type) throws AlipayApiException {
        return mineService.Recharge(userParam.getLogUserPid(), count, type);
    }

    /**
     *
     * @param rankType pan 0.总榜 1.周榜 2.月榜
     * @return list
     */
    @PostMapping("/mine/Ranking")
    @Access
    //@ApiOperation(value = "排行榜", notes = "")
    @GetLoginUser
    public List Ranking(@RequestParam(defaultValue = "0") Integer rankType) {
        return mineService.Ranking(rankType);
    }


    @PostMapping("/mine/address")
//    @ApiOperation(value = "收货地址", notes = "\n" +
//            "id: 地址id,\n" +
//            "name: 收货人姓名\n" +
//            "phone: 收货人电话\n" +
//            "address: 地址\n" +
//            "addressdetals: 详细地址\n" +
//            "state: 0正常1默认\n")
    @GetLoginUser
    @Access
    public List address(SysUserParam sysUserParam) {
        Integer userId = sysUserParam.getLogUserPid();
        return mineService.address(userId);
    }

    @PostMapping("/mine/deladdress")
//    @ApiOperation(value = "删除收货地址", notes = "\n")
    @GetLoginUser
    @Access
    public void deladdress(SysUserParam sysUserParam, int id) {
        Integer userId = sysUserParam.getLogUserPid();
        mineService.deladdress(userId, id);
    }

    @PostMapping("/mine/newaddress")
//    @ApiOperation(value = "新增收货地址", notes = "\n")
    @GetLoginUser
    @Access
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "收货人姓名"),
            @ApiImplicitParam(name = "phone", value = "收货人电话"),
            @ApiImplicitParam(name = "address", value = "地址"),
            @ApiImplicitParam(name = "addressdetals", value = "地址详情"),
            @ApiImplicitParam(name = "state", value = "0正常1默认"),
    })
    public void newaddress(SysUserParam sysUserParam, String name, String phone, String address, String addressdetals, int state) {
        Integer userId = sysUserParam.getLogUserPid();
        mineService.newaddress(userId, name, phone, address, addressdetals, state);
    }

    @PostMapping("/mine/editaddress")
//    @ApiOperation(value = "修改收货地址", notes = "\n")
    @GetLoginUser
    @Access
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "收货人姓名"),
            @ApiImplicitParam(name = "phone", value = "收货人电话"),
            @ApiImplicitParam(name = "address", value = "地址"),
            @ApiImplicitParam(name = "addressdetals", value = "地址详情"),
            @ApiImplicitParam(name = "state", value = "0正常1默认"),
            @ApiImplicitParam(name = "id", value = "地址id"),
    })
    public void editaddress(SysUserParam sysUserParam, String name, String phone, String address, String addressdetals, int state, int id) {
        Integer userId = sysUserParam.getLogUserPid();
        mineService.editaddress(userId, name, phone, address, addressdetals, state, id);
    }

    @PostMapping("/mine/defaultaddress")
//    @ApiOperation(value = "收货地址设置为默认/取消默认", notes = "\n")
    @GetLoginUser
    @Access
    @ApiImplicitParam(name = "id", value = "地址id")
    public void defaultaddress(SysUserParam sysUserParam, int id) {
        Integer userId = sysUserParam.getLogUserPid();
        mineService.defaultaddress(userId, id);
    }


    @PostMapping("/mine/confirmorder")
//    @ApiOperation(value = "确认收货(id 订单id)", notes = "\n")
    @GetLoginUser
    @Access
    public void confirmorder(SysUserParam sysUserParam, int id) {
        Integer userId = sysUserParam.getLogUserPid();
        mineService.confirmorder(userId, id);
    }

    @PostMapping("/mine/cancellation")
    //@ApiOperation(value = "注销", notes = "\n")
    @GetLoginUser
    @Access
    public void cancellation(SysUserParam sysUserParam) {
        Integer userId = sysUserParam.getLogUserPid();
        mineService.cancellation(userId);
    }


    @PostMapping("/mine/goodsmyorder")
    @Access
//    @ApiOperation(value = "我的订单", notes = "" +
//            "list1:[{待付款\n" +
//            "id:订单id\n" +
//            "orderNo:订单编号\n" +
//            "ordertype:状态\n" +
//            "totalmoney:实际付款总额\n" +
//            "commodity:订单编号\n" +
//            "[{\n" +
//            "img:图片\n" +
//            "price:价格\n" +
//            "name:名称\n" +
//            "num:购买数量\n" +
//            "specification:规格\n" +
//            "}]\n" +
//            "}]\n" +
//            "list4:[已完成]\n" +
//            "list3:[待收货]\n" +
//            "list2:[待发货]")
    @GetLoginUser
    public Map goodsmyorder(SysUserParam userParam) {
        return mineService.goodsmyorder(userParam.getLogUserPid());
    }

    @PostMapping("/mine/orderdetails")
//    @ApiOperation(value = "订单详情", notes = "\n" +
//            "img:图片\n" +
//            "orderNo:订单编号\n" +
//            "address:收货地址\n" +
//            "xiadantime:下单时间\n" +
//            "paymenttime:支付时间\n" +
//            "phone:电话\n" +
//            "id:id\n" +
//            "contacts:联系人\n" +
//            "totalmoney:实际付款总额\n" +
//            "delivery:配送方式\n" +
//            "deliverynum:配送单号\n" +
//            "coupon:优惠券金额\n" +
//            "second:倒计时(待付款状态才有)\n" +
//            "commodity:订单编号\n" +
//            "[{\n" +
//            "img:图片\n" +
//            "price:价格\n" +
//            "name:名称\n" +
//            "num:购买数量\n" +
//            "specification:规格\n" +
//            "}]\n" +
//
//            "type:订单状态(1.待付款,2.待发货,3.待收货,4.已完成)\n")
    @GetLoginUser
    @ApiImplicitParam(name = "orderid", value = "订单Id")
    public Map orderdetails(int orderid) {
        return mineService.orderxq(orderid);
    }

    @PostMapping("/mine/myTaem")
//    @ApiOperation(value = "我的团队", notes = " " +
//            "tdzrs:团队总人数" +
//            "ysmrs:已实名人数" +
//            "wsmrs:未实名人数" +
//            "team: [团队列表" +
//            "  {" +
//            "    nickname: 昵称" +
//            "    avatar: 头像" +
//            "    phone: 电话" +
//            "    realname: 0.未实名1.已实名" +
//            "  }" +
//            "],")
    @GetLoginUser
    @Access
    public Map<String, Object> myTaem(SysUserParam userParam) {
        return mineService.myTeam(userParam);
    }

}
