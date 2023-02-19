package com.andes.preat.exception.badRequest;

import com.andes.preat.exception.CustomException;

import static com.andes.preat.exception.ErrorStatusCode.USER_NOT_FOUND;

public class NotFoundUserException extends CustomException {
    public NotFoundUserException() {
        super(USER_NOT_FOUND);
    }
}
