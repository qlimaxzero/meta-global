package com.shitouren.core.controller.v2;

import com.shitouren.core.aop.Access;
import com.shitouren.core.common.Constants;
import com.shitouren.core.model.vo.StatLogVO;
import com.shitouren.core.service.StatLogService;
import com.shitouren.core.utils.IpUtils;
import com.shitouren.core.utils.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@Api(tags = "新版 - 埋点")
@RequestMapping("/v2/stat")
@RequiredArgsConstructor
public class StatLogController {

    private final StatLogService statLogService;

    @PostMapping("/log")
    @ApiOperation("埋点")
    @Access(value = false)
    public void log(StatLogVO statLogVO, HttpServletRequest request) {
        statLogService.log(statLogVO, IpUtils.getIpAddr(request), TokenUtil.getUid(request.getHeader(Constants.TOKEN)));
    }

}
