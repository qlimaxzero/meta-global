package com.shitouren.core.model.vo;

import lombok.Data;

import java.util.List;


@Data
public class ArchiveTransferVO {

    private List<ArchiveTransferList> archiveList;

    @Data
    public static class ArchiveTransferList {

        private String archiveId;

        private Integer publishCount;

        private List<String> archiveImage;

        private String archiveName;

        private String issueTime;

        private String archiveDescription;

        private String issuer;

        private String issuerDescription;

        private String copyRight;

        private String copyRightDescription;

        private String copyRightAuth;

        private String copyRightAuthDescription;

        private String support;

        private String remark;

        private String introduce;

        private String authorName;

        private String authorDescription;

        private String seriesTitle;

        private String seriesDescription;

        private List<String> seriesImage;

    }


}
