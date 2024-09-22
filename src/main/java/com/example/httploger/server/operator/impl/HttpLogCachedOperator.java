package com.example.httploger.server.operator.impl;

import com.example.httploger.config.HttpLogProperties;
import com.example.httploger.model.HttpLog;
import com.example.httploger.server.log.HttpLogService;

import java.util.List;

public class HttpLogCachedOperator extends AbstractHttpLogOperator {

    private HttpLogService currentHttpLogService = null;
    private Boolean isLevelModified = false;

    public HttpLogCachedOperator(List<HttpLogService> httpLogService, HttpLogProperties httpLogProperties) {
        super(httpLogService, httpLogProperties);
    }

    @Override
    public void logForLevel(HttpLog httpLog) {
        if (currentHttpLogService == null || isLevelModified) {
            currentHttpLogService = serviceSelector();
            isLevelModified = false;
        }
        printHttpLog(httpLog, currentHttpLogService);
    }

    @Override
    public void editedLevel(String level) {
        super.editedLevel(level);
        isLevelModified = true;
    }
}
