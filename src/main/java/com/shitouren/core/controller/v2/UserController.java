package com.shitouren.core.controller.v2;


import com.google.common.collect.ImmutableMap;
import com.shitouren.core.autogenerate.bean.Users;
import com.shitouren.core.config.exception.ExceptionConstant;
import com.shitouren.core.config.interceptor.WebContextHolder;
import com.shitouren.core.model.dto.MiningDTO;
import com.shitouren.core.model.vo.GuildVO;
import com.shitouren.core.model.vo.InviteeVO;
import com.shitouren.core.model.vo.NFTVO;
import com.shitouren.core.mp.bo.AssetUser;
import com.shitouren.core.service.*;
import com.shitouren.core.utils.AssertUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController("v2UserController")
@Api(tags = "新版 - 用户")
@RequestMapping("/v2/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AssetService assetService;
    private final MiningService miningService;
    private final GuildService guildService;
    private final UserGrantService userGrantService;

    @GetMapping("/nfts")
    @ApiOperation("NFT列表")
    public List<NFTVO> userNFTs() {
        return userGrantService.userNFTs(WebContextHolder.get());
    }

    @GetMapping("/guild")
    @ApiOperation("公会信息")
    public GuildVO guild() {
        return guildService.myGuild(WebContextHolder.get());
    }

    @GetMapping("/invitees")
    @ApiOperation("受邀者信息")
    public InviteeVO invitees() {
        return userService.getInviteeByUid(WebContextHolder.get());
    }

    @GetMapping("/power")
    @ApiOperation("个人算力相关")
    public Map<String, MiningDTO> power() {
        return miningService.getUserCurrentMiningAsset(WebContextHolder.get());
    }

    @GetMapping("/asset")
    @ApiOperation("个人资产")
    public List<AssetUser> assets() {
        return assetService.getUserAssets(WebContextHolder.get());
    }

    @GetMapping("/get")
    @ApiOperation("用户信息")
    public Map<String, String> getByUid(Integer uid) {
        Users user = userService.getUserById(uid);
        AssertUtil.notNull(user, ExceptionConstant.账号不存在);
        return ImmutableMap.of(
                "avatar", user.getHeadPrtraits(),
                "nickName", user.getNickName()
        );
    }

}
