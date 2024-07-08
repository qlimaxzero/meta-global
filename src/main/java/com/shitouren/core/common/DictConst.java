package com.shitouren.core.common;

public interface DictConst {

    int PAY_TYPE_U = 3;

    String AIC_NFT_ID_LIST = "aic_nft_id_list";//挖aic的nft id列表

    String MINING_RATE_AIB = "mining_rate_aib";//挖掘速率(单位:日)

    String MINING_RATE_AIC = "mining_rate_aic";//挖掘速率(单位:日)

    String MINING_ASSET = "mining_asset";//挖掘资金类型名称: AIB/AIC

    String ASSET_FROZEN_DURATION = "asset_frozen_duration";//挖掘数量冻结时长(单位:秒)

    String HEALTH_DEFAULT = "health_default";//默认健康值

    String HEALTH_REDUCE = "health_reduce";//健康值减少值(单位:日)

    String HEALTH_RESTORE_UNIT_VAL = "health_restore_unit_val";//健康值恢复单位值

    String HEALTH_RESTORE_UNIT_FEE = "health_restore_unit_fee";//健康值恢复单位费用

    String HEALTH_RESTORE_UNIT_ASSET = "health_restore_unit_asset";//健康值恢复单位所需资产类型

    String HEALTH_LEVEL_CHANGE_CONFIG = "health_level_change_config";//健康值等级-挖掘速度变更配置 80:1,60:0.8,30:0.5,0:0.1

    String INVITATION_POWER_REWARD_PERCENT1 = "invitation_power_reward_percent1";//邀请算力奖励1级

    String INVITATION_POWER_REWARD_PERCENT2 = "invitation_power_reward_percent2";//邀请算力奖励2级

    String GUILD_CREATE_FEE = "guild_create_fee";//公会创建费用(AIC)

    String GUILD_LEADER_REWARD_PERCENT = "guild_leader_reward_percent";//公会会长奖励(百分比)

    String GUILD_RANKING_REWARD_CONFIG = "guild_ranking_reward_config";//公会排名奖励百分比配置 10:0.2,30:0.15,500.1,100:0.05

    String GUILD_RANKING_SWITCH = "guild_ranking_switch";//公会排行赛开关1-开启,0-关闭

    String MARKET_SELL_FEE_RATE = "market_sell_fee_rate";//市场卖家手续费率

    String MARKET_BUY_FEE_RATE = "market_buy_fee_rate";//市场买家手续费率

    String NFT_AI_WORD_LIMIT = "nft_ai_word_limit";//AI字数限制周期值

    String NFT_AI_HEALTH_REDUCE = "nft_ai_health_reduce";//AI字数限制健康值减少值

    String NFT_AI_HEALTH_THRESHOLD = "nft_ai_health_threshold";//AI停止使用健康值阈值

    String EXCHANGE_SWITCH = "exchange_switch";//兑换是否开启1-开启

    String EXCHANGE_COUNTDOWN= "exchange_countdown";//兑换倒计时(单位:分)

    String GUILD_RANKING_NUM = "guild_ranking_num";//公会排行榜数量

    String TRANSFER_FEE_RATE_PREFIX = "transfer_fee_rate_";//转增手续费率(AIB/AIC)

    String TRANSFER_SWITCH = "transfer_switch";//转增是否开启1-开启

}
