package com.andes.preat.exception.badRequest;

import com.andes.preat.exception.CustomException;
import com.andes.preat.exception.ErrorStatusCode;

import static com.andes.preat.exception.ErrorStatusCode.USER_NOT_FOUND;

public class NotFoundUser extends CustomException {
    public NotFoundUser() {
        super(USER_NOT_FOUND);
    }
}
