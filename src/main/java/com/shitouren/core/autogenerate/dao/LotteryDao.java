package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.Lottery;
import com.shitouren.core.autogenerate.bean.LotteryExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LotteryDao {
    long countByExample(LotteryExample example);

    int deleteByExample(LotteryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Lottery record);

    int insertSelective(Lottery record);

    List<Lottery> selectByExample(LotteryExample example);

    Lottery selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Lottery record, @Param("example") LotteryExample example);

    int updateByExample(@Param("record") Lottery record, @Param("example") LotteryExample example);

    int updateByPrimaryKeySelective(Lottery record);

    int updateByPrimaryKey(Lottery record);

    int insertBatchSelective(List<Lottery> records);

    int updateBatchByPrimaryKeySelective(List<Lottery> records);
}