package com.shitouren.core.model.vo.php;

import com.shitouren.core.bean.eums.PointsTypeEnum;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@ApiModel(value = "phpShopPointsReqVO")
public class PhpShopPointsReqVO {

    @NotNull(message = "积分调整类型:SHOP-商城购物;PRODUCT-兑换商品;MERCHANT-商户激励积分")
    private PointsTypeEnum type;

    @NotNull(message = "积分不能为空")
    private BigDecimal points;

    @NotBlank(message = "登录账号:手机号或邮箱")
    private String username;

    /**
     * (SHOP,PRODUCT) : 积分调整唯一标识订单id
     */
    private String otherId;

    private String extend;

}
