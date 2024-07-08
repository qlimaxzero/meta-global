package com.shitouren.core.config.mp;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.shitouren.core.mp.mapper")
public class MybatisPlusConfig {


}
