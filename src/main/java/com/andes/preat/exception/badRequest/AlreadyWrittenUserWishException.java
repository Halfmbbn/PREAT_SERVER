package com.andes.preat.exception.badRequest;

import com.andes.preat.exception.CustomException;
import com.andes.preat.exception.ErrorStatusCode;

import static com.andes.preat.exception.ErrorStatusCode.USER_WISHES_ALREADY_EXISTS;

public class AlreadyWrittenUserWishException extends CustomException {
    public AlreadyWrittenUserWishException() {
        super(USER_WISHES_ALREADY_EXISTS);
    }
}
