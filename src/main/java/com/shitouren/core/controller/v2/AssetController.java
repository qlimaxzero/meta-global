package com.shitouren.core.controller.v2;

import com.shitouren.core.aop.LimitType;
import com.shitouren.core.aop.RateLimiter;
import com.shitouren.core.bean.eums.AssetTypeEnum;
import com.shitouren.core.config.interceptor.WebContextHolder;
import com.shitouren.core.model.vo.PayVO;
import com.shitouren.core.model.vo.RecordVO;
import com.shitouren.core.model.vo.TrialCalcVO;
import com.shitouren.core.mp.bo.AssetSale;
import com.shitouren.core.service.AssetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@Api(tags = "新版 - 资产相关")
@RequestMapping("/v2/asset")
@RequiredArgsConstructor
@Validated
public class AssetController {

    private final AssetService assetService;

    @GetMapping("/sales")
    @ApiOperation("出售列表")
    public Map<String, List<AssetSale>> sales() {
        return assetService.getAssetSales();
    }

    @PostMapping("/trial-calc-exchange")
    @ApiOperation("兑换试算")
    public PayVO exchangeTrialCalc(@NotNull(message = "Id cannot be empty") @RequestParam Integer saleId,
                                 @NotNull(message = "Asset name cannot be null") @RequestParam AssetTypeEnum asset,
                                 @NotNull(message = "The amount cannot be empty") @RequestParam BigDecimal payAmt) {
        return assetService.exchangeTrialCalc(WebContextHolder.get(), saleId, asset, payAmt);
    }

    @PostMapping("/exchange-confirm")
    @ApiOperation("确认兑换")
    public PayVO exchangeConfirm(@NotNull(message = "Id cannot be empty") @RequestParam Integer saleId,
                                 @NotNull(message = "Asset name cannot be null") @RequestParam AssetTypeEnum asset,
                                 @NotNull(message = "The amount cannot be empty") @RequestParam BigDecimal payAmt
                                 //@NotBlank(message = "The address cannot be empty")@RequestParam(required = false) String fromAddr
                                 ) {
        return assetService.exchangeConfirm(WebContextHolder.get(), saleId, asset, payAmt);
    }

    @PostMapping("/exchange-complete")
    @ApiOperation("兑换完成")
    public void exchangeComplete() {
        assetService.exchangeComplete(WebContextHolder.get());
    }

    @PostMapping("/exchange-cancel")
    @ApiOperation("取消兑换")
    public void exchangeCancel(@NotNull(message = "Id cannot be empty") @RequestParam Integer id) {
        assetService.exchangeCancel(WebContextHolder.get(), id);
    }

    @GetMapping("/exchange-records")
    @ApiOperation("兑换记录")
    public List<RecordVO> exchangeRecord() {
        return assetService.getUserExchangeRecords(WebContextHolder.get());
    }

    @GetMapping("/exchange-detail")
    @ApiOperation("兑换详情")
    public RecordVO exchangeDetail(@NotNull(message = "Id cannot be empty") @RequestParam Integer id) {
        return assetService.getUserExchangeDetail(WebContextHolder.get(), id);
    }

    @GetMapping("/records")
    @ApiOperation("资产记录")
    public List<RecordVO> assetRecords(@RequestParam(required = false) AssetTypeEnum asset) {
        return assetService.getUserAssetRecords(WebContextHolder.get(), asset);
    }

    @GetMapping("/trial-calc-transfer")
    @ApiOperation("转赠试算")
    public TrialCalcVO trialCalcTransfer(@RequestParam BigDecimal amt, @RequestParam AssetTypeEnum asset) {
        return assetService.trialCalcTransfer(amt, asset);
    }

    @PostMapping("/transfer")
    @ApiOperation("转赠")
    public void transfer(@RequestParam Integer receiveUid, @RequestParam BigDecimal amt, @RequestParam AssetTypeEnum asset) {
        assetService.transfer(WebContextHolder.get(), receiveUid, amt, asset);
    }

}
