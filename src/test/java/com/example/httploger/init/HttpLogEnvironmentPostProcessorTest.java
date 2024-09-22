package com.example.httploger.init;

import com.example.httploger.exception.HttpLogException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.StandardEnvironment;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class HttpLogEnvironmentPostProcessorTest {

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
    public void checkProperties_httpLogEnvironmentPostProcessor_cacheParamIsIncorrect() {
        String errorEnabled = "fals";
        StandardEnvironment standardEnvironment = new StandardEnvironment();
        standardEnvironment
                .getPropertySources()
                .addFirst(new MapPropertySource("test",
                        Collections.singletonMap("http-log.cache", errorEnabled)));

        HttpLogEnvironmentPostProcessor postProcessor = new HttpLogEnvironmentPostProcessor();
        assertThatExceptionOfType(HttpLogException.class)
                .isThrownBy(() -> postProcessor.postProcessEnvironment(standardEnvironment, new SpringApplication()))
                .withMessageContaining(String.format("Ошибка для http-log.cache=%s, допустимое значение true/false ",
                        errorEnabled));
    }


    @Test
    public void checkProperties_httpLogEnvironmentPostProcessor_levelParamIsIncorrect() {
        String errorLevel = "INFF";
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
                .withMessageContaining(String.format("Ошибка для http-log.level = %s",
                        errorLevel));
    }
}
