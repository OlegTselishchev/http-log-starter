package com.example.httploger.server.log.impl;

import com.example.httploger.model.HttpLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.slf4j.event.Level.WARN;

public class HttpLogServiceWarn extends AbstractHttpLogService {

    private static final Logger log = LoggerFactory.getLogger(HttpLogServiceInfo.class);

    public HttpLogServiceWarn() {
        super(WARN);
    }

    @Override
    public void printHttpLog(HttpLog httpLog) {
        log.warn("HTTP LOG: {}", httpLog);
    }

}
