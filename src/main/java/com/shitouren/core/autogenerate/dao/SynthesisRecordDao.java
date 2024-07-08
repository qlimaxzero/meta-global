package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.SynthesisRecord;
import com.shitouren.core.autogenerate.bean.SynthesisRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SynthesisRecordDao {
    long countByExample(SynthesisRecordExample example);

    int deleteByExample(SynthesisRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SynthesisRecord record);

    int insertSelective(SynthesisRecord record);

    List<SynthesisRecord> selectByExample(SynthesisRecordExample example);

    SynthesisRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SynthesisRecord record, @Param("example") SynthesisRecordExample example);

    int updateByExample(@Param("record") SynthesisRecord record, @Param("example") SynthesisRecordExample example);

    int updateByPrimaryKeySelective(SynthesisRecord record);

    int updateByPrimaryKey(SynthesisRecord record);

    int insertBatchSelective(List<SynthesisRecord> records);

    int updateBatchByPrimaryKeySelective(List<SynthesisRecord> records);
}