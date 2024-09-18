package com.example.httploger.server;

import com.example.httploger.model.HttpLog;

public interface HttpLogService {
    void printHttpLog(HttpLog httpLog);
}
