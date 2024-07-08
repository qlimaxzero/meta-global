package com.shitouren.core.controller.v2;

import com.shitouren.core.aop.LimitType;
import com.shitouren.core.aop.RateLimiter;
import com.shitouren.core.bean.eums.AssetTypeEnum;
import com.shitouren.core.common.DictConst;
import com.shitouren.core.config.interceptor.WebContextHolder;
import com.shitouren.core.model.vo.NFTAvatarVO;
import com.shitouren.core.model.vo.NFTVO;
import com.shitouren.core.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@Api(tags = "新版 - 首页")
@RequestMapping("/v2/index")
@RequiredArgsConstructor
public class IndexController {

    private final DictService dictService;
    private final AssetService assetService;
    private final MiningService miningService;
    private final UserGrantService userGrantService;

    @PostMapping("/mining")
    @ApiOperation("开挖~")
    public void startMining() {
        miningService.startMining(WebContextHolder.get());
    }

    @GetMapping("/config")
    @ApiOperation(value = "获取配置数据")
    public Map<String, String> getConfigData() {
        Map<String, String> map = new HashMap<>();
        map.put("aiWordLimit", dictService.getValue(DictConst.NFT_AI_WORD_LIMIT));
        map.put("aiHealthReduce", dictService.getValue(DictConst.NFT_AI_HEALTH_REDUCE));
        map.put("aiHealthThreshold", dictService.getValue(DictConst.NFT_AI_HEALTH_THRESHOLD));
        map.put("invitationPower1", dictService.getValue(DictConst.INVITATION_POWER_REWARD_PERCENT1));
        map.put("invitationPower2", dictService.getValue(DictConst.INVITATION_POWER_REWARD_PERCENT2));
        map.put("guildCreateFee", dictService.getValue(DictConst.GUILD_CREATE_FEE));
        map.put("guildCreateFeeUnit", AssetTypeEnum.AIC.name());
        map.put("guildLeaderReward", dictService.getValue(DictConst.GUILD_LEADER_REWARD_PERCENT));
        map.put("guildRankingReward", dictService.getValue(DictConst.GUILD_RANKING_REWARD_CONFIG));
        map.put("transferFeeRateAIB", dictService.getValue(DictConst.TRANSFER_FEE_RATE_PREFIX + AssetTypeEnum.AIB));
        map.put("transferFeeRateAIC", dictService.getValue(DictConst.TRANSFER_FEE_RATE_PREFIX + AssetTypeEnum.AIC));
        map.put("exchangeCountDown", dictService.getValue(DictConst.EXCHANGE_COUNTDOWN));
        map.put("healthDefault", dictService.getValue(DictConst.HEALTH_DEFAULT));
        return map;
    }

    @GetMapping("/hold-avatar")
    @ApiOperation(value = "持有的nft头像信息")
    public List<NFTAvatarVO> getHoldAvatar(@RequestParam Integer nftId) {
        return userGrantService.getHoldAvatar(WebContextHolder.get(), nftId);
    }

    @PostMapping("/hold-avatar")
    @ApiOperation(value = "设置持有的nft头像信息")
    public void checkHoldAvatar(@RequestParam Integer nftId, @RequestParam Integer collId) {
        userGrantService.checkHoldAvatar(WebContextHolder.get(), nftId, collId);
    }

    @GetMapping("/ai")
    @ApiOperation(value = "获取首页使用nft ai信息")
    public NFTVO getAI() {
        return userGrantService.getIndexNFT(WebContextHolder.get());
    }

    @PostMapping(value = "/set-role")
    @ApiOperation(value = "设置角色")
    public void setRole(@RequestParam String msg) {
        userGrantService.setRole(WebContextHolder.get(), msg);
    }

    @PostMapping(value = "/dialogue", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ApiOperation(value = "发起会话")
    @RateLimiter(count = 20, limitType = LimitType.IP)
    public SseEmitter dialogue(@RequestParam String msg) {
        return userGrantService.dialogue(WebContextHolder.get(), msg);
    }

    @GetMapping("/red-dot")
    @ApiOperation(value = "获取红点信息")
    public Map<String, Boolean> getRedDot() {
        return assetService.getRedDot(WebContextHolder.get());
    }

}
