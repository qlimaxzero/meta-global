package com.shitouren.core.service;

import cn.hutool.core.lang.Pair;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shitouren.core.autogenerate.bean.UserGrant;
import com.shitouren.core.model.dto.NFTHealthDTO;
import com.shitouren.core.model.vo.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.math.BigDecimal;
import java.util.List;

public interface UserGrantService extends IService<UserGrant> {

    int countInitNFT(Integer uid);

    BigDecimal sumTotalPower(Integer uid);

    /**
     * L: AIB
     * R: AIC
     */
    Pair<BigDecimal, BigDecimal> sumPowerByAsset(Integer uid, boolean isTotal);

    void initGrantNFT(Integer uid, String nickname, Integer gender);

    List<NFTVO> userNFTs(Integer uid);

    List<SellNFTVO> userMarketNFTs(Integer uid, Integer type);

    List<UserGrant> selectList(Integer uid);

    TrialCalcHealthVO trialCalcRestoreHealth(Integer uid, Integer id, boolean isInner);

    void restoreHealth(Integer uid, Integer id);

    void resetDisplay(Integer uid, Integer id);

    boolean updateHealthByCAS(Integer id, BigDecimal newHealth, BigDecimal oldHealth);

    void buyMarket(Integer uid, Integer id);

    NFTVO buyBlindBox(Integer uid, Integer id);

    NFTVO getIndexNFT(Integer uid);

    void setRole(Integer uid, String msg);

    SseEmitter dialogue(Integer uid, String msg);

    NFTHealthDTO deductAIHealth(Integer uid, String question, String answer);

    void updateHistoryDialogueRecord(Integer uid, Integer nftId);

    List<RecordNFTVO> getUserNFTRecords(Integer uid);

    List<NFTAvatarVO> getHoldAvatar(Integer uid, Integer nftId);

    void checkHoldAvatar(Integer uid, Integer nftId, Integer collId);

    void airdropQueryTask(int sec);

}
