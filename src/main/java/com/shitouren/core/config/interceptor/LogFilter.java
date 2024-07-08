package com.shitouren.core.config.interceptor;


import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@WebFilter(urlPatterns = "/*")
@Component
@Slf4j
public class LogFilter extends OncePerRequestFilter {

    private static final Set<String> excludeUrls = new HashSet<>();
    {
        excludeUrls.add("/Home/show");
        excludeUrls.add("/home/classification");
        excludeUrls.add("/mine/Ranking");
        excludeUrls.add("/mine/Collectionrecords");
        excludeUrls.add("/Collection/show");
        excludeUrls.add("/home/yuanyuzhou");
        excludeUrls.add("/yuanyuzhou/index");
        excludeUrls.add("/Home/message");

        excludeUrls.add("/v2/index/dialogue");
        excludeUrls.add("/v2/index/red-dot");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        long startTime = System.currentTimeMillis();
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        try {
            filterChain.doFilter(requestWrapper, responseWrapper);
        } finally {
            print(requestWrapper, responseWrapper, startTime);
            responseWrapper.copyBodyToResponse();
        }
    }

    private void print(ContentCachingRequestWrapper requestWrapper, ContentCachingResponseWrapper responseWrapper, long startTime) {
        Object[] logParamArr = getLogParamArr(requestWrapper, responseWrapper, startTime);
        log.info("{} - uid:[{}] request:[query:{}, body:{}] status:{} response:[{}] rt:{}ms", logParamArr);
    }

    @NotNull
    private Object[] getLogParamArr(ContentCachingRequestWrapper requestWrapper, ContentCachingResponseWrapper responseWrapper, long startTime) {
        Map<String, String> queryStr = this.passwordMask(ServletUtil.getParamMap(requestWrapper));
        String body = this.isJsonRequest(requestWrapper) ? new String(requestWrapper.getContentAsByteArray()) : "";
        return new Object[] {
                requestWrapper.getRequestURI(),
                WebContextHolder.get(),
                queryStr,
                body,
                responseWrapper.getStatus(),
                new String(responseWrapper.getContentAsByteArray()),
                System.currentTimeMillis() - startTime
        };
    }

    private Map<String, String> passwordMask(Map<String, String> queryStr) {
        if (queryStr.containsKey("userPassword")) {
            queryStr.put("userPassword", "***");
        }
        if (queryStr.containsKey("password")) {
            queryStr.put("password", "***");
        }
        if (queryStr.containsKey("password2")) {
            queryStr.put("password2", "***");
        }
        if (queryStr.containsKey("pass")) {
            queryStr.put("pass", "***");
        }
        return queryStr;
    }

    private boolean isJsonRequest(ServletRequest request) {
        return StrUtil.startWithIgnoreCase(request.getContentType(), MediaType.APPLICATION_JSON_VALUE);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return excludeUrls.contains(request.getRequestURI()) || WebMvcConfigurationConfig.swaggerExcludePathList
                .stream()
                .anyMatch(x -> request.getRequestURI().startsWith(x.replace("/**", "")));
    }

}
