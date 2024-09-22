package com.example.httploger.model;

import lombok.*;

import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HttpLog {
    private int status;
    private String method;
    private String url;
    private long workTimestamp;
    private Map<String, String> requestHeader;
    private Map<String, String> responseHeader;


    @Override
    public String toString() {
        return "\n" +
                "-------------------------------------------" + "\n" +
                "Status: " + status + "\n" +
                "Method: " + method + "\n" +
                "Url: " + url + "\n" +
                "Work_Timestamp: " + workTimestamp + "\n" +
                "Request_Header: " + requestHeader + "\n" +
                "Response_Header: " + responseHeader + "\n" +
                "-------------------------------------------";
    }
}
