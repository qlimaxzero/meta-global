package com.shitouren.core.config.sse;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okhttp3.internal.sse.RealEventSource;

import java.util.concurrent.*;

@Slf4j
public class SSEClientUtil {

    private static final OkHttpClient okHttpClient;

    static {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(1000);
        dispatcher.setMaxRequestsPerHost(500);

        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .dispatcher(dispatcher)
                .build();
    }

    public static void connect(Integer id, String url, String token, String system, String content) {
        FormBody body = new FormBody.Builder()
                .add("system", system)
                .add("content", content)
//                .add("msg", content)
                .build();
        Request request = new Request.Builder()
                .url(url)
                //.header("Accept", "text/event-stream")
                .header("user-token", token)
//                .header("token", token)
                .post(body)
                .build();

        // 实例化EventSource，注册EventSource监听器
        RealEventSource eventSource = new RealEventSource(request, new SSEEvenListener(id, content));
        eventSource.connect(okHttpClient);
    }

    public static String create(String url, Integer id) {
        String rs = HttpUtil.get(url + "?user=" + id);
        log.info("rs {}", rs);
        JSONObject obj = JSONUtil.parseObj(rs);
        if (obj.getInt("code") == 1) {
            return obj.getJSONObject("data").getStr("dialogue_id");
        }
        return null;
    }

    public static void main(String[] args) throws InterruptedException {
//        connect(1, "http://45.248.8.80:88/api/v1/stream_chat", "16bee66617c6447bf1628ad29a72287e", "", "订单", null);
//        connect(2, "http://127.0.0.1:8300/v2/index/dialogue", "e6b6846626d34bc480e8e253e67b95e5", "", finalI + "");
//        connect(3, "http://127.0.0.1:8300/v2/index/dialogue", "9bef8396a5aa4fb2a9bc3d20f3d4680f", "", "3", null);
        /*FormBody body = new FormBody.Builder()
                .add("content", "aa")
                .build();
        Request request = new Request.Builder()
                //.url("http://45.248.8.80:88/api/v1/stream_chat")
                .url("http://127.0.0.1:8300/v2/index/dialogue")
                .header("user-token", "c7fbf732e29b85c2544b926f6640ace9")
                .header("token", "9bef8396a5aa4fb2a9bc3d20f3d4680f")
                .post(RequestBody.create("msg=123", MediaType.parse("application/x-www-form-urlencoded")))
                .build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (response.isSuccessful()) {
                ResponseBody bb = response.body();
                BufferedSource source = bb.source();
                String line;
                while ((line = source.readUtf8Line()) != null) {
                    System.out.println(line);
                }
            } else {
                System.out.println(response);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }

}
