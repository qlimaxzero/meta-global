package com.shitouren.core.service;

import com.shitouren.core.model.dto.PowerEventDTO;
import com.shitouren.core.model.vo.GuildVO;
import com.shitouren.core.model.vo.UserVO;
import com.shitouren.core.mp.bo.Guild;

import java.math.BigDecimal;
import java.util.List;

public interface GuildService {

    void refreshGuildRanking(PowerEventDTO dto);

    Guild getGuildByLeader(Integer uid);

    Guild getGuildByMember(Integer uid);

    Integer getGuildIdByMember(Integer uid);

    void join(Integer uid, String code);

    void create(Integer uid, String name);

    int getRanking(Integer guildId);

    BigDecimal getRankScore(Integer guildId);

    GuildVO myGuild(Integer uid);

    List<UserVO> members(Integer guildId);

    List<GuildVO> rankingList();

}
