package com.shitouren.core.service;

import java.math.BigDecimal;

public interface DictService {
    String getValue(String code);

    String getValue(String code, String defaultValue);

    BigDecimal getDecimalValue(String code);

    void setValue(String code, String value);

}
