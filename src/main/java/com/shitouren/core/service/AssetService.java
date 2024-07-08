package com.shitouren.core.service;


import com.shitouren.core.bean.eums.AssetRecordEnum;
import com.shitouren.core.bean.eums.AssetTypeEnum;
import com.shitouren.core.model.vo.PayVO;
import com.shitouren.core.model.vo.RecordVO;
import com.shitouren.core.model.vo.TrialCalcVO;
import com.shitouren.core.mp.bo.AssetSale;
import com.shitouren.core.mp.bo.AssetUser;
import com.shitouren.core.mp.bo.ExchangeRecord;
import org.apache.commons.lang3.tuple.Triple;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface AssetService {

    List<AssetUser> getUserAssets(Integer uid);

    List<RecordVO> getUserAssetRecords(Integer uid, AssetTypeEnum asset);

    Map<String, List<AssetSale>> getAssetSales();

    /**
     * 挖掘奖励发放至冻结账户
     */
    void miningRewardGrant2Frozen(Integer uid, AssetTypeEnum asset, BigDecimal frozenAmt, AssetRecordEnum recordEnum);

    void modifyUsableAmt(Integer uid, AssetTypeEnum asset, BigDecimal amt, AssetRecordEnum optType, String relatedIds);

    void modifyFrozenAmt(Integer uid, AssetTypeEnum asset, BigDecimal frozenAmt, AssetRecordEnum optType);

    void unfrozenAmtTask();

    PayVO exchangeTrialCalc(Integer uid, Integer saleId, AssetTypeEnum assetName, BigDecimal payAmt);

    PayVO exchangeConfirm(Integer uid, Integer saleId, AssetTypeEnum asset, BigDecimal payAmt);

    Triple<ExchangeRecord, BigDecimal, BigDecimal> deductExchangeStock(Integer uid, Integer saleId, AssetTypeEnum asset, BigDecimal payAmt);

    void exchangeComplete(Integer uid);

    void exchangeQueryTask();

    void handleExchangeComplete(ExchangeRecord record, boolean force);

    List<RecordVO> getUserExchangeRecords(Integer uid);

    RecordVO getUserExchangeDetail(Integer uid, Integer id);

    Map<String, Boolean> getRedDot(Integer uid);

    TrialCalcVO trialCalcTransfer(BigDecimal amt, AssetTypeEnum asset);

    void transfer(Integer uid, Integer receiveUid, BigDecimal amt, AssetTypeEnum asset);

    void exchangeCancel(Integer uid, Integer id);

}
