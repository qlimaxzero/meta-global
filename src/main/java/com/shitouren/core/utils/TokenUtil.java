package com.shitouren.core.utils;


import cn.hutool.core.util.StrUtil;
import com.shitouren.core.bean.vo.user.UserLoginWx;
import com.shitouren.core.config.exception.ExceptionConstant;
import com.shitouren.core.config.redis.CloudRedisTemplate;

import java.util.function.Function;


public class TokenUtil {

    private static final CloudRedisTemplate cloudRedisTemplate = DdSpringUtils.getBean(CloudRedisTemplate.class);

    /**
     * 获取token。
     *
     * @param user
     * @return
     */
    public static String getToken(UserLoginWx user) {

        String loginAccount = String.valueOf(user.getUserId());
        Integer type = 1;
        String token = cloudRedisTemplate.get(loginAccount + 1);
        if (StringUtil.isValidStr(token)) {
            /**
             * 删除原有token
             */
            boolean set = cloudRedisTemplate.set(token, ExceptionConstant.账号在其他设备登录.getCode(), 60 * 60);
            if (!set) {
                return null;
            }
        }
        token = StringUtil.getUUID();
        boolean set = cloudRedisTemplate.set(token, user, 7 * 24 * 60 * 60);
        boolean set1 = cloudRedisTemplate.set(loginAccount + 1, token, 7 * 24 * 60 * 60);
        if (!set && !set1) {
            return null;
        }
        if (!set && set1) {
            cloudRedisTemplate.delete(loginAccount + 1);
            return null;
        }
        if (!set1 && set) {
            cloudRedisTemplate.delete(token);
            return null;
        }
        return token;
    }

    public static Integer getUid(String token) {
        if (StrUtil.isBlank(token)) {
            return null;
        }
        Object o = cloudRedisTemplate.get(token);
        if(o instanceof UserLoginWx){
            UserLoginWx user = (UserLoginWx) o;
            return user.getUserId();
        }
        return null;
    }

    public static String getTokenForTrade(UserLoginWx user) {

        String loginAccount = String.valueOf(user.getUserId());
        Integer type = 2;
        String token = cloudRedisTemplate.get(loginAccount + type);
        if (StringUtil.isValidStr(token)) {
            boolean set = cloudRedisTemplate.set(token, ExceptionConstant.账号在其他设备登录.getCode(), 60 * 60);
            if (!set) {
                return null;
            }
        }
        token = StringUtil.getUUID();
        boolean set = cloudRedisTemplate.set(token, user, 7 * 24 * 60 * 60);
        boolean set1 = cloudRedisTemplate.set(loginAccount + type, token, 7 * 24 * 60 * 60);
        if (!set && !set1) {
            return null;
        }
        if (!set && set1) {
            cloudRedisTemplate.delete(loginAccount + type);
            return null;
        }
        if (!set1 && set) {
            cloudRedisTemplate.delete(token);
            return null;
        }
        return token;
    }

    public static void deleteToken(String loginAccount, String token) {
        cloudRedisTemplate.delete(loginAccount);
        cloudRedisTemplate.delete(token);
    }



    public static String getFaceTokenKey(String token) {
        return keyGenerator.apply(token);
    }
    public static Integer getFaceTokenUid(String faceToken) {
        String faceKey = keyGenerator.apply(faceToken);
        return cloudRedisTemplate.get(faceKey);
    }

    public static void expireFaceToken(String faceToken) {
        String faceKey = keyGenerator.apply(faceToken);
         cloudRedisTemplate.delete(faceKey);
    }


    private static Function<String, String> keyGenerator = (faceToken) -> "faceToken#" + faceToken;
    public static void setFaceToken2UserId(String faceToken, Integer logUserPid) {
        String faceKey = keyGenerator.apply(faceToken);
        cloudRedisTemplate.set(faceKey, logUserPid, 3600);//存储facetoken与用户的关联关系,并保存一个小时
    }

    public static void setFaceToken2BackNo(String faceToken, String backNo) {
        String faceKey = "backNo#" + faceToken;
        cloudRedisTemplate.set(faceKey, backNo, 3600);//存储facetoken与用户的关联关系,并保存一个小时
    }

    public static String getBackNoRel(String faceToken) {
        String faceKey = "backNo#" + faceToken;
        return cloudRedisTemplate.get(faceKey);
    }


    public static Boolean getFaceTokenResult(Integer userId) {
       return cloudRedisTemplate.get("faceToken#result#" + userId);
    }

    public static void setFaceTokenResult(Integer userId, boolean result) {
        cloudRedisTemplate.set("faceToken#result#" + userId, result, 60*10);
    }

    private static final String FACE_TOKEN_KEY = "meta:face:token";

    public static void setFaceToken(String faceToken) {
        cloudRedisTemplate.set(FACE_TOKEN_KEY, faceToken, 24 * 60 * 60);
    }

    public static String getFaceToken() {
        return cloudRedisTemplate.get(FACE_TOKEN_KEY);
    }
}
