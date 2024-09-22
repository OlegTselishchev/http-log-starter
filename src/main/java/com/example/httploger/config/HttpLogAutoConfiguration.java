package com.example.httploger.config;

import com.example.httploger.intercepter.HttpLogInterceptor;

import com.example.httploger.server.log.HttpLogService;
import com.example.httploger.server.log.impl.*;
import com.example.httploger.server.operator.HttpLogOperator;
import com.example.httploger.server.operator.impl.HttpLogCachedOperator;
import com.example.httploger.server.operator.impl.HttpLogSimpleOperator;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


@AutoConfiguration
@EnableConfigurationProperties(HttpLogProperties.class)
public class HttpLogAutoConfiguration {


    @Bean
    public HttpLogService httpLogServiceInfo() {
        return new HttpLogServiceInfo();
    }

    @Bean
    public HttpLogService httpLogServiceWarn() {
        return new HttpLogServiceWarn();
    }

    @Bean
    public HttpLogService httpLogServiceDebug() {
        return new HttpLogServiceDebug();
    }

    @Bean
    public HttpLogService httpLogServiceTrace() {
        return new HttpLogServiceTrace();
    }

    @Bean
    public HttpLogService httpLogServiceError() {
        return new HttpLogServiceError();
    }

    @Bean
    @ConditionalOnProperty(prefix = "http-log", value = "cache", havingValue = "false", matchIfMissing = true)
    public HttpLogOperator httpLogSimpleOperator(List<HttpLogService> httpLogServices, HttpLogProperties properties) {
        return new HttpLogSimpleOperator(httpLogServices, properties);
    }

    @Bean
    @ConditionalOnProperty(prefix = "http-log", value = "cache", havingValue = "true")
    public HttpLogOperator httpLogCacheOperator(List<HttpLogService> httpLogServices, HttpLogProperties properties) {
        return new HttpLogCachedOperator(httpLogServices, properties);
    }

    @Bean
    @ConditionalOnExpression("${http-log.enabled:false}")
    public HttpLogInterceptor httpLogInterceptor(HttpLogOperator httpLogOperator) {
        return new HttpLogInterceptor(httpLogOperator);
    }

    @Bean
    @ConditionalOnBean(name = "httpLogInterceptor")
    public WebMvcConfigurer webMvcConfigurer(HttpLogInterceptor httpLogInterceptor) {
        return new WebMvcConfigurer() {
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(httpLogInterceptor);
            }
        };
    }
}
