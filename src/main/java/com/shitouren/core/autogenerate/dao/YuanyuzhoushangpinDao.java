package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.Yuanyuzhoushangpin;
import com.shitouren.core.autogenerate.bean.YuanyuzhoushangpinExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface YuanyuzhoushangpinDao {
    long countByExample(YuanyuzhoushangpinExample example);

    int deleteByExample(YuanyuzhoushangpinExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Yuanyuzhoushangpin record);

    int insertSelective(Yuanyuzhoushangpin record);

    List<Yuanyuzhoushangpin> selectByExample(YuanyuzhoushangpinExample example);

    Yuanyuzhoushangpin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Yuanyuzhoushangpin record, @Param("example") YuanyuzhoushangpinExample example);

    int updateByExample(@Param("record") Yuanyuzhoushangpin record, @Param("example") YuanyuzhoushangpinExample example);

    int updateByPrimaryKeySelective(Yuanyuzhoushangpin record);

    int updateByPrimaryKey(Yuanyuzhoushangpin record);

    int insertBatchSelective(List<Yuanyuzhoushangpin> records);

    int updateBatchByPrimaryKeySelective(List<Yuanyuzhoushangpin> records);
}