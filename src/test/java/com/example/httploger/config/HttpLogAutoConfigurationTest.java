package com.example.httploger.config;

import com.example.httploger.intercepter.HttpLogInterceptor;
import com.example.httploger.server.log.impl.HttpLogServiceInfo;
import com.example.httploger.server.log.impl.HttpLogServiceWarn;
import com.example.httploger.server.operator.impl.HttpLogCachedOperator;
import com.example.httploger.server.operator.impl.HttpLogSimpleOperator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HttpLogAutoConfigurationTest {

    private final ApplicationContextRunner context = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(HttpLogAutoConfiguration.class));

    @Test
    public void createBeans_httpLogServiceInfo_successCreateBean() {
        context.withPropertyValues()
                .run(context -> {
                    assertThat(context).hasSingleBean(HttpLogServiceInfo.class);
                });
    }

    @Test
    public void createBeans_httpLogServiceWarn_successCreateBean() {
        context.withPropertyValues()
                .run(context -> {
                    assertThat(context).hasSingleBean(HttpLogServiceWarn.class);
                });
    }

    @Test
    public void createBeans_httpLogServiceDebug_successCreateBean() {
        context.withPropertyValues()
                .run(context -> {
                    assertThat(context).hasSingleBean(HttpLogServiceWarn.class);
                });
    }

    @Test
    public void createBeans_httpLogServiceTrace_successCreateBean() {
        context.withPropertyValues()
                .run(context -> {
                    assertThat(context).hasSingleBean(HttpLogServiceWarn.class);
                });
    }

    @Test
    public void createBeans_httpLogServiceError_successCreateBean() {
        context.withPropertyValues()
                .run(context -> {
                    assertThat(context).hasSingleBean(HttpLogServiceWarn.class);
                });
    }

    @Test
    public void createBeans_httpLogSimpleOperation_successCreateBean() {
        context.withPropertyValues("http-log.cache=false")
                .run(context -> {
                    assertThat(context).hasSingleBean(HttpLogSimpleOperator.class);
                });
    }

    @Test
    public void createBeans_httpLogCacheOperation_successCreateBean() {
        context.withPropertyValues("http-log.cache=true")
                .run(context -> {
                    assertThat(context).hasSingleBean(HttpLogCachedOperator.class);
                });
    }

    @Test
    public void createBeans_httpLogInterceptor_successCreateBeanAndRegistryNewInterceptor() {
        context.withPropertyValues("http-log.enabled=true")
                .run(context -> {
                    assertThat(context).hasSingleBean(HttpLogSimpleOperator.class);
                    assertThat(context).hasSingleBean(HttpLogInterceptor.class);
                    assertThat(context).hasSingleBean(WebMvcConfigurer.class);

                    WebMvcConfigurer mvcConf = context.getBean(WebMvcConfigurer.class);
                    InterceptorRegistry registry = mock(InterceptorRegistry.class);
                    mvcConf.addInterceptors(registry);
                    verify(registry).addInterceptor(any(HandlerInterceptor.class));
                });
    }

    @Test
    public void createBeans_httpLogInterceptor_doNotCreateBean() {
        context.withPropertyValues("http-log.enabled=false")
                .run(context -> {
                    assertThat(context).doesNotHaveBean(HttpLogInterceptor.class);
                    assertThat(context).doesNotHaveBean(WebMvcConfigurer.class);
                });
    }
}
