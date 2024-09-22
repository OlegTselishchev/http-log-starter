package com.example.httploger.server.log.impl;

import com.example.httploger.server.log.HttpLogService;
import org.slf4j.event.Level;

public abstract class AbstractHttpLogService implements HttpLogService {

    private final Level level;


    public AbstractHttpLogService(Level level) {
        this.level = level;
    }

    @Override
    public boolean isApplicable(Level level) {
        return this.level.equals(level);
    }
}
