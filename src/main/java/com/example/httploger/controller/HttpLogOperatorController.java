package com.example.httploger.controller;

import com.example.httploger.server.operator.HttpLogOperator;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.event.Level;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/rest/")
@RequiredArgsConstructor
public class HttpLogOperatorController {
    private final HttpLogOperator logOperator;
    private static String LOG_LEVEL_RESPONSE = "Setup log level: %s";
    private static String LOG_ENABLED_RESPONSE = "Http logging: %s";

    @Operation(description = "Get http-log property.")
    @GetMapping("/properties")
    public String getHttpLogProperties() {
        return logOperator.getHttpLogProperties();
    }

    @Operation(description = "Http logging enabled.")
    @GetMapping("/logging/enabled")
    public String logEnabled() {
        logOperator.setupEnabled(true);
        return String.format(LOG_ENABLED_RESPONSE, true);
    }

    @Operation(description = "Http logging disabled.")
    @GetMapping("/logging/disabled")
    public String logDisabled() {
        logOperator.setupEnabled(false);
        return String.format(LOG_ENABLED_RESPONSE, false);
    }

    @Operation(description = "Set log level INFO")
    @GetMapping("/level/info")
    public String logLevelInfo() {
        logOperator.editedLevel(Level.INFO.toString());
        return String.format(LOG_LEVEL_RESPONSE, Level.INFO);
    }

    @Operation(description = "Set log level WARN")
    @GetMapping("/level/warn")
    public String logLevelWarn() {
        logOperator.editedLevel(Level.WARN.toString());
        return String.format(LOG_LEVEL_RESPONSE, Level.WARN);
    }

    @Operation(description = "Set log level DEBUG")
    @GetMapping("/level/debug")
    public String logLevelDebug() {
        logOperator.editedLevel(Level.DEBUG.toString());
        return String.format(LOG_LEVEL_RESPONSE, Level.DEBUG);
    }

    @Operation(description = "Set log level TRACE")
    @GetMapping("/level/trace")
    public String logLevelTrace() {
        logOperator.editedLevel(Level.TRACE.toString());
        return String.format(LOG_LEVEL_RESPONSE, Level.TRACE);
    }

    @Operation(description = "Set log level ERROR")
    @GetMapping("/level/error")
    public String logLevelError() {
        logOperator.editedLevel(Level.ERROR.toString());
        return String.format(LOG_LEVEL_RESPONSE, Level.ERROR);
    }
}
