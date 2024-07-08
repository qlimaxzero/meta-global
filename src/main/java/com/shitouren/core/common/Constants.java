package com.shitouren.core.common;

import cn.hutool.core.collection.ListUtil;

import java.util.List;

/**
 * 通用常量信息
 *
 * @author ruoyi
 */
public class Constants {

    /**
     * 平台场景持有用户: 18033613398 (27673)
     */
    public static final Integer PLATFORM_USER_ID = 27673;

    /**
     * 元宇宙场景藏品id
     */
    public static final int YUANYUZHOU_COLL_ID = 66;

    /**
     * 元宇宙场景开启(可使用)标识
     */
    public static final Byte YUANYUZHOU_OPEN = 1;

    public static final String META_KEY = "meta-ai:";

    public static final String LOCK_KEY = META_KEY + "lock:";

    public static final String IDEMPOTENT_KEY = META_KEY + "idempotent:";

    public static final String SCHEDULING_KEY = META_KEY + "scheduling:machine";

    public static final String PI_COMPLETE_LOCK_KEY = META_KEY + "pi:lock:complete:";

    public static final String BLINDBOX_LOCK_KEY = META_KEY + "blindbox:lock:";

    public static final String MARKET_LOCK_KEY = META_KEY + "blindbox:lock:";

    public static final String EXCHANGE_LOCK_KEY = META_KEY + "exchange:lock:";

    public static final String CODE_LIMIT_KEY = META_KEY + "code:limit:";

    /**
     * 通用成功标识
     */
    public static final String SUCCESS = "0";

    /**
     * 通用失败标识
     */
    public static final String FAIL = "1";

    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 登录用户 redis key
     */
    public static final String LOGIN_TOKEN_KEY = "login_tokens:";

    /**
     * 防重提交 redis key
     */
    public static final String REPEAT_SUBMIT_KEY = "repeat_submit:";

    /**
     * 限流 redis key
     */
    public static final String RATE_LIMIT_KEY = "rate_limit:";

    /**
     * 验证码有效期（分钟）
     */
    public static final Integer CAPTCHA_EXPIRATION = 2;

    /**
     * 令牌
     */
    public static final String TOKEN = "token";

    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 令牌前缀
     */
    public static final String LOGIN_USER_KEY = "login_user_key";

    /**
     * 用户ID
     */
    public static final String JWT_USERID = "userid";

    /**
     * 资源映射路径 前缀
     */
    public static final String RESOURCE_PREFIX = "/profile";

    public static final String AVATAR_DEFAULT = "/profile/mmeettaallooggoo.png";

    public static final String SWITCH_ON = "1";//开关1-开启,0-关闭

    public static final String AI_ROLE_SYMBOL = "//";//ai角色符号

    public static final String EXPIRE_TIME = "30";//默认超时时间

    // 初始化NFT 男/女
    public static final Integer INIT_NFT_ID_M = 1;
    public static final Integer INIT_NFT_ID_F = 2;
    public static final List<Integer> INIT_NFT_LIST = ListUtil.of(INIT_NFT_ID_M, INIT_NFT_ID_F);

    // 发放状态:0-未发放;1-已发放
    public static final Byte MINING_STATUS_NOT = 0;
    public static final Byte MINING_STATUS_OK = 1;

    // 状态: 0-已冻结;1-已解冻
    public static final Byte ASSET_STATUS_FROZEN = 0;
    public static final Byte ASSET_STATUS_UNFROZEN = 1;

    // 资金记录类型
    public static final Byte ASSET_RECORD_AIRDROP = 0;//空投
    public static final Byte ASSET_RECORD_MINING_FROZEN = 1;//挖掘-冻结
    public static final Byte ASSET_RECORD_MINING_UNFROZEN = 2;//挖掘-解冻
    public static final Byte ASSET_RECORD_RESTORE_HEALTH = 3;//恢复健康值
    public static final Byte ASSET_RECORD_GUILD = 4;//创建公会
    public static final Byte ASSET_RECORD_MARKET_BUY = 5;//市场-购买
    public static final Byte ASSET_RECORD_MARKET_SELL = 6;//出售-上架
    public static final Byte ASSET_RECORD_BLINDBOX_BUY = 7;//盲盒购买
    public static final Byte ASSET_RECORD_EXCHANGE = 8;//兑换

    // 状态:0-已奖励;1-已重置
    public static final Byte INVITATION_POWER_INIT = 0;
    public static final Byte INVITATION_POWER_RESET = 1;

    // 首页展示状态:0-不展示;1-展示
    public static final Byte DISPLAY_ON = 1;
    public static final Byte DISPLAY_OFF = 0;

    // 是否计做有效用户:1-有效
    public static final Byte USER_VALID = 1;
    public static final Byte USER_INVALID = 0;

    // nft变动类型
    public static final Integer NFT_TRANSFER_AIRDROP = 0; //空投
    public static final Integer NFT_TRANSFER_BLINDBOX_BUY = 1; //盲盒购买
    public static final Integer NFT_TRANSFER_MARKET_BUY = 2; //购买
    public static final Integer NFT_TRANSFER_MARKET_SELL = 3; //售出

    //0.待上架1.已上架2.交易中3.已完成4.已消耗
    public static final Integer NFT_TYPE_INIT = 0;
    public static final Integer NFT_TYPE_UP = 1;
    public static final Integer NFT_TYPE_TRADE = 2;

    // 用户市场nft上架状态
    public static final Integer USER_MARKET_NFT_UP = 1;
    public static final Integer USER_MARKET_NFT_PENDING = 2;

    // 0首发1市场2空投
    public static final Integer NFT_STATE_INIT = 99;
    public static final Integer NFT_STATE_BUY = 0;
    public static final Integer NFT_STATE_MARKET = 1;
    public static final Integer NFT_STATE_AIRDROP = 2;

    // 资产售卖状态 0-下架;1-上架
    public static final Byte ASSET_SALE_DOWN = 0;
    public static final Byte ASSET_SALE_UP = 1;

    // 公会排行榜
    public static final String GUILD_RANKING_KEY = META_KEY + "guild:ranking";

    // 已上架
    public static final Integer ISSUE_TYPE_UP = 1;

    // nft ai聊天字数统计
    public static final String NFT_WORD_COUNT_KEY = META_KEY + "nft:word:count:";

    public static final int DIALOGUE_RECORD_LIMIT = 50;

    public static final String RECORD_REDDOT_KEY = META_KEY + "record:reddot:";
}
