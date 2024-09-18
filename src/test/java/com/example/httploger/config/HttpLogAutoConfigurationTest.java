package com.example.httploger.config;

import com.example.httploger.exception.HttpLogException;
import com.example.httploger.init.HttpLogEnvironmentPostProcessor;
import com.example.httploger.intercepter.HttpLogInterceptor;
import com.example.httploger.server.impl.HttpLogServiceInfo;
import com.example.httploger.server.impl.HttpLogServiceWarn;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

//@ExtendWith(SpringExtension.class)
public class HttpLogAutoConfigurationTest {

    private final ApplicationContextRunner context = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(HttpLogAutoConfiguration.class));

    @Test
    public void createBeans_httpLogServiceInfo_successCreateBean() {
        context.withPropertyValues( "http-log.enabled=true", "http-log.level=INFO")
                .run(context -> {
                    assertThat(context).hasSingleBean(HttpLogServiceInfo.class);
                });
    }

    @Test
    public void createBeans_httpLogServiceWarn_successCreateBean() {
        context.withPropertyValues( "http-log.enabled=true", "http-log.level=WARN")
                .run(context -> {
                    assertThat(context).hasSingleBean(HttpLogServiceWarn.class);
                });
    }

    @Test
    public void createBeans_httpLogInterceptor_successCreateBean() {
        context.withPropertyValues("http-log.enabled=true", "http-log.level=INFO")
                .run(context -> {
                    assertThat(context).hasSingleBean(HttpLogServiceInfo.class);
                    assertThat(context).hasSingleBean(HttpLogInterceptor.class);
                    assertThat(context).hasSingleBean(WebMvcConfigurer.class);

                    WebMvcConfigurer mvcConf = context.getBean(WebMvcConfigurer.class);
                    InterceptorRegistry registry = mock(InterceptorRegistry.class);
                    mvcConf.addInterceptors(registry);
                    verify(registry).addInterceptor(any(HandlerInterceptor.class));
                });
    }

    @Test
    public void createBeans_httpLogInterceptor_dontCreateBean() {
        context.withPropertyValues("http-log.enabled=false")
                .run(context -> {
                    assertThat(context).doesNotHaveBean(HttpLogInterceptor.class);
                    assertThat(context).doesNotHaveBean(WebMvcConfigurer.class);
                });
    }

    @Test
    public void checkProperties_httpLogEnvironmentPostProcessor_enabledParamIsNull() {
        StandardEnvironment standardEnvironment = new StandardEnvironment();
        standardEnvironment
                .getPropertySources()
                .addFirst(new MapPropertySource("test",
                        Collections.singletonMap("http-log.enabled", null)));

        HttpLogEnvironmentPostProcessor postProcessor = new HttpLogEnvironmentPostProcessor();
        assertThatExceptionOfType(HttpLogException.class)
                .isThrownBy(() -> postProcessor.postProcessEnvironment(standardEnvironment, new SpringApplication()))
                .withMessageContaining("http-log.enabler не может быть null");
    }


    @Test
    public void checkProperties_httpLogEnvironmentPostProcessor_enabledParamIsIncorrect() {
        String errorEnabled = "truee";
        StandardEnvironment standardEnvironment = new StandardEnvironment();
        standardEnvironment
                .getPropertySources()
                .addFirst(new MapPropertySource("test",
                        Collections.singletonMap("http-log.enabled", errorEnabled)));

        HttpLogEnvironmentPostProcessor postProcessor = new HttpLogEnvironmentPostProcessor();
        assertThatExceptionOfType(HttpLogException.class)
                .isThrownBy(() -> postProcessor.postProcessEnvironment(standardEnvironment, new SpringApplication()))
                .withMessageContaining(String.format("Ошибка для http-log.enabled=%s, допустимое значение true/false ",
                        errorEnabled));
    }


    @Test
    public void checkProperties_httpLogEnvironmentPostProcessor_levelParamIsNull() {
        StandardEnvironment standardEnvironment = new StandardEnvironment();
        standardEnvironment
                .getPropertySources()
                .addFirst(new MapPropertySource("test",
                        Collections.singletonMap("http-log.enabled", "true")));

        HttpLogEnvironmentPostProcessor postProcessor = new HttpLogEnvironmentPostProcessor();
        assertThatExceptionOfType(HttpLogException.class)
                .isThrownBy(() -> postProcessor.postProcessEnvironment(standardEnvironment, new SpringApplication()))
                .withMessageContaining("http-log.level не может быть null");
    }

    @Test
    public void checkProperties_httpLogEnvironmentPostProcessor_levelParamIsIncorrect() {
        String errorLevel = "INF";
        Map<String, Object> properties = new HashMap<>();
        properties.put("http-log.enabled", "true");
        properties.put("http-log.level", errorLevel);

        StandardEnvironment standardEnvironment = new StandardEnvironment();
        standardEnvironment
                .getPropertySources()
                .addFirst(new MapPropertySource("test", properties));

        HttpLogEnvironmentPostProcessor postProcessor = new HttpLogEnvironmentPostProcessor();
        assertThatExceptionOfType(HttpLogException.class)
                .isThrownBy(() -> postProcessor.postProcessEnvironment(standardEnvironment, new SpringApplication()))
                .withMessageContaining(String.format("Ошибка для http-log.level=%s, допустимое значение WARN/INFO",
                        errorLevel));
    }
}
