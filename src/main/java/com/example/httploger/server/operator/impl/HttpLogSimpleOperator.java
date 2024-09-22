package com.example.httploger.server.operator.impl;

import com.example.httploger.config.HttpLogProperties;
import com.example.httploger.model.HttpLog;
import com.example.httploger.server.log.HttpLogService;

import java.util.List;

public class HttpLogSimpleOperator extends AbstractHttpLogOperator {

    public HttpLogSimpleOperator(List<HttpLogService> httpLogService, HttpLogProperties httpLogProperties) {
        super(httpLogService, httpLogProperties);
    }

    @Override
    public void logForLevel(HttpLog httpLog) {
        printHttpLog(httpLog, serviceSelector());
    }

    @Override
    public void editedLevel(String level) {
        super.editedLevel(level);
    }
}
