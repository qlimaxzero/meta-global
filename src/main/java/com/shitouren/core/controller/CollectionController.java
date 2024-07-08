package com.shitouren.core.controller;

import com.shitouren.core.aop.Access;
import com.shitouren.core.aop.GetLoginUser;
import com.shitouren.core.bean.param.SysUserParam;
import com.shitouren.core.service.CollectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
//@Api(value = "收藏", tags = "收藏")
public class CollectionController {

    @Autowired
    CollectionService collectionService;

    @PostMapping("/Collection/show")
    //@ApiOperation(value = "我的藏品", notes = "")
    @Access
    @GetLoginUser
    public List show(SysUserParam sysUserParam) {
        return collectionService.showColl(sysUserParam.getLogUserPid());
    }

    @PostMapping("/Collection/showinfo")
    //@ApiOperation(value = "叠加藏品详情", notes = "")
    @Access
    @GetLoginUser
    public List showinfo(SysUserParam sysUserParam, int collid) {
        return collectionService.showinfo(sysUserParam.getLogUserPid(), collid);
    }

    @PostMapping("/Collection/search")
    //@ApiOperation(value = "搜索", notes = "")
    @Access
    @GetLoginUser
    @ApiImplicitParam(name = "search", value = "搜索内容")
    public List search(SysUserParam sysUserParam, String search) {
        return collectionService.search(sysUserParam.getLogUserPid(), search);
    }

    @PostMapping("/Collection/details")
    //@ApiOperation(value = "收藏商品详情", notes = "")
    @Access
    @GetLoginUser
    public Map details(SysUserParam sysUserParam, int id) {
        return collectionService.details(sysUserParam.getLogUserPid(),id);
    }

    @PostMapping("/Collection/certificate")
    //@ApiOperation(value = "证书", notes = "")
    @Access
    @GetLoginUser
    public Map certificate(SysUserParam sysUserParam, int id) {
        return collectionService.certificate(sysUserParam.getLogUserPid(), id);
    }

    @PostMapping("/Collection/sellmoney")
    //@ApiOperation(value = "计算价格", notes = "")
    @Access
    @GetLoginUser
    public BigDecimal sellmoney(SysUserParam sysUserParam, BigDecimal money) {
        return collectionService.sellmoney(sysUserParam.getLogUserPid(), money);
    }

    @PostMapping("/Collection/Passon")
    //@ApiOperation(value = "转赠", notes = "")
    @Access
    @GetLoginUser
    public void Passon(SysUserParam sysUserParam, int id, String phone, String pass) {
        collectionService.Passon(sysUserParam.getLogUserPid(), id, phone, pass);
    }

    @PostMapping("/Collection/canshu")
    //@ApiOperation(value = "参数", notes = "")
    @Access(value = false)
    public Map canshu() {
        return collectionService.canshu();
    }


}
