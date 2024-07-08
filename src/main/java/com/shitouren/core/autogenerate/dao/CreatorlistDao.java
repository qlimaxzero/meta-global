package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.Creatorlist;
import com.shitouren.core.autogenerate.bean.CreatorlistExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CreatorlistDao {
    long countByExample(CreatorlistExample example);

    int deleteByExample(CreatorlistExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Creatorlist record);

    int insertSelective(Creatorlist record);

    List<Creatorlist> selectByExample(CreatorlistExample example);

    Creatorlist selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Creatorlist record, @Param("example") CreatorlistExample example);

    int updateByExample(@Param("record") Creatorlist record, @Param("example") CreatorlistExample example);

    int updateByPrimaryKeySelective(Creatorlist record);

    int updateByPrimaryKey(Creatorlist record);

    int insertBatchSelective(List<Creatorlist> records);

    int updateBatchByPrimaryKeySelective(List<Creatorlist> records);
}