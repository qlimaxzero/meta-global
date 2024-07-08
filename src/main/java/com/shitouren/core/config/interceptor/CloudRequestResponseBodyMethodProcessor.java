
package com.shitouren.core.config.interceptor;

import com.google.common.collect.Lists;
import com.shitouren.core.aop.Access;
import com.shitouren.core.config.exception.CloudException;
import com.shitouren.core.config.exception.ExceptionConstant;
import com.shitouren.core.model.vo.CommonResp;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class CloudRequestResponseBodyMethodProcessor implements HandlerMethodArgumentResolver, HandlerMethodReturnValueHandler {

    List<Class<?>> payClasses = Lists.newArrayList(
//            PayAppController.class,
//            AliPay.class
    );

    public boolean supportsParameter(MethodParameter parameter) {
        return true;
    }

    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HandlerMethodArgumentResolver resolver = getArgumentResolver(parameter);
        return setUserInfo(webRequest, resolver.resolveArgument(parameter, mavContainer, webRequest, binderFactory));
    }

    private HandlerMethodArgumentResolver getArgumentResolver(MethodParameter parameter) {
        HandlerMethodArgumentResolver result = this.argumentResolverCache.get(parameter);
        if (result == null) {
            Iterator iterator = this.requestMappingHandlerAdapter.getArgumentResolvers().iterator();
            while (iterator.hasNext()) {
                HandlerMethodArgumentResolver methodArgumentResolver = (HandlerMethodArgumentResolver) iterator.next();
                if (methodArgumentResolver == null) {
                    continue;
                }
                if (methodArgumentResolver instanceof CloudRequestResponseBodyMethodProcessor) {
                    continue;
                }
                if (methodArgumentResolver.supportsParameter(parameter)) {
                    result = methodArgumentResolver;
                    this.argumentResolverCache.put(parameter, methodArgumentResolver);
                    break;
                }
            }
        }
        return result;
    }

    private Object setUserInfo(NativeWebRequest webRequest, Object obj) {
        if (obj != null) {
            HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
            assert request != null;
            if (obj instanceof List) {
                log.info(">>>>>> 请求接口地址为：{}， 请求参数为：{} ", request.getServletPath(), JSONArray.fromObject(obj));
                return obj;
            }
//            if(obj instanceof UserParam){
//                return obj;
//            }
//             try {
//                 log.info(">>>>>> 请求接口地址为：{}， 请求参数为：{} ", request.getServletPath(), JSON.toJSONString(obj));
//             } catch (Exception e) {
//                 log.info("\n >>>>>>  请求参数非JSON格式：{}", obj);
//             }

        }
        return obj;
    }
    public boolean supportsReturnType(MethodParameter methodParameter) {
        if (methodParameter.getMethod() == null) {
            return false;
        }
        Class<?> returnType = methodParameter.getMethod().getReturnType();
        if (SseEmitter.class.isAssignableFrom(returnType)) {
            return false;
        }
        RestController c = methodParameter.getDeclaringClass().getAnnotation(RestController.class);
        return c != null;
    }

    public void handleReturnValue(Object obj, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws Exception {
        HandlerMethodReturnValueHandler resolver = getReturnValueHandler(methodParameter);
        if (obj == null || obj == "") {
            obj = new Object();
        }
        if (obj instanceof ResponseEntity) {
            //解决swagger访问的问题
            ResponseEntity responseEntity = (ResponseEntity) obj;
            if (responseEntity.getStatusCodeValue() == HttpStatus.OK.value()) {
                resolver.handleReturnValue(obj, methodParameter, modelAndViewContainer, nativeWebRequest);
                return;
            } else if (responseEntity.getStatusCodeValue() == HttpStatus.NOT_FOUND.value()) {
                throw new CloudException(ExceptionConstant.请求不存在, responseEntity.getBody());
            } else {
                throw new CloudException(ExceptionConstant.请求错误, responseEntity.getBody());
            }
        } else if (obj instanceof HttpEntity) {
            HttpEntity httpEntity = (HttpEntity) obj;
//            JSONObject object = JSONObject.fromObject(httpEntity);
            throw new CloudException(ExceptionConstant.请求错误, httpEntity.getBody());
        }

        // 针对支付回调相关接口直接返回
        if (payClasses.contains(methodParameter.getDeclaringClass())) {
            Access access = methodParameter.getMethod().getAnnotation(Access.class);
            if (access != null && !access.value()) {
                resolver.handleReturnValue(obj, methodParameter, modelAndViewContainer, nativeWebRequest);
                return;
            }
        }

        if (obj.equals("success")) {
            resolver.handleReturnValue(obj, methodParameter, modelAndViewContainer, nativeWebRequest);
        } else {
            String zfc = obj.toString();
            if (zfc.length() > 7 && zfc.substring(0, 6).equals("[{Arti")) {
                resolver.handleReturnValue(obj, methodParameter, modelAndViewContainer, nativeWebRequest);
            } else {
                if(obj instanceof CommonResp){
                    resolver.handleReturnValue(obj, methodParameter, modelAndViewContainer, nativeWebRequest);
                } else{
                    resolver.handleReturnValue(new JsonResultObject<>(obj), methodParameter, modelAndViewContainer, nativeWebRequest);
                }
            }
        }
    }

    private HandlerMethodReturnValueHandler getReturnValueHandler(MethodParameter parameter) {
        HandlerMethodReturnValueHandler result = this.returnValueHandlerCache.get(parameter);
        if (result == null) {
            Iterator iterator = this.requestMappingHandlerAdapter.getReturnValueHandlers().iterator();
            while (iterator.hasNext()) {
                HandlerMethodReturnValueHandler methodReturnValueHandler = (HandlerMethodReturnValueHandler) iterator.next();
                if (methodReturnValueHandler instanceof CloudRequestResponseBodyMethodProcessor) continue;
                if (methodReturnValueHandler.supportsReturnType(parameter)) {
                    result = methodReturnValueHandler;
                    this.returnValueHandlerCache.put(parameter, methodReturnValueHandler);
                    break;
                }
            }
        }
        return result;
    }

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    private final Map<MethodParameter, HandlerMethodArgumentResolver> argumentResolverCache = new ConcurrentHashMap(256);
    private final Map<MethodParameter, HandlerMethodReturnValueHandler> returnValueHandlerCache = new ConcurrentHashMap(256);

}
