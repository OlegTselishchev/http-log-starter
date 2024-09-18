package com.example.httploger.init;

import com.example.httploger.exception.HttpLogException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

@Slf4j
public class HttpLogEnvironmentPostProcessor implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        log.info("Post processor environment");
        String enablerProperty = environment.getProperty("http-log.enabled");
        String levelProperty = environment.getProperty("http-log.level");

        if (enablerProperty == null) {
            throw new HttpLogException("http-log.enabler не может быть null");
        }
        boolean isBoolean = Boolean.TRUE.toString().equalsIgnoreCase(enablerProperty)
                || Boolean.FALSE.toString().equalsIgnoreCase(enablerProperty);
        if (!isBoolean) {
            throw new HttpLogException(String.format("Ошибка для http-log.enabled=%s, допустимое значение true/false ",
                    enablerProperty));
        }

        if (levelProperty == null) {
            throw new HttpLogException("http-log.level не может быть null");
        }
        boolean isLevel = levelProperty.equals("INFO") || levelProperty.equals("WARN");
        if (!isLevel) {
            throw new HttpLogException(String.format("Ошибка для http-log.level=%s, допустимое значение WARN/INFO",
                    levelProperty));
        }
    }
}
