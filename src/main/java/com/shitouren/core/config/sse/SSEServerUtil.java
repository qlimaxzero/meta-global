package com.shitouren.core.config.sse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

@Slf4j
public class SSEServerUtil {

    private final static Map<Integer, SseEmitter> sseEmitterMap = new ConcurrentHashMap<>();

    /**
     * 创建连接并返回 SseEmitter
     *
     * @param id ID
     * @return SseEmitter
     */
    public static SseEmitter connect(Integer id) {
        log.info("sseEmitterMap.size {} connectId: {}", sseEmitterMap.size(), id);
        if (sseEmitterMap.containsKey(id)) {
            return sseEmitterMap.get(id);
        }
        // 设置超时时间，0表示不过期。默认30秒，超过时间未完成会抛出异常:AsyncRequestTimeoutException
        SseEmitter sseEmitter = new SseEmitter(180 * 1000L);
        // 注册回调
        sseEmitter.onCompletion(onCompletion(id));
        sseEmitter.onTimeout(onTimeout(id));
        sseEmitter.onError(onError(id, sseEmitter));

        sseEmitterMap.put(id, sseEmitter);

        /*new Thread(() -> {
            boolean end = false;
            while (!end) {
                try {
                    Thread.sleep(RandomUtil.randomInt(10) * 1000L);
                } catch (InterruptedException e) {
                    System.out.println("error::" + e.getMessage());
                }
                Map<String, Object> map = new HashMap<>();
                map.put("result", RandomUtil.randomString("啊实额存在我开的库啊", RandomUtil.randomInt(10)));
                if (RandomUtil.randomInt(20) > 15) {
                    end = true;

                    int i = RandomUtil.randomInt(2);
                    map.put("remainingWordCount", i > 0 ? RandomUtil.randomInt(1000) : 0);
                    map.put("useHealth", "0.38");
                    map.put("stop", i == 0);
                }
                map.put("end", end);
                map.put("id", Thread.currentThread().getId());
                try {
                    send(id, JSONUtil.toJsonStr(map));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            close(id);
            System.out.println("end!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }).start();*/
        return sseEmitter;
    }

    public static void send(Integer id, String message) {
        if (sseEmitterMap.containsKey(id)) {
            try {
                // sseEmitterMap.get(id).send(message, MediaType.APPLICATION_JSON);
                sseEmitterMap.get(id).send(message);
            } catch (IOException e) {
                log.error("[{}] sendMessage: {}", id, e.getMessage(), e);
                close(id);
            }
        }
    }

    public static void send(boolean isClose, Integer id, String message) {
        if (sseEmitterMap.containsKey(id)) {
            try {
                // sseEmitterMap.get(id).send(message, MediaType.APPLICATION_JSON);
                sseEmitterMap.get(id).send(message);
                if (isClose) {
                    close(id);
                }
            } catch (IOException e) {
                log.error("[{}] sendMessage: {}", id, e.getMessage(), e);
                close(id);
            }
        }
    }

    public static void close(Integer id) {
        SseEmitter sseEmitter = sseEmitterMap.get(id);
        if (sseEmitter != null) {
            sseEmitter.complete();
            sseEmitterMap.remove(id);
        }
        log.info("remove: {}", id);
    }

    private static Runnable onCompletion(Integer id) {
        return () -> {
            log.info("onCompletion: {}", id);
            close(id);
        };
    }

    private static Runnable onTimeout(Integer id) {
        return () -> {
            log.info("onTimeout: {}", id);
            close(id);
        };
    }

    private static Consumer<Throwable> onError(Integer id, SseEmitter sseEmitter) {
        return throwable -> {
            log.error("connection exception: {} err {}", id, throwable.getMessage());
            try {
                sseEmitter.send(SseEmitter.event()
                        .id(String.valueOf(id))
                        .name("Connection exception!")
                        .data("Connection exception, please try again!")
                        .reconnectTime(3000)
                        .build());
                sseEmitterMap.put(id, sseEmitter);
            } catch (IOException e) {
                log.error("", e);
                close(id);
            }
        };
    }

}
