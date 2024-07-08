package com.shitouren.core.config.interceptor;

import com.alibaba.fastjson.serializer.*;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.shitouren.core.utils.DecimalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.ResourceUtils;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebMvcConfigurationConfig extends WebMvcConfigurationSupport {

    @Autowired
    private CloudHandlerInterceptor handlerInterceptor;

    /**
     * 排除过滤swagger的相关资源
     */
    public static List<String> swaggerExcludePathList = new ArrayList<>();

    static {
        swaggerExcludePathList.add("/doc.html");
        swaggerExcludePathList.add("/swagger-resources/**");
        swaggerExcludePathList.add("/v2/api-docs/**");
        swaggerExcludePathList.add("/v2/api-docs-ext/**");
        swaggerExcludePathList.add("/webjars/**");
        swaggerExcludePathList.add("/static/**");
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(handlerInterceptor)
                .addPathPatterns("/**")
                 //排除过滤swagger相关
                .excludePathPatterns(swaggerExcludePathList);
        super.addInterceptors(registry);
    }
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
        super.addResourceHandlers(registry);
    }

    @Autowired
    private CloudRequestResponseBodyMethodProcessor methodProcessor;

    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(methodProcessor);
    }

    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        super.addReturnValueHandlers(returnValueHandlers);
        returnValueHandlers.add(methodProcessor);
    }

    @PostConstruct
    public void init() {
        RequestMappingHandlerAdapter requestMappingHandlerAdapter = requestMappingHandlerAdapter(mvcContentNegotiationManager()
                ,mvcConversionService()
                ,mvcValidator());
        final List<HandlerMethodArgumentResolver> argumentResolvers = new ArrayList<>(requestMappingHandlerAdapter.getArgumentResolvers());
        argumentResolvers.add(0, methodProcessor);
        requestMappingHandlerAdapter.setArgumentResolvers(argumentResolvers);

        final List<HandlerMethodReturnValueHandler> returnValueHandlers = new ArrayList<>(requestMappingHandlerAdapter.getReturnValueHandlers());
        returnValueHandlers.add(0, methodProcessor);
        requestMappingHandlerAdapter.setReturnValueHandlers(returnValueHandlers);
    }

    /**
     * 使用阿里 fastjson 作为JSON MessageConverter
     *
     * @param converters
     */
    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(
                //保留map空的字段
                SerializerFeature.WriteMapNullValue,
                // 将String类型的NULL转化为""
                SerializerFeature.WriteNullStringAsEmpty,
                // 将Number类型的NULL转化为0
                SerializerFeature.WriteNullNumberAsZero,

                //SerializerFeature.WriteNonStringValueAsString,
                // 将List类型的NULL转成[]
                SerializerFeature.WriteNullListAsEmpty,
                // 将Boolean类型的NULL转化为false
                SerializerFeature.WriteNullBooleanAsFalse,
                // 避免循环引用
                SerializerFeature.DisableCircularReferenceDetect);
//        /*解决LONG丢失经度的问题*/
//        SerializeConfig serializeConfig = SerializeConfig.globalInstance;
//        serializeConfig.put(Long.class , ToStringSerializer.instance);
//        serializeConfig.put(Long.TYPE , ToStringSerializer.instance);
//        config.setSerializeConfig(serializeConfig);

        ValueFilter decimalFilter = (obj, name, value) -> {
            if (value instanceof BigDecimal) {
                return DecimalUtil.of(value).toPlainString();
            }
            return value;
        };
        config.setSerializeFilters(decimalFilter);
        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(StandardCharsets.UTF_8);
        List<MediaType> mediaTypeList = new ArrayList<>();
        // 解决中文乱码问题，相当于在Controller上的@RequestMapping中加了个属性produces = "application/json"
        mediaTypeList.add(MediaType.APPLICATION_JSON);
        converter.setSupportedMediaTypes(mediaTypeList);
        converters.add(converter);
        converters.add(new StringHttpMessageConverter());

    }

}
