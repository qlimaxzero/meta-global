package com.shitouren.core.controller.v2;


import com.shitouren.core.aop.*;
import com.shitouren.core.config.interceptor.WebContextHolder;
import com.shitouren.core.model.vo.BlindBoxVO;
import com.shitouren.core.model.vo.NFTVO;
import com.shitouren.core.model.vo.RecordNFTVO;
import com.shitouren.core.model.vo.TrialCalcHealthVO;
import com.shitouren.core.service.HomeService;
import com.shitouren.core.service.UserGrantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Slf4j
@RestController
@Api(tags = "新版 - NFT")
@RequestMapping("/v2/nft")
@RequiredArgsConstructor
@Validated
public class NFTController {

    private final UserGrantService userGrantService;
    private final HomeService homeService;

    @PostMapping("/init")
    @ApiOperation("选择性别初始化NFT")
    @ApiImplicitParam(name = "gender", value = "性别 1-男;2-女", required = true)
    public void initNFT(@NotBlank(message = "Nickname cannot be empty") @RequestParam String nickname,
                        @RequestParam Integer gender) {
        userGrantService.initGrantNFT(WebContextHolder.get(), nickname, gender);
    }

    @GetMapping("/trial-calc-restore-health")
    @ApiOperation("恢复健康值试算")
    public TrialCalcHealthVO trialCalcRestoreHealth(@RequestParam(required = false) Integer id) {
        return userGrantService.trialCalcRestoreHealth(WebContextHolder.get(), id, false);
    }

    @PostMapping("/restore-health")
    @ApiOperation("恢复健康值")
    @Idempotent
    public void restoreHealth(@RequestParam(required = false) Integer id) {
        userGrantService.restoreHealth(WebContextHolder.get(), id);
    }

    @PostMapping("/reset-display")
    @ApiOperation("调整展示的NFT")
    @Idempotent
    public void resetDisplay(@RequestParam Integer id) {
        userGrantService.resetDisplay(WebContextHolder.get(), id);
    }

    @GetMapping("/blind-boxes")
    @ApiOperation("盲盒列表")
    public List<BlindBoxVO> blindBoxes() {
        return homeService.blindbox(WebContextHolder.get());
    }

    @PostMapping("/buy")
    @ApiOperation("购买盲盒")
    public NFTVO buy(@RequestParam Integer id) {
        return userGrantService.buyBlindBox(WebContextHolder.get(), id);
    }

    @GetMapping("/records")
    @ApiOperation("NFT记录")
    public List<RecordNFTVO> nftRecords() {
        return userGrantService.getUserNFTRecords(WebContextHolder.get());
    }

}
