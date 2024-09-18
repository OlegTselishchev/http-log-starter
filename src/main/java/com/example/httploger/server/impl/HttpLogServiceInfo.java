package com.example.httploger.server.impl;

import com.example.httploger.model.HttpLog;
import com.example.httploger.server.HttpLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpLogServiceInfo implements HttpLogService {
    private static final Logger log = LoggerFactory.getLogger(HttpLogServiceInfo.class);

    @Override
    public void printHttpLog(HttpLog httpLog) {
        log.info("HTTP LOG: {}", httpLog);
    }
}
