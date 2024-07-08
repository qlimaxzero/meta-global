package com.shitouren.core.controller;

import cn.hutool.json.JSONObject;
import com.alipay.api.AlipayApiException;
import com.shitouren.core.aop.Access;
import com.shitouren.core.aop.GetLoginUser;
import com.shitouren.core.aop.LimitType;
import com.shitouren.core.aop.RateLimiter;
import com.shitouren.core.autogenerate.bean.*;
import com.shitouren.core.autogenerate.dao.MyOrderDao;
import com.shitouren.core.autogenerate.dao.UserGrantDao;
import com.shitouren.core.autogenerate.dao.YuanyuzhouDao;
import com.shitouren.core.autogenerate.dao.YuanyuzhoushangpinDao;
import com.shitouren.core.bean.param.SysUserParam;
import com.shitouren.core.config.exception.CloudException;
import com.shitouren.core.config.exception.ExceptionConstant;
import com.shitouren.core.config.redis.CloudRedisTemplate;
import com.shitouren.core.model.vo.BlindBoxVO;
import com.shitouren.core.service.DictService;
import com.shitouren.core.service.HomeService;
import com.shitouren.core.service.SynchronizedService;
import com.shitouren.core.utils.DdSpringUtils;
import com.shitouren.core.utils.SignUtils;
import com.shitouren.core.utils.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@Api(value = "首页", tags = "首页")
public class HomeController {

    @Autowired
    HomeService homeService;
    @Autowired
    DictService dictService;
    @Autowired
    SynchronizedService synchronizedService;
    @Autowired
    MyOrderDao myOrderDao;
    @Autowired
    UserGrantDao userGrantDao;
    @Autowired
    YuanyuzhouDao yuanyuzhouDao;
    @Autowired
    YuanyuzhoushangpinDao yuanyuzhoushangpinDao;


    @PostMapping("/Home/shows")
    //@ApiOperation(value = "首页", notes = "")
    @Access(value = false)
    public Map shows() {
        return homeService.show();
    }

    @PostMapping("/Home/show")
    //@ApiOperation(value = "新首页", notes = "")
    @Access
    @GetLoginUser
    public Map shows(SysUserParam sysUserParam, int type) {
        return homeService.getHomePageDetail(sysUserParam.getLogUserPid(), type);
    }

    @PostMapping("/Home/bannerdetails")
    //@ApiOperation(value = "轮播详情", notes = "")
    @Access(value = false)
    public String bannerdetails(int id) {
        return homeService.bannerdetails(id);
    }

    @GetMapping("/Home/getBanners")
    @ApiOperation(value = "轮播", notes = "")
    @Access
    @GetLoginUser
    public List<Map<String, Object>> getBanners() {
        return homeService.getBanners();
    }

    @PostMapping("/Home/blindbox")
    //@ApiOperation(value = "盲盒", notes = "")
    @Access
    @GetLoginUser
    public List<BlindBoxVO> blindbox(SysUserParam sysUserParam) {
        return homeService.blindbox(sysUserParam.getLogUserPid());
    }


    @PostMapping("/Home/nftgood")
    //@ApiOperation(value = "nft藏品", notes = "")
    @Access
    @GetLoginUser
    public Map nftgood(SysUserParam sysUserParam) {
        return homeService.nftgood(sysUserParam.getLogUserPid());
    }

    @PostMapping("/Home/creatergood")
    //@ApiOperation(value = "创作者藏品", notes = "")
    @Access
    @GetLoginUser
    public Map creatergood(SysUserParam sysUserParam) {
        return homeService.creatergood(sysUserParam.getLogUserPid());
    }


    @PostMapping("/Home/calendarl")
    //@ApiOperation(value = "发售日历", notes = "")
    @Access
    public List calendarl() {
        return homeService.calendarl();
    }

    @PostMapping("/Home/calendarls")
    //@ApiOperation(value = "搜索发售日历", notes = "")
    @Access
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "0.数字藏品1.数字盲盒"),
            @ApiImplicitParam(name = "neir", value = "搜索内容")
    })
    public List calendarls(int type, String neir) {
        return homeService.calendarls(type, neir);
    }

    @PostMapping("/Home/details")
    //@ApiOperation(value = "数字藏品详情", notes = "")
    @Access
    @GetLoginUser
    public Map details(SysUserParam sysUserParam, int id) {
        return homeService.details(sysUserParam.getLogUserPid(), id);
    }

    @PostMapping("/Home/viewall")
    //@ApiOperation(value = "查看全部", notes = "")
    @Access
    @GetLoginUser
    public Map viewall(SysUserParam sysUserParam, int id, int StartRow) {
        return homeService.viewall(sysUserParam.getLogUserPid(), id, StartRow);
    }

    @PostMapping("/Home/Confirmorder")
    //@ApiOperation(value = "确认订单", notes = "")
    @Access
    @GetLoginUser
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "藏品id"),
            @ApiImplicitParam(name = "orderno", value = "编号"),
            @ApiImplicitParam(name = "faceToken", value = "faceToken"),
    })
    @RateLimiter(key = "/Home/Confirmorder", time = 1, count = 5, limitType = LimitType.IP)
    public int Confirmorder(SysUserParam sysUserParam, int id, String orderno, String faceToken) {
        return synchronizedService.Confirmorder(sysUserParam.getLogUserPid(), id, orderno, faceToken);
    }

    @PostMapping("/Home/Confirmorder1")
    //@ApiOperation(value = "确认订单带元宇宙", notes = "")
    @Access
    @GetLoginUser
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "藏品id"),
            @ApiImplicitParam(name = "orderno", value = "编号"),
            @ApiImplicitParam(name = "yuanyuzhouid", value = "元宇宙ID"),
    })
    public int Confirmorder1(SysUserParam sysUserParam, int id, String orderno, int yuanyuzhouid, String faceToken) {
        Yuanyuzhou yuanyuzhou = yuanyuzhouDao.selectByPrimaryKey(yuanyuzhouid);
        if (yuanyuzhou.getUserId() != 0) {
            throw new CloudException(ExceptionConstant.元宇宙ID已存在);
        }
        MyOrderExample myOrderExample = new MyOrderExample();
        myOrderExample.createCriteria().andYuanyuzhouidEqualTo(yuanyuzhouid).andOrdertypeEqualTo(0);
        List<MyOrder> myOrderList = myOrderDao.selectByExample(myOrderExample);

        if (!myOrderList.isEmpty()) {
            throw new CloudException(ExceptionConstant.元宇宙ID已存在);
        }


        int myorderid = synchronizedService.Confirmorder(sysUserParam.getLogUserPid(), id, orderno, faceToken);
        MyOrder myOrder = myOrderDao.selectByPrimaryKey(myorderid);
        myOrder.setYuanyuzhouid(yuanyuzhouid);
        myOrderDao.updateByPrimaryKeySelective(myOrder);


        return myorderid;
    }


    @PostMapping("/Home/Confirmorderdetails")
    //@ApiOperation(value = "订单详情", notes = "")
    @Access
    @GetLoginUser
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "订单id"),
    })
    public Map Confirmorderdetails(SysUserParam sysUserParam, int id) {
        return homeService.Confirmorderdetails(sysUserParam.getLogUserPid(), id);
    }

    @PostMapping("/Home/ordertype")
    //@ApiOperation(value = "订单详情", notes = "")
    @Access
    @GetLoginUser
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id"),
    })
    public int ordertype(int id) {
        return homeService.ordertype(id);
    }

    @PostMapping("/Home/Payorder")
    //@ApiOperation(value = "付款", notes = "")
    @Access
    @GetLoginUser
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id"),
            @ApiImplicitParam(name = "type", value = "1.平台2.微信3.支付宝4.其他"),
            @ApiImplicitParam(name = "buycard", value = "0不使用1使用"),
            @ApiImplicitParam(name = "outOrderNo", value = "外部支付订单号"),
    })
    public Map Payorder(SysUserParam sysUserParam, int id, int type, String password, int buycard, String outOrderNo) throws AlipayApiException {
        return homeService.Payorder(sysUserParam.getLogUserPid(), id, type, password, buycard, outOrderNo);
    }

    @PostMapping("/Home/cancelorder")
    @Access
    //@ApiOperation(value = "取消订单", notes = "")
    @GetLoginUser
    public void cancelorder(SysUserParam userParam, int id) {
        homeService.cancelorder(userParam.getLogUserPid(), id);
    }

    @PostMapping("/Home/mintnft")
    //@ApiOperation(value = "发行", notes = "")
    @Access(value = false)
    public void mintnft(int collectionid, int user_grantid) {
        homeService.mintnft(collectionid, user_grantid);
    }

    @PostMapping("/Home/adduser")
   // @ApiOperation(value = "更新用户", notes = "")
    @Access
    @GetLoginUser
    @ApiImplicitParams({
            @ApiImplicitParam(name = "address", value = "地址"),
            @ApiImplicitParam(name = "privateKey", value = "私钥"),
    })
    public void adduser(SysUserParam sysUserParam, String address, String privateKey) {
        homeService.adduser(sysUserParam.getLogUserPid(), address, privateKey);
    }

    @PostMapping("/Home/hqaddress")
    //@ApiOperation(value = "获取地址", notes = "")
    @Access(value = false)
    public Map hqaddress() {
        return homeService.hqaddress();
    }


    @PostMapping("/Home/exchange")
    //@ApiOperation(value = "积分兑换", notes = "")
    @Access
    @GetLoginUser
    @ApiImplicitParams({
            @ApiImplicitParam(name = "number", value = "数量"),

    })
    public void exchange(SysUserParam sysUserParam, int number) {
        homeService.exchange(sysUserParam.getLogUserPid(), number);
    }

    @PostMapping("/Home/agreementss")
    @ApiOperation(value = "协议", notes = "")
    @Access(value = false)
    public String agreementss(int id) {
        return homeService.agreementss(id);
    }


    @PostMapping("/home/goodsdetails")
//    @ApiOperation(value = "商品详情", notes = "\n" +
//            // "collections: 0未收藏1已收藏,\n"+
//            "id: 商品id,\n" +
//            "name: 名称,\n" +
//            "img: 图片,\n" +
//            "price: 价格,\n" +
//            "stock: 库存,\n" +
//            "sold: 已售,\n" +
//            //"content: 详情,\n"+
//            "moreimg: 多图,\n"
//    )
    @GetLoginUser
    @Access
    public Map goodsdetails(SysUserParam sysUserParam, int id) {
        return homeService.goodsdetails(sysUserParam.getLogUserPid(), id);
    }

    @PostMapping("/home/goods11")
//    @ApiOperation(value = "实物商城", notes = "\n" +
//            // "collections: 0未收藏1已收藏,\n"+
//            "id: 商品id,\n" +
//            "name: 名称,\n" +
//            "img: 图片,\n" +
//            "price: 价格,\n" +
//            "stock: 库存,\n" +
//            "sold: 已售,\n" +
//            //"content: 详情,\n"+
//            "moreimg: 多图,\n"
//    )
    @GetLoginUser
    @Access
    public List goods11() {
        return homeService.goods();
    }

    @PostMapping("/home/lottery")
    @GetLoginUser
    //@ApiOperation(value = "抽奖页面")
    public Map lottery(SysUserParam param) {
        return homeService.lottery(param.getLogUserPid());
    }


    @PostMapping("/home/start")
    @GetLoginUser
    //@ApiOperation(value = "开始抽奖")
    public String lotteryStart(SysUserParam param) {
        return homeService.lotteryDraw(param.getLogUserPid());
    }

    //签到页面

    @PostMapping("/home/sign")
    @GetLoginUser
//    @ApiOperation(value = "签到页面", notes = "\n" +
//            // "collections: 0未收藏1已收藏,\n"+
//            "continue: 连续签到,\n" +
//            "award: 明日奖励,\n" +
//            "todaymoney: 今日元气丸,\n" +
//            "money: 总元气丸,\n" +
//            "invitefriend: 邀请好友奖励元气丸,\n" +
//            "maxinviteperday: 每日邀请好友奖励元气丸上线,\n" +
//            "followaccount: 关注奖励,\n" +
//            "followaccounttype: 是否关注0没有1关注,\n" +
//            "buygoods: 购买首发奖励元气丸,\n" +
//            "register: 注册实名奖励元气丸,\n" +
//            "registertype: 是否注册实名0没有1有,,\n" +
//            "todaysign: 今日是否签到0没有1有,,\n"
//
//    )
    public Map sign(SysUserParam param) {
        return homeService.sign(param.getLogUserPid());
    }

    @PostMapping("/home/followaccount")
    @GetLoginUser
    //@ApiOperation(value = "关注公众号")
    public void followaccount(SysUserParam param, String followaccount) {
        homeService.followAccount(param.getLogUserPid(), followaccount);
    }

    @PostMapping("/home/daysign")
    @GetLoginUser
    //@ApiOperation(value = "每日签到")
    public void daySign(SysUserParam param) {
        homeService.daySign(param.getLogUserPid());
    }

    @PostMapping("/home/moneylist")
    @GetLoginUser
    //@ApiOperation(value = "元气币记录")
    public List moneylist(SysUserParam param) {
        return homeService.moneylist(param.getLogUserPid());
    }

    @PostMapping("/home/balancelist")
    @GetLoginUser
    //@ApiOperation(value = "资产记录")
    public List balancelist(SysUserParam param) {
        return homeService.balancelist(param.getLogUserPid());
    }

    @PostMapping("/home/version")
    @Access(value = false)
    //@ApiOperation(value = "版本号")
    public Map version() {
        return homeService.version();
    }


    @PostMapping("/home/classification")
    @Access(value = false)
    //@ApiOperation(value = "首页分类")
    @Cacheable(value = "classification")
    public List classification() {
        return homeService.classification();
    }

    @PostMapping("/home/yuanyuzhou")
    @GetLoginUser
    //@ApiOperation(value = "元宇宙")
    public Map yuanyuzhou(SysUserParam param) {
        return homeService.yuanyuzhou(param.getLogUserPid());
    }

    @PostMapping("/home/yuanyuzhoudetails")
    @GetLoginUser
    //@ApiOperation(value = "元宇宙详情")
    public Map yuanyuzhoudetails(int id) {
        Map rmap = new HashMap();
        Yuanyuzhou yuanyuzhou = yuanyuzhouDao.selectByPrimaryKey(id);

        rmap.put("yuanyuzhou", yuanyuzhou);

        YuanyuzhoushangpinExample yuanyuzhoushangpinExample = new YuanyuzhoushangpinExample();
        yuanyuzhoushangpinExample.createCriteria().andYuanyuzhouidEqualTo(yuanyuzhou.getId());
        List<Yuanyuzhoushangpin> yuanyuzhoushangpins = yuanyuzhoushangpinDao.selectByExample(yuanyuzhoushangpinExample);
        rmap.put("yuanyuzhoushangpin", yuanyuzhoushangpins);
        UserGrantExample userGrantExample = new UserGrantExample();
        userGrantExample.createCriteria().andYuanyuzhouidEqualTo(id);
        List<UserGrant> userGrants = userGrantDao.selectByExample(userGrantExample);
        if (userGrants.size() > 0) {
            rmap.put("userGrant", userGrants.get(0));
        } else {
            rmap.put("userGrant", null);
        }
        return rmap;
    }

    @PostMapping("/home/yuanyuzhouxiugaitupian")
    @GetLoginUser
   // @ApiOperation(value = "元宇宙修改图片")
    public String yuanyuzhouxiugaitupian(SysUserParam sysUserParam, int yuanyuzhouid, String imgurl, String wangzhi) {

        Yuanyuzhou yuanyuzhou = yuanyuzhouDao.selectByPrimaryKey(yuanyuzhouid);
        yuanyuzhou.setUrl(imgurl);
        yuanyuzhou.setWangzhi(wangzhi);
        if (!yuanyuzhou.getUserId().equals(sysUserParam.getLogUserPid())) {
            throw new CloudException(ExceptionConstant.元宇宙ID已存在);
        }
        yuanyuzhouDao.updateByPrimaryKeySelective(yuanyuzhou);
        return "成功";
    }

    @PostMapping("/home/yuanyuzhouxiugaijianjie")
    @GetLoginUser
    //@ApiOperation(value = "元宇宙修改简介")
    public Map yuanyuzhouxiugaijianjie(SysUserParam sysUserParam, int yuanyuzhouid, String jianjie) {

        Yuanyuzhou yuanyuzhou = yuanyuzhouDao.selectByPrimaryKey(yuanyuzhouid);
        yuanyuzhou.setJianjie(jianjie);
        if (!yuanyuzhou.getUserId().equals(sysUserParam.getLogUserPid())) {
            throw new CloudException(ExceptionConstant.元宇宙ID已存在);
        }
        yuanyuzhouDao.updateByPrimaryKeySelective(yuanyuzhou);
        Map rmap = new HashMap();
        rmap.put("jianjie", jianjie);
        return rmap;
    }

    @PostMapping("/home/yuanyuzhouxiugaiurl1")
    @GetLoginUser
    //@ApiOperation(value = "元宇宙修改第二张图")
    public Map yuanyuzhouxiugaiurl1(SysUserParam sysUserParam, int yuanyuzhouid, String url1, int usergrantid) {

        Yuanyuzhou yuanyuzhou = yuanyuzhouDao.selectByPrimaryKey(yuanyuzhouid);
        yuanyuzhou.setUrl1(url1);
        yuanyuzhou.setUsergrantid(usergrantid);
        if (!yuanyuzhou.getUserId().equals(sysUserParam.getLogUserPid())) {
            throw new CloudException(ExceptionConstant.元宇宙ID已存在);
        }
        yuanyuzhouDao.updateByPrimaryKeySelective(yuanyuzhou);
        Map rmap = new HashMap();
        rmap.put("url1", url1);
        return rmap;
    }

    @PostMapping("/home/yuanyuzhouaddshangpin")
    @GetLoginUser
    //@ApiOperation(value = "元宇宙添加商品")
    public String yuanyuzhouaddshangpin(SysUserParam sysUserParam, int yuanyuzhouid, String imgurl, String wangzhi, String name) {

        Yuanyuzhoushangpin yuanyuzhoushangpin = new Yuanyuzhoushangpin();
        yuanyuzhoushangpin.setYuanyuzhouid(yuanyuzhouid);
        yuanyuzhoushangpin.setUrl(imgurl);
        yuanyuzhoushangpin.setWangzhi(wangzhi);
        yuanyuzhoushangpin.setName(name);
        yuanyuzhoushangpin.setAddtime(new Date());
        Yuanyuzhou yuanyuzhou = yuanyuzhouDao.selectByPrimaryKey(yuanyuzhouid);
        if (!yuanyuzhou.getUserId().equals(sysUserParam.getLogUserPid())) {
            throw new CloudException(ExceptionConstant.元宇宙ID已存在);
        }
        yuanyuzhoushangpinDao.insertSelective(yuanyuzhoushangpin);
        return "成功";
    }

    @PostMapping("/home/yuanyuzhoushangpinshanchu")
    @GetLoginUser
    //@ApiOperation(value = "元宇宙商品删除")
    public String yuanyuzhoushangpinshanchu(SysUserParam sysUserParam, int id) {
        Yuanyuzhoushangpin yuanyuzhoushangpin = yuanyuzhoushangpinDao.selectByPrimaryKey(id);
        Yuanyuzhou yuanyuzhou = yuanyuzhouDao.selectByPrimaryKey(yuanyuzhoushangpin.getYuanyuzhouid());
        if (!yuanyuzhou.getUserId().equals(sysUserParam.getLogUserPid())) {
            throw new CloudException(ExceptionConstant.元宇宙ID已存在);
        }
        yuanyuzhoushangpinDao.deleteByPrimaryKey(id);
        return "成功";
    }

    @PostMapping("/home/yuanyuzhouhechai")
    @GetLoginUser
    //@ApiOperation(value = "元宇宙合/拆")
    public String yuanyuzhouhechai(SysUserParam sysUserParam, int yuanyuzhouid, String command) {
        String[] tijiaostr = {"2*2", "3*3", "4*4", "5*5", "6*6", "拆分"};
        if (Arrays.asList(tijiaostr).contains(command) == false) {
            throw new CloudException(ExceptionConstant.元宇宙ID失败);
        }
        Yuanyuzhou yuanyuzhou = yuanyuzhouDao.selectByPrimaryKey(yuanyuzhouid);
        if (!yuanyuzhou.getUserId().equals(sysUserParam.getLogUserPid())) {
            throw new CloudException(ExceptionConstant.元宇宙ID不是您的);
        }
        if (command.equals("拆分")) {
            if (yuanyuzhou.getPid() == 0) {
                throw new CloudException(ExceptionConstant.元宇宙ID不需要拆分);
            }
            YuanyuzhouExample yuanyuzhouExample = new YuanyuzhouExample();
            yuanyuzhouExample.createCriteria().andUserIdEqualTo(yuanyuzhou.getUserId()).andPidEqualTo(yuanyuzhou.getPid());
            List<Yuanyuzhou> yuanyuzhouList = yuanyuzhouDao.selectByExample(yuanyuzhouExample);
            for (int i = 0; i < yuanyuzhouList.size(); i++) {
                Yuanyuzhou yuanyuzhoufor1 = yuanyuzhouList.get(i);
                yuanyuzhoufor1.setPid(0);
                yuanyuzhoufor1.setDaxiao("1*1");
                yuanyuzhouDao.updateByPrimaryKeySelective(yuanyuzhoufor1);
            }
            return "拆分成功";
        }
        int hebingdaxiao = 2;
        if (command.equals("2*2")) {
            hebingdaxiao = 2;
        }
        if (command.equals("3*3")) {
            hebingdaxiao = 3;
        }
        if (command.equals("4*4")) {
            hebingdaxiao = 4;
        }
        if (command.equals("5*5")) {
            hebingdaxiao = 5;
        }
        if (command.equals("6*6")) {
            hebingdaxiao = 6;
        }

        for (int i = 0; i < hebingdaxiao; i++) {
            for (int j = 0; j < hebingdaxiao; j++) {
                int[] xy = {yuanyuzhou.getX() + i, yuanyuzhou.getY() + j};
                YuanyuzhouExample yuanyuzhouExample = new YuanyuzhouExample();
                yuanyuzhouExample.createCriteria().andUserIdEqualTo(yuanyuzhou.getUserId()).andPidEqualTo(0).andXEqualTo(xy[0]).andYEqualTo(xy[1]);
                List<Yuanyuzhou> yuanyuzhouList = yuanyuzhouDao.selectByExample(yuanyuzhouExample);
                if (yuanyuzhouList.size() == 0) {
                    throw new CloudException(ExceptionConstant.元宇宙ID没有符合合并需求);
                }
            }
        }

        for (int i = 0; i < hebingdaxiao; i++) {
            for (int j = 0; j < hebingdaxiao; j++) {
                int[] xy = {yuanyuzhou.getX() + i, yuanyuzhou.getY() + j};
                YuanyuzhouExample yuanyuzhouExample = new YuanyuzhouExample();
                yuanyuzhouExample.createCriteria().andUserIdEqualTo(yuanyuzhou.getUserId()).andPidEqualTo(0).andXEqualTo(xy[0]).andYEqualTo(xy[1]);
                List<Yuanyuzhou> yuanyuzhouList = yuanyuzhouDao.selectByExample(yuanyuzhouExample);
                Yuanyuzhou yuanyuzhouxiugai1 = yuanyuzhouList.get(0);
                yuanyuzhouxiugai1.setPid(yuanyuzhou.getId());
                yuanyuzhouxiugai1.setDaxiao(command);
                yuanyuzhouxiugai1.setIsGoumai(0);
                yuanyuzhouDao.updateByPrimaryKeySelective(yuanyuzhouxiugai1);
            }
        }
        return "成功";
    }

    @PostMapping("/home/getxmcode")
    @GetLoginUser
    //@ApiOperation(value = "元宇宙")
    public Map getxmcode(SysUserParam param) {
        String code = StringUtil.getUUID();

        CloudRedisTemplate cloudRedisTemplate = DdSpringUtils.getBean(CloudRedisTemplate.class);
        cloudRedisTemplate.set(code, param.getLogUserPid(), 60 * 60);

        Map rmap = new HashMap();
        rmap.put("appId", "invokingProperties.getAppId()");
        rmap.put("code", code);
        rmap.put("timestamp", System.currentTimeMillis());
        String jiamicuan = "appId=" + rmap.get("appId") + "&code=" + rmap.get("code") + "&timestamp=" + rmap.get("timestamp");
        rmap.put("sign", SignUtils.getMD5(jiamicuan).toLowerCase());
        return rmap;
    }

    /**
     * 积分兑换白名单
     */
    @PostMapping("/home/exchangeWhiteList")
    @GetLoginUser
    //@ApiOperation(value = "积分兑换白名单")
    public void exchangeWhiteList(SysUserParam param, int number) {
        homeService.exchangeWhiteList(param.getLogUserPid(), number);
    }

    @GetMapping("/home/activity")
    //@ApiOperation(value = "获取活动数据")
    public JSONObject getActivityData() {
        return homeService.getActivityData("activityPopup");
    }

}
