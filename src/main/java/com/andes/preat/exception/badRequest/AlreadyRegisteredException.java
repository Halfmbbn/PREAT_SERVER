package com.andes.preat.exception.badRequest;

import com.andes.preat.exception.CustomException;
import com.andes.preat.exception.ErrorStatusCode;

import static com.andes.preat.exception.ErrorStatusCode.USER_ALREADY_REGISTERED;

public class AlreadyRegisteredException extends CustomException {
    public AlreadyRegisteredException() {
        super(USER_ALREADY_REGISTERED);
    }
}
