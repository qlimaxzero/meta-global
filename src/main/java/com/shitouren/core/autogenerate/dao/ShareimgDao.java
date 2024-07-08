package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.Shareimg;
import com.shitouren.core.autogenerate.bean.ShareimgExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ShareimgDao {
    long countByExample(ShareimgExample example);

    int deleteByExample(ShareimgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Shareimg record);

    int insertSelective(Shareimg record);

    List<Shareimg> selectByExample(ShareimgExample example);

    Shareimg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Shareimg record, @Param("example") ShareimgExample example);

    int updateByExample(@Param("record") Shareimg record, @Param("example") ShareimgExample example);

    int updateByPrimaryKeySelective(Shareimg record);

    int updateByPrimaryKey(Shareimg record);

    int insertBatchSelective(List<Shareimg> records);

    int updateBatchByPrimaryKeySelective(List<Shareimg> records);
}