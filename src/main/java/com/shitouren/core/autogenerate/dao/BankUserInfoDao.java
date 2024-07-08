package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.BankUserInfo;
import com.shitouren.core.autogenerate.bean.BankUserInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BankUserInfoDao {
    long countByExample(BankUserInfoExample example);

    int deleteByExample(BankUserInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BankUserInfo record);

    int insertSelective(BankUserInfo record);

    List<BankUserInfo> selectByExample(BankUserInfoExample example);

    BankUserInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BankUserInfo record, @Param("example") BankUserInfoExample example);

    int updateByExample(@Param("record") BankUserInfo record, @Param("example") BankUserInfoExample example);

    int updateByPrimaryKeySelective(BankUserInfo record);

    int updateByPrimaryKey(BankUserInfo record);

    int insertBatchSelective(List<BankUserInfo> records);

    int updateBatchByPrimaryKeySelective(List<BankUserInfo> records);
}