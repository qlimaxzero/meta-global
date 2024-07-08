package com.shitouren.core.service;

import cn.hutool.core.util.StrUtil;
import com.shitouren.core.autogenerate.bean.Dict;
import com.shitouren.core.autogenerate.bean.DictExample;
import com.shitouren.core.autogenerate.dao.DictDao;
import com.shitouren.core.utils.DecimalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Slf4j
@Service
public class DictServiceImpl implements DictService {
    @Autowired(required = false)
    DictDao dictDao;

    @Cacheable(value = "getValue", key = "#code")
    public String getValue(String code) {
        log.info("get value code = {}", code);
        DictExample dictExample = new DictExample();
        dictExample.createCriteria().andCodeEqualTo(code);
        List<Dict> dictList = dictDao.selectByExample(dictExample);
        return dictList.isEmpty() ? null : dictList.get(0).getValue();
    }

    @Override
    public String getValue(String code, String defaultValue) {
        String value = ((DictService) AopContext.currentProxy()).getValue(code);
        return StrUtil.isBlank(value) ? defaultValue : value;
    }

    @Override
    public BigDecimal getDecimalValue(String code) {
        String value = ((DictService) AopContext.currentProxy()).getValue(code);
        return DecimalUtil.of(value);
    }

    public void setValue(String code, String value) {
        DictExample dictExample = new DictExample();
        dictExample.createCriteria().andCodeEqualTo(code);
        Dict dict = dictDao.selectByExample(dictExample).get(0);
        dict.setValue(value);
        dictDao.updateByPrimaryKeySelective(dict);
    }
}
