package com.shitouren.core.model.vo;

import com.shitouren.core.autogenerate.bean.UserGrant;
import com.shitouren.core.bean.eums.AssetTypeEnum;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class TrialCalcHealthVO {

    private BigDecimal health;
    private BigDecimal fee;
    private AssetTypeEnum asset;
    private Integer num;
    private List<UserGrant> grants;

}
