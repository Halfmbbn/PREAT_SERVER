package com.andes.preat.exception.badRequest;

import com.andes.preat.exception.CustomException;
import com.andes.preat.exception.ErrorStatusCode;

import static com.andes.preat.exception.ErrorStatusCode.USER_WISHES_NOT_FOUND;

public class UserWishNotFoundException extends CustomException {
    public UserWishNotFoundException() {
        super(USER_WISHES_NOT_FOUND);
    }
}
