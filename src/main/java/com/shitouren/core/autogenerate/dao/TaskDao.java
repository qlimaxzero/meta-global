package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.aop.Master;
import com.shitouren.core.autogenerate.bean.Task;
import com.shitouren.core.autogenerate.bean.TaskExample;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TaskDao {
    long countByExample(TaskExample example);

    int deleteByExample(TaskExample example);

    int deleteByPrimaryKey(Integer id);

    @Master
    int insert(Task record);

    @Master
    int insertSelective(Task record);

    List<Task> selectByExample(TaskExample example);

    Task selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Task record, @Param("example") TaskExample example);

    int updateByExample(@Param("record") Task record, @Param("example") TaskExample example);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);

    @Master
    int insertBatchSelective(List<Task> records);

    int updateBatchByPrimaryKeySelective(List<Task> records);

    List<Task> getTodaySignRecords(@Param("userId") int userId,
                                   @Param("createTime") Date createTime,
                                   @Param("state")Integer state);
}
