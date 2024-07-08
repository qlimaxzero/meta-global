package com.shitouren.core.service;

import com.alipay.api.AlipayApiException;
import com.shitouren.core.model.vo.TrialCalcVO;
import com.shitouren.core.model.vo.SellNFTVO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface MarketService {
    List<SellNFTVO> show(int userid, int type);

    TrialCalcVO trialCalcBuyer(Integer uid, Integer id);

    TrialCalcVO trialCalcSeller(Integer uid, Integer id, BigDecimal sellPrice);

    List classificationselect(int type);

    List classification();

    List search(int userid, String search, int type);

    Map details(int userid, int id);

    int Confirmorder(int userid, int id);

    Map Confirmorderdetails(int userid, int id);

    Map Payorder(int userid, int id, int type,String pass,int buycard) throws AlipayApiException;

}
