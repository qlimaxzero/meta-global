package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.BankWithdraw;
import com.shitouren.core.autogenerate.bean.BankWithdrawExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BankWithdrawDao {
    long countByExample(BankWithdrawExample example);

    int deleteByExample(BankWithdrawExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BankWithdraw record);

    int insertSelective(BankWithdraw record);

    List<BankWithdraw> selectByExample(BankWithdrawExample example);

    BankWithdraw selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BankWithdraw record, @Param("example") BankWithdrawExample example);

    int updateByExample(@Param("record") BankWithdraw record, @Param("example") BankWithdrawExample example);

    int updateByPrimaryKeySelective(BankWithdraw record);

    int updateByPrimaryKey(BankWithdraw record);

    int insertBatchSelective(List<BankWithdraw> records);

    int updateBatchByPrimaryKeySelective(List<BankWithdraw> records);
}