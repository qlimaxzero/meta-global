package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.Creater;
import com.shitouren.core.autogenerate.bean.CreaterExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CreaterDao {
    long countByExample(CreaterExample example);

    int deleteByExample(CreaterExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Creater record);

    int insertSelective(Creater record);

    List<Creater> selectByExample(CreaterExample example);

    Creater selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Creater record, @Param("example") CreaterExample example);

    int updateByExample(@Param("record") Creater record, @Param("example") CreaterExample example);

    int updateByPrimaryKeySelective(Creater record);

    int updateByPrimaryKey(Creater record);

    int insertBatchSelective(List<Creater> records);

    int updateBatchByPrimaryKeySelective(List<Creater> records);
}