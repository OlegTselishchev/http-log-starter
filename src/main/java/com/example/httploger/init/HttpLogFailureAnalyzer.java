package com.example.httploger.init;

import com.example.httploger.exception.HttpLogException;
import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

public class HttpLogFailureAnalyzer extends AbstractFailureAnalyzer<HttpLogException> {
    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, HttpLogException cause) {
        return new FailureAnalysis(cause.getMessage(),
                "Укажи валидные значения для http-log", cause);
    }
}
