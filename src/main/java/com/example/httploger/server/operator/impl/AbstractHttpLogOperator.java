package com.example.httploger.server.operator.impl;

import com.example.httploger.config.HttpLogProperties;
import com.example.httploger.model.HttpLog;
import com.example.httploger.server.operator.HttpLogOperator;
import com.example.httploger.server.log.HttpLogService;
import org.slf4j.event.Level;
import java.util.List;

import static org.slf4j.event.Level.ERROR;
import static org.slf4j.event.Level.INFO;


public abstract class AbstractHttpLogOperator implements HttpLogOperator {
    private final List<HttpLogService> httpLogService;
    private final HttpLogProperties httpLogProperties;

    private final HttpLogService defaultLogLeve;
    private final HttpLogService onErrorLogLevel;

    public AbstractHttpLogOperator(List<HttpLogService> httpLogService, HttpLogProperties httpLogProperties) {
        this.httpLogService = httpLogService;
        this.httpLogProperties = httpLogProperties;
        this.defaultLogLeve = serviceSelector(INFO);
        this.onErrorLogLevel = serviceSelector(ERROR);
    }

    @Override
    public void editedLevel(String level) {
        httpLogProperties.setLevel(level);
    }

    @Override
    public void setupEnabled(Boolean enabled) {
        httpLogProperties.setEnabled(enabled);
    }

    @Override
    public String getHttpLogProperties() {
        return httpLogProperties.toString();
    }

    public void printHttpLog(HttpLog httpLog, HttpLogService logService) {
        if (httpLogProperties.getEnabled()) {
            if (httpLog.getStatus() == 200) {
                logService.printHttpLog(httpLog);
            } else {
                onErrorLogLevel.printHttpLog(httpLog);
            }
        }
    }

    protected HttpLogService serviceSelector() {
        return serviceSelector(convertLevel(httpLogProperties.getLevel()));
    }

    private HttpLogService serviceSelector(Level level){
        return httpLogService.stream().filter(e -> e.isApplicable(level)).findFirst().orElse(defaultLogLeve);
    }

    private Level convertLevel(String level) {
        return Level.valueOf(level);
    }
}
