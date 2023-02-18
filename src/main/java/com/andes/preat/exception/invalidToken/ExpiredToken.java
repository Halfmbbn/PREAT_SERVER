package com.andes.preat.exception.invalidToken;

import com.andes.preat.exception.CustomException;

import static com.andes.preat.exception.ErrorStatusCode.TOKEN_EXPIRED;

public class ExpiredToken extends CustomException {
    public ExpiredToken() {
        super(TOKEN_EXPIRED);
    }
}
