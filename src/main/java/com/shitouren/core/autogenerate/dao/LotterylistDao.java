package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.Lotterylist;
import com.shitouren.core.autogenerate.bean.LotterylistExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LotterylistDao {
    long countByExample(LotterylistExample example);

    int deleteByExample(LotterylistExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Lotterylist record);

    int insertSelective(Lotterylist record);

    List<Lotterylist> selectByExample(LotterylistExample example);

    Lotterylist selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Lotterylist record, @Param("example") LotterylistExample example);

    int updateByExample(@Param("record") Lotterylist record, @Param("example") LotterylistExample example);

    int updateByPrimaryKeySelective(Lotterylist record);

    int updateByPrimaryKey(Lotterylist record);

    int insertBatchSelective(List<Lotterylist> records);

    int updateBatchByPrimaryKeySelective(List<Lotterylist> records);
}