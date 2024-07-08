package com.shitouren.core.bean.eums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PointsTypeEnum {

    // 0邀请好友1关注公众号2购买首发藏品3新用户注册4每日签到
    INVITE(0, "邀请好友"),
    FOLLOW(1, "关注公众号"),
    BUY_FIRST(2, "新用户首次首发"),
    REGISTER(3, "新用户注册"),
    SIGN(4, "每日签到"),
    SHOP(5, "购买商品"),//购买时平台及商户奖励积分
    PRODUCT(6, "兑换商品"),//积分商品购买
    HOLD(7, "模块持有奖励"),
    EXCHANGE_TICKET(8, "兑换优先购券"),
    EXCHANGE_SCENE(9, "兑换场景"), // go使用
    BUY_COLL(10, "购买首发模块"),
    MERCHANT(11, "商户激励"),
    TEST(12, "赛车游戏积分返还"),
    ACTIVITY_REWARDS(13, "活动奖励"),
    ;

    private Integer type;
    private String desc;

    public static String getDescByType(Integer type) {
        for (PointsTypeEnum pointsTypeEnum : PointsTypeEnum.values()) {
            if (pointsTypeEnum.getType().equals(type)) {
                return pointsTypeEnum.getDesc();
            }
        }
        return null;
    }

}
