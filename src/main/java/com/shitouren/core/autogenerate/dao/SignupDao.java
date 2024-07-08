package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.Signup;
import com.shitouren.core.autogenerate.bean.SignupExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SignupDao {
    long countByExample(SignupExample example);

    int deleteByExample(SignupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Signup record);

    int insertSelective(Signup record);

    List<Signup> selectByExample(SignupExample example);

    Signup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Signup record, @Param("example") SignupExample example);

    int updateByExample(@Param("record") Signup record, @Param("example") SignupExample example);

    int updateByPrimaryKeySelective(Signup record);

    int updateByPrimaryKey(Signup record);

    int insertBatchSelective(List<Signup> records);

    int updateBatchByPrimaryKeySelective(List<Signup> records);
}