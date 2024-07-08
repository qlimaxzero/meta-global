package com.shitouren.core.config.sse;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.shitouren.core.config.exception.CloudException;
import com.shitouren.core.config.exception.ExceptionConstant;
import com.shitouren.core.model.dto.NFTHealthDTO;
import com.shitouren.core.service.UserGrantService;
import com.shitouren.core.utils.AssertUtil;
import com.shitouren.core.utils.DecimalUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

import java.util.HashMap;
import java.util.Map;

@Getter
@Slf4j
public class SSEEvenListener extends EventSourceListener {

    private Integer id;
    private String question;
    private StringBuffer answer;
    private static final UserGrantService userGrantService = SpringUtil.getBean(UserGrantService.class);

    public SSEEvenListener(Integer id, String question) {
        this.id = id;
        this.question = question;
        this.answer = new StringBuffer();
    }

    @Override
    public void onOpen(EventSource eventSource, Response response) {
        log.info("id {}", getId());
    }

    @Override
    public void onEvent(EventSource eventSource, String id, String type, String data) {
        log.info("id {} data {}", getId(), data);
        if (StrUtil.isBlank(data)) {
            return;
        }

        String sendMsg;
        boolean isEnd = true;
        try {
            JSONObject obj = JSONUtil.parseObj(data);
            AssertUtil.isFalse(obj.containsKey("error_code"), ExceptionConstant.网络异常);
            isEnd = obj.getBool("is_end", false);
            getAnswer().append(obj.getStr("result"));

            Map<String, Object> map = new HashMap<>();
            map.put("code", ExceptionConstant.处理成功.getCode());
            map.put("end", isEnd);
            map.put("id", obj.getStr("id"));
            map.put("result", obj.getStr("result"));
            if (isEnd) {
                NFTHealthDTO dto = userGrantService.deductAIHealth(getId(), question, getAnswer().toString());
                map.put("remainingWordCount", dto.getRemainingWordCount());
                map.put("useHealth", DecimalUtil.of(dto.getUseHealth()));
                map.put("stop", dto.isStop());
            }
            sendMsg = JSONUtil.toJsonStr(map);
        } catch (CloudException e) {
            log.error("", e);
            sendMsg = buildEx(e);
        } catch (Exception e) {
            log.error("", e);
            sendMsg = buildEx(ExceptionConstant.网络异常);
        }
        SSEServerUtil.send(isEnd, getId(), sendMsg);
    }

    @Override
    public void onClosed(EventSource eventSource) {
        log.info("id {}", getId());
        SSEServerUtil.close(getId());
    }

    @Override
    public void onFailure(EventSource eventSource, Throwable t, Response response) {
        log.error("id {}", getId(), t);
        if (response == null) {
            SSEServerUtil.send(true, getId(), buildEx(ExceptionConstant.网络异常));
            eventSource.cancel();
            return;
        }

        String data = null;
        try {
            if (response.isSuccessful()) {
                data = response.body().string();
            } else {
                throw new RuntimeException("response fail!");
            }
        } catch (Exception e) {
            log.error("response : {}", response.code() + " / " + response.message(), e);
        }
        log.warn("id {} data {}", getId(), data);
        SSEServerUtil.send(true, getId(), buildEx(ExceptionConstant.网络异常));
        eventSource.cancel();
    }

    private String buildEx(CloudException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", e.getCode());
        map.put("info", e.getInfo());
        return JSONUtil.toJsonStr(map);
    }

    private String buildEx(ExceptionConstant e) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", e.getCode());
        map.put("info", e.getMsg());
        return JSONUtil.toJsonStr(map);
    }

}
