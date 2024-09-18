package com.example.httploger.intercepter;

import com.example.httploger.model.HttpLog;

import com.example.httploger.server.HttpLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class HttpLogInterceptor implements HandlerInterceptor {
    private static final String START_TIMESTAMP = "startTimestamp";
    private static final Logger log = LoggerFactory.getLogger(HttpLogInterceptor.class);
    private HttpLogService httpLogService;

    public void setHttpLogService(HttpLogService httpLogService) {
        this.httpLogService = httpLogService;
    }

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long start = System.currentTimeMillis();
        request.setAttribute(START_TIMESTAMP, start);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception exception) throws Exception {
        long start = (long) request.getAttribute(START_TIMESTAMP);
        long end = System.currentTimeMillis();
        long workTimestamp = end - start;
        String method = request.getMethod();
        String url = request.getRequestURI();
        int status = response.getStatus();
        Map<String, String> requestHeaders = getRequestHeaders(request);
        Map<String, String> responseHeaders = getResponseHeaders(response);
        HttpLog httpLog = createHttpLog(status, method, url, workTimestamp, requestHeaders, responseHeaders);

        if (status == 200) {
            httpLogService.printHttpLog(httpLog);
        } else {
            log.error("HTTP-LOG: {}", httpLog);
        }
    }

    private Map<String, String> getRequestHeaders(HttpServletRequest request) {
        Map<String,String> requestHeaders = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            requestHeaders.put(headerName, headerValue);
        }
        return requestHeaders;
    }

    private Map<String, String> getResponseHeaders(HttpServletResponse response) {
        Map<String, String> responseHeaders = new HashMap<>();
        Collection<String> headerNames = response.getHeaderNames();
        for (String headerName : headerNames) {
            String headerValue = response.getHeader(headerName);
            responseHeaders.put(headerName, headerValue);
        }
        return responseHeaders;
    }

    private HttpLog createHttpLog(int status, String method, String url, long workTimestamp,
                                  Map<String, String> requestHeaders, Map<String, String> responseHeaders) {
        return new HttpLog(status, method, url, workTimestamp, requestHeaders, responseHeaders);
    }
}
