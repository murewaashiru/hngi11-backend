package com.example.visitor.utils;

import jakarta.servlet.http.HttpServletRequest;

public class HelperUtils {
    public static String getClientIp(HttpServletRequest request) {
        String clientIp = "";

        if (request != null) {
            clientIp = request.getHeader("X-FORWARDED-FOR");
        }

        if ((clientIp == null || clientIp.isEmpty()) && request != null) {
            clientIp = request.getRemoteAddr();
        }

        return clientIp;
    }
}
