package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.Invitelist;
import com.shitouren.core.autogenerate.bean.InvitelistExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface InvitelistDao {
    long countByExample(InvitelistExample example);

    int deleteByExample(InvitelistExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Invitelist record);

    int insertSelective(Invitelist record);

    List<Invitelist> selectByExample(InvitelistExample example);

    Invitelist selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Invitelist record, @Param("example") InvitelistExample example);

    int updateByExample(@Param("record") Invitelist record, @Param("example") InvitelistExample example);

    int updateByPrimaryKeySelective(Invitelist record);

    int updateByPrimaryKey(Invitelist record);

    int insertBatchSelective(List<Invitelist> records);

    int updateBatchByPrimaryKeySelective(List<Invitelist> records);
}