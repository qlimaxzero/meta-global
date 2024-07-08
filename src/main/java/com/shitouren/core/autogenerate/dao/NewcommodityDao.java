package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.Newcommodity;
import com.shitouren.core.autogenerate.bean.NewcommodityExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface NewcommodityDao {
    long countByExample(NewcommodityExample example);

    int deleteByExample(NewcommodityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Newcommodity record);

    int insertSelective(Newcommodity record);

    List<Newcommodity> selectByExampleWithBLOBs(NewcommodityExample example);

    List<Newcommodity> selectByExample(NewcommodityExample example);

    Newcommodity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Newcommodity record, @Param("example") NewcommodityExample example);

    int updateByExampleWithBLOBs(@Param("record") Newcommodity record, @Param("example") NewcommodityExample example);

    int updateByExample(@Param("record") Newcommodity record, @Param("example") NewcommodityExample example);

    int updateByPrimaryKeySelective(Newcommodity record);

    int updateByPrimaryKeyWithBLOBs(Newcommodity record);

    int updateByPrimaryKey(Newcommodity record);

    int insertBatchSelective(List<Newcommodity> records);

    int updateBatchByPrimaryKeySelective(List<Newcommodity> records);
}