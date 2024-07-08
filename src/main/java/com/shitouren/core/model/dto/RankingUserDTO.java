package com.shitouren.core.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class RankingUserDTO {
    private List<RankingUserDTOList> rankingList;

    @Data
    public static class RankingUserDTOList{

        private Integer rankingNo;

        private String realName;

        private String phone;

        private BigDecimal amount;

        private Integer count;

    }
}
