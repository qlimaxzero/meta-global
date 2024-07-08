package com.shitouren.core.config.exception;

import cn.hutool.extra.mail.MailException;
import com.alibaba.fastjson.JSONException;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class CloudExceptionAdvice {

    /**
     * 全局异常处理
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = {Exception.class})
    public Map<String, Object> exceptionHandler(Exception ex) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", ExceptionConstant.请求失败.getCode());
        map.put("info", ExceptionConstant.请求失败.getMsg());
        map.put("data", new JSONObject());
        log.error("", ex);
        return map;
    }

    @ResponseBody
    @ExceptionHandler(value = {MailException.class})
    public Map<String, Object> exceptionHandler(MailException ex) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", ExceptionConstant.邮件发送失败.getCode());
        map.put("info", ExceptionConstant.邮件发送失败.getMsg());
        map.put("data", new JSONObject());
        log.error("", ex);
        return map;
    }

    /**
     * 拦截参数校验异常 MethodArgumentNotValidException.class
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = {
            ConstraintViolationException.class,
            MissingServletRequestParameterException.class,
            MethodArgumentTypeMismatchException.class,
            MethodArgumentNotValidException.class,
            BindException.class
    })
    public Map<String, Object> paramsExceptionHandler(Exception ex) {
        Map<String, Object> map = new HashMap<>();
        String defaultMessage;
        if (ex instanceof MethodArgumentNotValidException) {
            defaultMessage = ((MethodArgumentNotValidException) ex).getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        } else if (ex instanceof BindException) {
            defaultMessage = ((BindException) ex).getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        } else if (ex instanceof ConstraintViolationException) {
            defaultMessage = ((ConstraintViolationException) ex).getConstraintViolations().iterator().next().getMessage();
        } else {
            defaultMessage = ExceptionConstant.参数异常.getMsg();
        }
        map.put("info", defaultMessage);
        map.put("code", ExceptionConstant.参数异常.getCode());
        map.put("data", new JSONObject());
        log.error("{}", ex.getMessage());
        return map;
    }

    /**
     * 拦截自定义异常 CloudException.class
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = CloudException.class)
    public Map<String, Object> cloudExceptionHandler(CloudException ex) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", ex.getCode());
        map.put("info", ex.getInfo());
        map.put("data", ex.getData());
        return map;
    }

    /**
     * 拦截自定义异常 CloudException.class
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = JSONException.class)
    public Map<String, Object> alibabaJSONException(JSONException ex) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", ExceptionConstant.参数解析错误.getCode());
        map.put("info", ExceptionConstant.参数解析错误.getMsg());
        map.put("data", ex.getMessage());
        log.error("", ex);
        return map;
    }

}
