package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.DealRecord;
import com.shitouren.core.autogenerate.bean.DealRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DealRecordDao {
    long countByExample(DealRecordExample example);

    int deleteByExample(DealRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DealRecord record);

    int insertSelective(DealRecord record);

    List<DealRecord> selectByExample(DealRecordExample example);

    DealRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DealRecord record, @Param("example") DealRecordExample example);

    int updateByExample(@Param("record") DealRecord record, @Param("example") DealRecordExample example);

    int updateByPrimaryKeySelective(DealRecord record);

    int updateByPrimaryKey(DealRecord record);

    int insertBatchSelective(List<DealRecord> records);

    int updateBatchByPrimaryKeySelective(List<DealRecord> records);
}