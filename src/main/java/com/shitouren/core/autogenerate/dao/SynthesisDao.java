package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.Synthesis;
import com.shitouren.core.autogenerate.bean.SynthesisExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SynthesisDao {
    long countByExample(SynthesisExample example);

    int deleteByExample(SynthesisExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Synthesis record);

    int insertSelective(Synthesis record);

    List<Synthesis> selectByExampleWithBLOBs(SynthesisExample example);

    List<Synthesis> selectByExample(SynthesisExample example);

    Synthesis selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Synthesis record, @Param("example") SynthesisExample example);

    int updateByExampleWithBLOBs(@Param("record") Synthesis record, @Param("example") SynthesisExample example);

    int updateByExample(@Param("record") Synthesis record, @Param("example") SynthesisExample example);

    int updateByPrimaryKeySelective(Synthesis record);

    int updateByPrimaryKeyWithBLOBs(Synthesis record);

    int updateByPrimaryKey(Synthesis record);

    int insertBatchSelective(List<Synthesis> records);

    int updateBatchByPrimaryKeySelective(List<Synthesis> records);

    int updateSynthesizedSafelyByPrimaryKey(@Param("id") Integer id);
}