package com.example.httploger.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "http-log")
public class HttpLogProperties {
    private Boolean enabled = false;
    private Boolean cache = false;
    private String level = "INFO";

    @Override
    public String toString() {
        return "enabled=" + enabled + "\n" +
                "cache=" + cache + "\n" +
                "level=" + level;
    }
}
