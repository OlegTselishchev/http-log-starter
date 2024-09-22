package com.example.httploger.init;

import com.example.httploger.exception.HttpLogException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class HttpLogEnvironmentPostProcessor implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        log.info("Post processor environment");
        checkEnabledPropertyOrThrow(environment);
        checkCachePropertyOrThrow(environment);
        checkLevelPropertyOrThrow(environment);
    }

    private void checkLevelPropertyOrThrow(ConfigurableEnvironment environment) {
        String enabledProperty = environment.getProperty("http-log.enabled");
        if (enabledProperty != null) {
            boolean isBoolean = Boolean.TRUE.toString().equalsIgnoreCase(enabledProperty)
                    || Boolean.FALSE.toString().equalsIgnoreCase(enabledProperty);
            if (!isBoolean) {
                throw new HttpLogException(String.format("Ошибка для http-log.enabled=%s, допустимое значение true/false ",
                        enabledProperty));
            }
        }
    }

    private void checkCachePropertyOrThrow(ConfigurableEnvironment environment) {
        String cacheProperty = environment.getProperty("http-log.cache");
        if (cacheProperty != null) {
            boolean isBoolean = Boolean.TRUE.toString().equalsIgnoreCase(cacheProperty)
                    || Boolean.FALSE.toString().equalsIgnoreCase(cacheProperty);
            if (!isBoolean) {
                throw new HttpLogException(String.format("Ошибка для http-log.cache=%s, допустимое значение true/false ",
                        cacheProperty));
            }
        }
    }

    private void checkEnabledPropertyOrThrow(ConfigurableEnvironment environment) {
        String levelProperty = environment.getProperty("http-log.level");
        if (levelProperty != null) {
            List<String> levels = Arrays.asList("INFO", "WARN", "DEBUG", "TRACE", "ERROR");
            if (!levels.contains(levelProperty)) {
                throw new HttpLogException(String.format("Ошибка для http-log.level = %s",
                        levelProperty));
            }
        }
    }

}
