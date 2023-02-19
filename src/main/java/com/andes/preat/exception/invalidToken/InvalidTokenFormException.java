package com.andes.preat.exception.invalidToken;

import com.andes.preat.exception.CustomException;

import static com.andes.preat.exception.ErrorStatusCode.TOKEN_INVALID_FORM;

public class InvalidTokenFormException extends CustomException {
    public InvalidTokenFormException() {
        super(TOKEN_INVALID_FORM);
    }
}
