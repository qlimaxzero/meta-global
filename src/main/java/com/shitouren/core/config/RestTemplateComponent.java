package com.shitouren.core.config;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Component
@Slf4j
public class RestTemplateComponent {

    @Resource
    private RestTemplate restTemplate;

    public Object postEntity(String url, String jsonParam) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json"); // 传递请求体时必须设置
        // 2.请求头 & 请求体
        HttpEntity<String> fromEntity = new HttpEntity<>(JSONUtil.toJsonStr(jsonParam), httpHeaders);
        ResponseEntity<String> objectResponseEntity = restTemplate.postForEntity(url, fromEntity, String.class);
        JSONObject jsonObject = JSONUtil.parseObj(objectResponseEntity);
        return jsonObject.get("body");
    }
}
