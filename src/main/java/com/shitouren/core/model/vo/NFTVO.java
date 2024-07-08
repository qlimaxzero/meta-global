package com.shitouren.core.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class NFTVO {

    private Integer id;
    private String avatarUrl;
    private String imgUrl;
    private String no;
    private String name;
    private BigDecimal health;
    @ApiModelProperty(value = "1-为首页展示, 默认第一个")
    private Byte display;
    private Integer wordLimit;
    private Integer remainingWordCount;
    @ApiModelProperty(value = "抽中的NFT数量")
    private Integer num;

    private List<DialogueRecordVO> dialogueList;

}
