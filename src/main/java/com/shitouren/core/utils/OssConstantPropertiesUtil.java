package com.shitouren.core.utils;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @description: 阿里oss配置工具类
 * @author: Donugh
 * @create: 2023-05-15 00:09
 **/

@Component
@Data
public class OssConstantPropertiesUtil implements InitializingBean {
    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;//地域节点

    @Value("${aliyun.oss.file.accessKeyID}")
    private String keyId;//id

    @Value("${aliyun.oss.file.accessKeySecret}")
    private String keySecret;//秘钥

    @Value("${aliyun.oss.file.bucketName}")
    private String bucketName;//项目名称

    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() {
        END_POINT = endpoint;
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keySecret;
        BUCKET_NAME = bucketName;
    }

}
