package com.shitouren.core.utils;

import cn.hutool.core.lang.Pair;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.shitouren.core.bean.eums.AssetTypeEnum;
import com.shitouren.core.config.exception.ExceptionConstant;
import lombok.var;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DictConfigResolver {

    public static BigDecimal getHealthHitPercent(String healthLevelChangeConfig, BigDecimal health) {
        if (health == null) {
            return new BigDecimal("0.1");
        }
        // 80:1,60:0.8,30:0.5,0:0.1
        String[] levelConfig = healthLevelChangeConfig.split(StrUtil.COMMA);
        for (String str : levelConfig) {
            var kv = str.split(StrUtil.COLON);
            BigDecimal threshold = new BigDecimal(kv[0]);
            if (health.compareTo(threshold) >= 0) {
                return new BigDecimal(kv[1]);
            }
        }
        // 默认最小速率
        return new BigDecimal("0.1");
    }

    public static BigDecimal getGuildHitPercent(String guildRankingRewardConfig, int ranking) {
        if (ranking == -1) {
            return BigDecimal.ZERO;
        }
        // 10:0.2,30:0.15,50:0.1,100:0.05
        String[] rankRewardArr = guildRankingRewardConfig.split(StrUtil.COMMA);
        for (String str : rankRewardArr) {
            var kv = str.split(StrUtil.COLON);
            int threshold = Integer.parseInt(kv[0]);
            if (ranking <= threshold) {
                return new BigDecimal(kv[1]);
            }
        }
        // 默认无加成
        return BigDecimal.ZERO;
    }

    public static Pair<Integer, Integer> getRandomHitCollId(String probably) {
        // [{"collId":14,"percent":"2","num":2},{"collId":22,"percent":"1","num":1}]
        JSONArray array = JSONUtil.parseArray(probably);
        Integer totalPercent = array.stream().map(x -> ((JSONObject) x).getInt("percent")).reduce(0, (x, y) -> x + y);
        int ticket = RandomUtil.randomInt(totalPercent);
        int val = 0;
        for (int j = 0; j < array.size(); j++) {
            JSONObject jsonObject = array.getJSONObject(j);
            val += jsonObject.getInt("percent");
            if (ticket < val) {
                return Pair.of(jsonObject.getInt("collId"), jsonObject.getInt("num", 1));
            }
        }
        return Pair.of(null, null);
    }

    /**
     * 小于0 标识需计算字符为0, abs后为剩余可用字符; 大于0 则标识需计算字符
     * @param words 文字内容
     * @param useWordCount 已使用的字符数量
     * @param wordLimit 限制数量
     */
    public static int getNeedCalcWordCount(String words, Integer useWordCount, Integer wordLimit) {
        if (StrUtil.isBlank(words)) {
            return 0;
        }
        int calcCount = words.length();
        int remainingWordCount = (wordLimit == null ? 0 : wordLimit) - (useWordCount == null ? 0 : useWordCount);
        if (remainingWordCount <= 0) { //已经超出限制, 则所有字符即为需计算的字符数
            return calcCount;
        }
        return calcCount - remainingWordCount;
    }

    public static BigDecimal getHealthReduceByWord(int needCalcWordCount, String wordLimitCycle, BigDecimal healthReduce) {
        if (needCalcWordCount <= 0) {
            return BigDecimal.ZERO;
        }
        int cycle = needCalcWordCount / Integer.parseInt(wordLimitCycle) + 1;
        return DecimalUtil.multiply(healthReduce, DecimalUtil.of(cycle));
    }

    /**
     * sys_dict_data 字典表pay_type类型
     * 暂时写死, 只生效AIB/AIC
     * 1-AIB
     * 2-AIC
     * //3-U
     */
    public static AssetTypeEnum getBlindBoxPayAsset(String payType) {
        AssetTypeEnum typeEnum = AssetTypeEnum.getAsset(getPayType(payType));
        // 默认
        if (typeEnum == null) {
            return AssetTypeEnum.AIC;
        }
        return typeEnum;
    }

    // todo 默认第一个配置的类型
    public static Integer getPayType(String payType) {
        JSONArray array = JSONUtil.parseArray(payType);
        return (Integer)array.get(0);
    }

    public static List<Integer> getNFTIdList(String value) {
        if (value == null) {
            return new ArrayList<>();
        }
        return Arrays.stream(value.split(StrUtil.COMMA)).map(Integer::parseInt).collect(Collectors.toList());
    }

    public static BigDecimal getPayRatio(String payRatio) {
        JSONArray array = JSONUtil.parseArray(payRatio);
        AssertUtil.isFalse(array.isEmpty(), ExceptionConstant.数据异常);
        Object o = array.get(0);
        AssertUtil.notNull(o, ExceptionConstant.数据异常);
        return new BigDecimal(((JSONObject) o).getStr("ratio", "0"));
    }

}
