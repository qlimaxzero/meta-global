package com.shitouren.core.model.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InviteeVO {

    private List<UserVO> firstLevels = new ArrayList<>();
    private List<UserVO> secondLevels = new ArrayList<>();
    private List<UserVO> invalids = new ArrayList<>();

}
