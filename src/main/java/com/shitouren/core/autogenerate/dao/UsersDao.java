package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.Users;
import com.shitouren.core.autogenerate.bean.UsersExample;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UsersDao {
    long countByExample(UsersExample example);

    int deleteByExample(UsersExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(Users record);

    int insertSelective(Users record);

    List<Users> selectByExample(UsersExample example);

    Users selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") Users record, @Param("example") UsersExample example);

    int updateByExample(@Param("record") Users record, @Param("example") UsersExample example);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    int insertBatchSelective(List<Users> records);

    int updateBatchByPrimaryKeySelective(List<Users> records);

    Users getUserById(Integer userId);

    /**
     * "increase referral rewards"。
     * @return
     */
    int addInvitationRewardsForOneTime(@Param("addMoney") BigDecimal addMoney,
                                       @Param("userId") Integer userId);
    /**
     * "increase referral rewards"。
     * @return
     */
    int addMoneySafely(@Param("addMoney") BigDecimal addMoney,
                       @Param("userId") Integer userId,
                       @Param("money") BigDecimal money);

    //更新积分
    int updateScoreByPrimaryKey(@Param("userId") Integer userId, @Param("money") BigDecimal money);

    //查询排行榜
    List<Users> selectRankingList(@Param("realNameType") Integer realNameType, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("count") Integer count);

    int updateBalanceByCAS(@Param("userId") Integer userId, @Param("oldBalance") BigDecimal oldBalance, @Param("balance") BigDecimal balance);

    List<Map<String, Object>> selectColumnsByUidList(@Param("columns") String columns, @Param("uidList") List<Integer> uidList);

}
