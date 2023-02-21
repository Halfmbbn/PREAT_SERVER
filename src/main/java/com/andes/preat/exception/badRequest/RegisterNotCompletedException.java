package com.andes.preat.exception.badRequest;

import com.andes.preat.exception.CustomException;
import com.andes.preat.exception.ErrorStatusCode;

import static com.andes.preat.exception.ErrorStatusCode.USER_NOT_COMPLETE_REGISTER;

public class RegisterNotCompletedException extends CustomException {
    public RegisterNotCompletedException() {
        super(USER_NOT_COMPLETE_REGISTER);
    }
}
