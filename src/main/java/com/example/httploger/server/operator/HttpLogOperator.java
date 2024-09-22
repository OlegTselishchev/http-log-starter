package com.example.httploger.server.operator;

import com.example.httploger.model.HttpLog;
import com.example.httploger.server.log.HttpLogService;


public interface HttpLogOperator {
    void logForLevel(HttpLog httpLog);
    void editedLevel(String level);
    void setupEnabled(Boolean enabled);
    String getHttpLogProperties();
}
