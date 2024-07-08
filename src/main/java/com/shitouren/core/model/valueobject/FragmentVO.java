package com.shitouren.core.model.valueobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class FragmentVO implements Serializable {

    @JsonProperty("fragment")
    private Integer fragment;
    @JsonProperty("name")
    private String name;
    @JsonProperty("count")
    private Integer count;
}
