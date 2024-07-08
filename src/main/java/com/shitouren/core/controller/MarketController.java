package com.shitouren.core.controller;

import com.alipay.api.AlipayApiException;
import com.shitouren.core.aop.Access;
import com.shitouren.core.aop.GetLoginUser;
import com.shitouren.core.aop.Idempotent;
import com.shitouren.core.bean.param.SysUserParam;
import com.shitouren.core.config.interceptor.WebContextHolder;
import com.shitouren.core.model.vo.TrialCalcVO;
import com.shitouren.core.model.vo.SellNFTVO;
import com.shitouren.core.service.CollectionService;
import com.shitouren.core.service.MarketService;
import com.shitouren.core.service.UserGrantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/market")
@Api(value = "市场", tags = "市场")
public class MarketController {

    @Autowired
    MarketService marketService;

    @Autowired
    CollectionService collectionService;
    @Autowired
    UserGrantService userGrantService;

    @GetMapping("/show")
    @ApiOperation("市场")
    @ApiImplicitParam(name = "type", value = "1.最新(由近到晚) 2.最新(由晚到近)3.价格(由低到高)4.价格(由高到低)")
    public List<SellNFTVO> show(@RequestParam Integer type) {
        return marketService.show(WebContextHolder.get(), type);
    }

    @GetMapping("/user/nfts")
    @ApiOperation("自己的nft")
    @ApiImplicitParam(name = "type", value = "1-已上架(包含交易中) 2-待上架")
    public List<SellNFTVO> userMarketNFTs(@RequestParam Integer type) {
        return userGrantService.userMarketNFTs(WebContextHolder.get(), type);
    }

    @GetMapping("/trial-calc-buyer")
    @ApiOperation("买家试算")
    public TrialCalcVO trialCalcBuyer(@RequestParam Integer id) {
        return marketService.trialCalcBuyer(WebContextHolder.get(), id);
    }

    @GetMapping("/trial-calc-seller")
    @ApiOperation("卖家试算")
    public TrialCalcVO trialCalcSeller(@RequestParam Integer id, @RequestParam BigDecimal sellPrice) {
        return marketService.trialCalcSeller(WebContextHolder.get(), id, sellPrice);
    }

    @PostMapping("/up")
    @ApiOperation("上架")
    @Idempotent
    public void marketUp(@RequestParam Integer id, @RequestParam BigDecimal sellPrice) {
        collectionService.marketUp(WebContextHolder.get(), id, sellPrice, null);
    }


    @PostMapping("/down")
    @Access
    @ApiOperation("下架")
    @Idempotent
    public void marketDown(@RequestParam Integer id) {
        collectionService.marketDown(WebContextHolder.get(), id);
    }

    @PostMapping("/buy")
    @Access
    @ApiOperation("购买")
    @Idempotent
    public void buy(@RequestParam Integer id) {
        userGrantService.buyMarket(WebContextHolder.get(), id);
    }


    @PostMapping("/Market/classification")
    //@ApiOperation(value = "分类列表", notes = "")
    @Access
    @GetLoginUser
    public List classification() {
        return marketService.classification();
    }

    @PostMapping("/Market/classificationselect")
    //@ApiOperation(value = "分类查询", notes = "")
    @Access
    @GetLoginUser
    @ApiImplicitParam(name = "id", value = "分类id")
    public List classificationselect(int id) {
        return marketService.classificationselect(id);
    }

    @PostMapping("/Market/search")
    //@ApiOperation(value = "搜索", notes = "")
    @Access
    @GetLoginUser
    @ApiImplicitParam(name = "search", value = "搜索内容")
    public List search(SysUserParam sysUserParam, String search, int type) {
        return marketService.search(sysUserParam.getLogUserPid(), search, type);
    }

    @PostMapping("/Market/details")
    //@ApiOperation(value = "市场藏品详情", notes = "")
    @Access
    @GetLoginUser
    public Map details(SysUserParam sysUserParam, int id) {
        return marketService.details(sysUserParam.getLogUserPid(), id);
    }

    @PostMapping("/Market/Confirmorder")
    //@ApiOperation(value = "确认订单", notes = "")
    @Access
    @GetLoginUser
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id"),
    })
    public int Confirmorder(SysUserParam sysUserParam, int id) {
        return marketService.Confirmorder(sysUserParam.getLogUserPid(), id);
    }

    @PostMapping("/Market/Confirmorderdetails")
    //@ApiOperation(value = "订单详情", notes = "")
    @Access
    @GetLoginUser
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id"),
    })
    public Map Confirmorderdetails(SysUserParam sysUserParam, int id) {
        return marketService.Confirmorderdetails(sysUserParam.getLogUserPid(), id);
    }

    @PostMapping("/Market/Payorder")
    //@ApiOperation(value = "支付订单", notes = "")
    @Access
    @GetLoginUser
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id"),
    })
    public Map Payorder(SysUserParam sysUserParam, int id, int type, String password, int buycard) throws AlipayApiException {
        return marketService.Payorder(sysUserParam.getLogUserPid(), id, type, password, buycard);
    }
}
