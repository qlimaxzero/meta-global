package com.shitouren.core.service;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.shitouren.core.config.exception.ExceptionConstant;
import com.shitouren.core.model.vo.StatLogVO;
import com.shitouren.core.mp.bo.StatLog;
import com.shitouren.core.mp.mapper.StatLogMapper;
import com.shitouren.core.utils.AssertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.*;

@Service
@RequiredArgsConstructor
public class StatLogServiceImpl implements StatLogService {

    private final StatLogMapper statLogMapper;

    private ThreadPoolExecutor executor;

    {
        executor = new ThreadPoolExecutor(
                1,
                2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(100),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy()
        );
    }

    @Override
    public void log(StatLogVO vo, String ipAddr, Integer uid) {
        AssertUtil.isTrue(vo.getEventTime() > 1000, ExceptionConstant.参数异常);
        executor.execute(() -> {
            StatLog entity = new StatLog();
            entity.setUserid(uid);
            entity.setPageId(vo.getPageId());
            entity.setActionId(vo.getActionId());
            entity.setEventType(vo.getEventType());
            entity.setDetail(vo.getDetail());
            entity.setIp(ipAddr);
            entity.setUa(vo.getUa());
            entity.setBrowser(vo.getBrowser());
            entity.setSystem(vo.getSystem());
            entity.setEventTime(LocalDateTimeUtil.of(vo.getEventTime()));
            entity.setCreateTime(LocalDateTime.now());
            statLogMapper.insert(entity);
        });
    }

}
