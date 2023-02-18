package com.andes.preat.support;

import com.andes.preat.exception.invalidToken.InvalidTokenForm;
import com.andes.preat.exception.invalidToken.NotFoundTokenFromHeader;
import org.springframework.stereotype.Component;

@Component
public class AuthTokenExtractor {
    public String extractToken(final String authorizationHeader, final String tokenType) {
        if (authorizationHeader == null) {
            throw new NotFoundTokenFromHeader();
        }
        final String[] splitHeaders = authorizationHeader.split(" ");
        if (splitHeaders.length != 2 || !splitHeaders[0].equalsIgnoreCase(tokenType)) {
            throw new InvalidTokenForm();
        }
        return splitHeaders[1];
    }
}
