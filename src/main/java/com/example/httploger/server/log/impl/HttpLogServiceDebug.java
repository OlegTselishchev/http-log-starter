package com.example.httploger.server.log.impl;

import com.example.httploger.model.HttpLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.slf4j.event.Level.DEBUG;

public class HttpLogServiceDebug extends AbstractHttpLogService {

    private static final Logger log = LoggerFactory.getLogger(HttpLogServiceDebug.class);

    public HttpLogServiceDebug() {
        super(DEBUG);
    }

    @Override
    public void printHttpLog(HttpLog httpLog) {
        log.debug("HTTP LOG: {}", httpLog);
    }
}
