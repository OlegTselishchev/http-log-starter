package com.example.httploger.config;

import com.example.httploger.intercepter.HttpLogInterceptor;
import com.example.httploger.server.HttpLogService;
import com.example.httploger.server.impl.HttpLogServiceWarn;
import com.example.httploger.server.impl.HttpLogServiceInfo;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@AutoConfiguration
@EnableConfigurationProperties(HttpLogProperties.class)
@ConditionalOnProperty(prefix = "http-log", value = "enabled", havingValue = "true")
public class HttpLogAutoConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "http-log", value = "level", havingValue = "INFO")
    public HttpLogService httpLogServiceInfo() {
        return new HttpLogServiceInfo();
    }

    @Bean
    @ConditionalOnProperty(prefix = "http-log", value = "level", havingValue = "WARN")
    public HttpLogService httpLogServiceWarn() {
        return new HttpLogServiceWarn();
    }

    @Bean
    @ConditionalOnExpression("${http-log.enabled:false}")
    public HttpLogInterceptor httpLogInterceptor() {
        return new HttpLogInterceptor();
    }

    @Bean
    @ConditionalOnBean(name = "httpLogInterceptor")
    public WebMvcConfigurer webMvcConfigurer(HttpLogInterceptor httpLogInterceptor, HttpLogService httpLogService) {
        return new WebMvcConfigurer() {
            public void addInterceptors(InterceptorRegistry registry) {
                httpLogInterceptor.setHttpLogService(httpLogService);
                registry.addInterceptor(httpLogInterceptor);
            }
        };
    }
}
