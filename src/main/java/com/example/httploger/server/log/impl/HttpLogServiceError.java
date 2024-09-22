package com.example.httploger.server.log.impl;

import com.example.httploger.model.HttpLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.slf4j.event.Level.ERROR;

public class HttpLogServiceError extends AbstractHttpLogService  {

    private static final Logger log = LoggerFactory.getLogger(HttpLogServiceError.class);

    public HttpLogServiceError() {
        super(ERROR);
    }

    @Override
    public void printHttpLog(HttpLog httpLog) {
        log.error("HTTP LOG: {}", httpLog);
    }
}
