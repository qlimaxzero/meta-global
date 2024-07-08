package com.shitouren.core.service;

import cn.hutool.core.util.RandomUtil;
import com.shitouren.core.bean.eums.AssetRecordEnum;
import com.shitouren.core.bean.eums.AssetTypeEnum;
import com.shitouren.core.common.Constants;
import com.shitouren.core.common.DictConst;
import com.shitouren.core.config.exception.ExceptionConstant;
import com.shitouren.core.config.redis.CloudRedisTemplate;
import com.shitouren.core.model.dto.PowerEventDTO;
import com.shitouren.core.model.vo.GuildVO;
import com.shitouren.core.model.vo.UserVO;
import com.shitouren.core.mp.bo.Guild;
import com.shitouren.core.mp.bo.GuildMember;
import com.shitouren.core.mp.mapper.GuildMapper;
import com.shitouren.core.mp.mapper.GuildMemberMapper;
import com.shitouren.core.utils.AssertUtil;
import com.shitouren.core.utils.DecimalUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class GuildServiceImpl implements GuildService {

    private final GuildMapper guildMapper;
    private final GuildMemberMapper guildMemberMapper;

    private final UserGrantService userGrantService;
    private final AssetService assetService;
    private final DictService dictService;
    private final UserService userService;

    private final CloudRedisTemplate cloudRedisTemplate;
    private final ApplicationEventPublisher eventPublisher;

    private static final Map<Integer, Guild> guildMap = new HashMap<>();

    @PostConstruct
    public void init() {
        ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutor.scheduleAtFixedRate(() -> {
            List<Guild> guilds = guildMapper.selectList(null);
            guildMap.putAll(guilds.stream().collect(Collectors.toMap(Guild::getId, Function.identity())));
            //log.info("load guild list {}", guilds.size());
        }, 0, 30, TimeUnit.SECONDS);

        List<GuildMember> guildMembers = guildMemberMapper.selectList(null);
        if (guildMembers.isEmpty()) {
            return;
        }

        Map<Integer, BigDecimal> collect = guildMembers.stream()
                .collect(Collectors.groupingBy(
                        GuildMember::getGuildId,
                        Collectors.reducing(
                                BigDecimal.ZERO, x -> userGrantService.sumTotalPower(x.getUserid()),
                                DecimalUtil::add
                        )
                ));

        cloudRedisTemplate.zadd(Constants.GUILD_RANKING_KEY, collect);
    }

    @Override
    public void refreshGuildRanking(PowerEventDTO dto) {
        Integer guildId = dto.getGuildId() == null ? getGuildIdByMember(dto.getUid()) : dto.getGuildId();
        if (guildId == null) {
            log.warn("user not guild, {}", dto);
            return;
        }
        BigDecimal power = guildMemberMapper.selectMembers(guildId)
                .stream()
                .map(x -> userGrantService.sumTotalPower(x.getUserid()))
                .reduce(BigDecimal.ZERO, DecimalUtil::add);
        cloudRedisTemplate.zadd(Constants.GUILD_RANKING_KEY, guildId, power);
    }


    @Override
    public Guild getGuildByLeader(Integer uid) {
        return guildMapper.getGuildByUid(uid);
    }

    @Override
    public Guild getGuildByMember(Integer uid) {
        GuildMember guildMember = guildMemberMapper.getGuildByMember(uid);
        if (guildMember == null) {
            return null;
        }
        return getGuild(guildMember.getGuildId());
    }

    private Guild getGuild(Integer id) {
        return guildMap.getOrDefault(id, guildMapper.selectById(id));
    }

    @Override
    public Integer getGuildIdByMember(Integer uid) {
        GuildMember guildMember = guildMemberMapper.getGuildByMember(uid);
        return guildMember == null ? null : guildMember.getGuildId();
    }

    @Override
    public void join(Integer uid, String code) {
        AssertUtil.isNull(getGuildIdByMember(uid), ExceptionConstant.GUILD_JOIN_ALREAY);
        Guild guild = guildMapper.getGuildByCode(code);
        AssertUtil.notNull(guild, ExceptionConstant.邀请码不存在);
        guildMemberMapper.save(uid, guild.getId());
        // 触发公会事件
        eventPublisher.publishEvent(new PowerEventDTO(uid, guild.getId()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Integer uid, String name) {
        AssertUtil.isNull(getGuildIdByMember(uid), ExceptionConstant.GUILD_JOIN_ALREAY);
        Guild guild = guildMapper.getGuildByName(name);
        AssertUtil.isNull(guild, ExceptionConstant.GUILD_ALREAY_EXIST);

        // create
        guild = guildMapper.save(uid, name, RandomUtil.randomStringUpper(8));
        guildMemberMapper.save(uid, guild.getId());

        assetService.modifyUsableAmt(uid, AssetTypeEnum.AIC,
                dictService.getDecimalValue(DictConst.GUILD_CREATE_FEE).negate(), AssetRecordEnum.GUILD, String.valueOf(guild.getId()));

        // 触发公会事件
        eventPublisher.publishEvent(new PowerEventDTO(uid, guild.getId()));
    }

    @Override
    public int getRanking(Integer guildId) {
        return cloudRedisTemplate.zReverseRank(Constants.GUILD_RANKING_KEY, guildId);
    }

    @Override
    public BigDecimal getRankScore(Integer guildId) {
        return cloudRedisTemplate.zscore(Constants.GUILD_RANKING_KEY, guildId);
    }

    @Override
    public GuildVO myGuild(Integer uid) {
        Guild guild = this.getGuildByMember(uid);
        if (guild == null) {
            return null;
        }

        return GuildVO.builder()
                .id(guild.getId())
                .name(guild.getName())
                .code(guild.getCode())
                .ranking(getRanking(guild.getId()))
                .power(cloudRedisTemplate.zscore(Constants.GUILD_RANKING_KEY, guild.getId()))
                .build();
    }

    @Override
    public List<UserVO> members(Integer guildId) {
        AssertUtil.notNull(getGuild(guildId), ExceptionConstant.GUILD_NOT_EXIST);

        List<Integer> uidList = guildMemberMapper.selectMembers(guildId)
                .stream()
                .map(GuildMember::getUserid)
                .collect(Collectors.toList());

        List<Map<String, Object>> users = userService.selectColumnsByUidList("user_id as uid, nick_name as nickname", uidList);

        return users.stream().map(x -> UserVO.builder()
                .uid((Integer) x.get("uid"))
                .nickname((String) x.get("nickname"))
                .power(userGrantService.sumTotalPower((Integer) x.get("uid")))
                .build()
        ).collect(Collectors.toList());
    }

    @Override
    public List<GuildVO> rankingList() {
        long num = Long.parseLong(dictService.getValue(DictConst.GUILD_RANKING_NUM, "200"));
        return cloudRedisTemplate.zReverseRangeByScore(Constants.GUILD_RANKING_KEY, 0, num)
                .stream()
                .map(x ->
                        GuildVO.builder()
                                .name(getGuild((Integer) x.getValue()).getName())
                                .power(DecimalUtil.of(x.getScore()))
                                .build()
                )
                .collect(Collectors.toList());
    }

}
