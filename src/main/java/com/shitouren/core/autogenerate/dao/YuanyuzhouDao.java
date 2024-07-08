package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.Yuanyuzhou;
import com.shitouren.core.autogenerate.bean.YuanyuzhouExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;

@Mapper
public interface YuanyuzhouDao {
    long countByExample(YuanyuzhouExample example);
    @CacheEvict("getYuanyuzhouList")
    int deleteByExample(YuanyuzhouExample example);
    @CacheEvict("getYuanyuzhouList")
    int deleteByPrimaryKey(Integer id);
    @CacheEvict("getYuanyuzhouList")
    int insert(Yuanyuzhou record);
    @CacheEvict("getYuanyuzhouList")
    int insertSelective(Yuanyuzhou record);

    List<Yuanyuzhou> selectByExample(YuanyuzhouExample example);

    Yuanyuzhou selectByPrimaryKey(Integer id);

    @CacheEvict("getYuanyuzhouList")
    int updateByExampleSelective(@Param("record") Yuanyuzhou record, @Param("example") YuanyuzhouExample example);
    @CacheEvict("getYuanyuzhouList")
    int updateByExample(@Param("record") Yuanyuzhou record, @Param("example") YuanyuzhouExample example);
    @CacheEvict("getYuanyuzhouList")
    int updateByPrimaryKeySelective(Yuanyuzhou record);
    @CacheEvict("getYuanyuzhouList")
    int updateByPrimaryKey(Yuanyuzhou record);
    @CacheEvict("getYuanyuzhouList")
    int insertBatchSelective(List<Yuanyuzhou> records);
    @CacheEvict("getYuanyuzhouList")
    int updateBatchByPrimaryKeySelective(List<Yuanyuzhou> records);
}
