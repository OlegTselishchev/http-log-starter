package com.example.httploger.server.log;

import com.example.httploger.model.HttpLog;
import org.slf4j.event.Level;

public interface HttpLogService {
    void printHttpLog(HttpLog httpLog);
    boolean isApplicable(Level level);
}
