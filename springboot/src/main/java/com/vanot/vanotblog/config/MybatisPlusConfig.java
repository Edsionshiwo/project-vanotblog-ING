package com.vanot.vanotblog.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Mybatis 配置
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.vanot.vanotblog.mapper")
public class MybatisPlusConfig {
    /**
     * Bean 注解
     * 注解 Bean Factory 生成实例的方法，通过 DI 注入到 IoC 容器
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}

