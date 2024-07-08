package com.shitouren.core.utils;


import org.springframework.util.StringUtils;

import java.util.*;

public class StringUtil {

    /**
     * 判断是否是有效的字符串，空串为无效串
     * @param str
     * @return
     */
    public static boolean isValidStr(String str) {
        return str != null && str.trim().length() > 0 && !str.trim().equalsIgnoreCase("null");
    }

    public static boolean isEmpty(String str) {
        return StringUtils.isEmpty(str);
    }

    /**
     * if str is null then convert str to "".
     *
     * @param str
     * @return
     */
    public static String convertStrIfNull(String str) {
        return str == null ? "" : str;
    }


    /**
     * 获取字符串字节长度（包含中文和中文符号）
     *
     * @param str 含有中文和中文符号的字符串
     * @return int
     */
    public static int getLength(String str) {
        return str.replaceAll("[\u4E00-\u9FA5\u3000-\u303F\uFF00-\uFFEF]", "rr").length();
    }


    /**
     * 获取UUID
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String map2Str(Map<String, String> map, String firstOpt, String secondOpt) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(entry.getKey())
                    .append(secondOpt)
                    .append(entry.getValue())
                    .append(firstOpt);
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

}
