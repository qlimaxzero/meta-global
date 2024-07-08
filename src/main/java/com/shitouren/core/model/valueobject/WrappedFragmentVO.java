package com.shitouren.core.model.valueobject;

import lombok.Data;

import java.io.Serializable;
@Data
public class WrappedFragmentVO implements Serializable {

    private int id;
    private String name;
    private int count;
    private String img;

}
