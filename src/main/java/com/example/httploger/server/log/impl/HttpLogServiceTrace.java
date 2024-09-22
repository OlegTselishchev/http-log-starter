package com.example.httploger.server.log.impl;

import com.example.httploger.model.HttpLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.slf4j.event.Level.TRACE;

public class HttpLogServiceTrace extends AbstractHttpLogService {

    private static final Logger log = LoggerFactory.getLogger(HttpLogServiceTrace.class);

    public HttpLogServiceTrace() {
        super(TRACE);
    }

    @Override
    public void printHttpLog(HttpLog httpLog) {
        log.trace("HTTP LOG: {}", httpLog);
    }
}
