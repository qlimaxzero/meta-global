package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.Pullnew;
import com.shitouren.core.autogenerate.bean.PullnewExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PullnewDao {
    long countByExample(PullnewExample example);

    int deleteByExample(PullnewExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Pullnew record);

    int insertSelective(Pullnew record);

    List<Pullnew> selectByExample(PullnewExample example);

    Pullnew selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Pullnew record, @Param("example") PullnewExample example);

    int updateByExample(@Param("record") Pullnew record, @Param("example") PullnewExample example);

    int updateByPrimaryKeySelective(Pullnew record);

    int updateByPrimaryKey(Pullnew record);

    int insertBatchSelective(List<Pullnew> records);

    int updateBatchByPrimaryKeySelective(List<Pullnew> records);
}