package com.andes.preat.exception.invalidToken;

import com.andes.preat.exception.CustomException;

import static com.andes.preat.exception.ErrorStatusCode.TOKEN_INVALID_FORM;

public class InvalidTokenForm extends CustomException {
    public InvalidTokenForm() {
        super(TOKEN_INVALID_FORM);
    }
}
