package com.example.httploger.server.impl;

import com.example.httploger.model.HttpLog;
import com.example.httploger.server.HttpLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpLogServiceWarn implements HttpLogService {
    private static final Logger log = LoggerFactory.getLogger(HttpLogServiceInfo.class);

    @Override
    public void printHttpLog(HttpLog httpLog) {
        log.warn("HTTP LOG: {}", httpLog);
    }
}
