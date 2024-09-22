package com.example.httploger.server.log.impl;

import com.example.httploger.model.HttpLog;
import com.example.httploger.server.log.HttpLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import static org.slf4j.event.Level.INFO;

public class HttpLogServiceInfo implements HttpLogService {

    private final Level level = INFO;
    private static final Logger log = LoggerFactory.getLogger(HttpLogServiceInfo.class);

    @Override
    public void printHttpLog(HttpLog httpLog) {
        log.info("HTTP LOG: {}", httpLog);
    }

    @Override
    public boolean isApplicable(Level level) {
        return this.level.equals(level);
    }
}
