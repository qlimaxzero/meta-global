package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.Openboxrecord;
import com.shitouren.core.autogenerate.bean.OpenboxrecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OpenboxrecordDao {
    long countByExample(OpenboxrecordExample example);

    int deleteByExample(OpenboxrecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Openboxrecord record);

    int insertSelective(Openboxrecord record);

    List<Openboxrecord> selectByExample(OpenboxrecordExample example);

    Openboxrecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Openboxrecord record, @Param("example") OpenboxrecordExample example);

    int updateByExample(@Param("record") Openboxrecord record, @Param("example") OpenboxrecordExample example);

    int updateByPrimaryKeySelective(Openboxrecord record);

    int updateByPrimaryKey(Openboxrecord record);

    int insertBatchSelective(List<Openboxrecord> records);

    int updateBatchByPrimaryKeySelective(List<Openboxrecord> records);
}