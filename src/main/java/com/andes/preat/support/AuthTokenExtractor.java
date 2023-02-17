package com.andes.preat.support;

import org.springframework.stereotype.Component;

@Component
public class AuthTokenExtractor {
    public String extractToken(final String authorizationHeader, final String tokenType) {
        if (authorizationHeader == null) {
            throw new IllegalArgumentException("헤더 정보가 없습니다");
        }
        final String[] splitHeaders = authorizationHeader.split(" ");
        if (splitHeaders.length != 2 || !splitHeaders[0].equalsIgnoreCase(tokenType)) {
            throw new IllegalArgumentException("헤더 정보가 올바르지 않습니다.");
        }
        return splitHeaders[1];
    }
}
