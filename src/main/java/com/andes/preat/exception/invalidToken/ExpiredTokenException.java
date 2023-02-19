package com.andes.preat.exception.invalidToken;

import com.andes.preat.exception.CustomException;

import static com.andes.preat.exception.ErrorStatusCode.TOKEN_EXPIRED;

public class ExpiredTokenException extends CustomException {
    public ExpiredTokenException() {
        super(TOKEN_EXPIRED);
    }
}
