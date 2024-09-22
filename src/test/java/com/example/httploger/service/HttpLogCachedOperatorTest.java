package com.example.httploger.service;

import com.example.httploger.config.HttpLogProperties;
import com.example.httploger.server.operator.HttpLogOperator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@SpringBootTest(properties = {
        "http-log.enabled=true",
        "http-log.cache=true",
        "http-log.level=TRACE"
})
@ExtendWith(SpringExtension.class)
public class HttpLogCachedOperatorTest {

    @Autowired
    private HttpLogOperator httpLogCachedOperator;

    @Test
    void getHttpLogProperties_success() {
        HttpLogProperties expectedHttpLogProperties = new HttpLogProperties(true, true, "TRACE");
        String actualHttpLogProperties = httpLogCachedOperator.getHttpLogProperties();
        Assertions.assertEquals(expectedHttpLogProperties.toString(), actualHttpLogProperties);
    }

    @Test
    void editedEnabler_success() {
        httpLogCachedOperator.setupEnabled(false);
        String actualHttpLogProperties = httpLogCachedOperator.getHttpLogProperties();
        Assertions.assertTrue(actualHttpLogProperties.contains("enabled=false"));
        httpLogCachedOperator.setupEnabled(true);
    }

    @Test
    void editedLogLevel_success() {
        httpLogCachedOperator.editedLevel("ERROR");
        String actualHttpLogProperties2 = httpLogCachedOperator.getHttpLogProperties();
        Assertions.assertTrue(actualHttpLogProperties2.contains("level=ERROR"));
        httpLogCachedOperator.editedLevel("TRACE");
    }
}
