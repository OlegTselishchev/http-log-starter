package com.example.httploger.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "http-log")
public class HttpLogProperties {
    private boolean enabled;
    private String level;
}
