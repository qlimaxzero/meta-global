package com.shitouren.core.autogenerate.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@TableName("user_grant")
@Data
public class UserGrant {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id(0则为官方)
     */
    private Integer userid;

    /**
     * 藏品id
     */
    private Integer collid;

    /**
     * 发行编号
     */
    private String truenumber;

    /**
     * 藏品hash值
     */
    private String hashs;

    /**
     * 生成时间
     */
    private Date createtime;

    /**
     * 购买价格
     */
    private BigDecimal buyprice;

    /**
     * 购买时间
     */
    private Date buytime;

    /**
     * 付款截止时间
     */
    private Date endtime;

    /**
     * 出售价格
     */
    private BigDecimal sellprice;

    /**
     * 0.待上架1.已上架2.交易中3.已完成4.已消耗
     */
    private Integer type;

    /**
     * 对方用户
     */
    private Integer oppositeuser;

    /**
     * 上架时间
     */
    private Date sjtime;

    /**
     * nft币id
     */
    private String tokenid;

    /**
     * 1.平台 2.微信 3.支付宝 4.其他
     */
    private Integer paytype;

    /**
     * 0首发1市场2空投
     */
    private Integer state;

    /**
     *
     */
    private String classid;

    /**
     * 元宇宙ID 默认0 不是0 了就是元宇宙ID
     */
    private Integer yuanyuzhouid;

    /**
     * 藏品使用情况(0:未使用;1:使用;)
     */
    @TableField("`usage`")
    private Integer usage;

    /**
     * 交易时间, 所有藏品此时间在字段上线后统一设置
     */
    private Date tradeTime;

    /**
     * 算力
     */
    private BigDecimal power;

    /**
     * 健康值
     */
    private BigDecimal health;

    /**
     * 是否首页展示:1-是
     */
    private Byte display;

    /**
     * gpt会话id
     */
    private String dialogueId;

    /**
     * gtp会话角色
     */
    private String dialogueRole;

}
