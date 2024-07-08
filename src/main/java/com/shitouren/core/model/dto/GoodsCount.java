package com.shitouren.core.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class GoodsCount {
    // 档案id数组
    private List<GoodsCountList> archiveList;

    @Data
    public static class GoodsCountList{

        // 档案id
        private String archiveId;

        // 档案下的藏品数量
        private Integer count;

    }
}
