package com.andes.preat.exception.badRequest;

import com.andes.preat.exception.CustomException;

import static com.andes.preat.exception.ErrorStatusCode.USER_NOT_FOUND_PLATFORM;

public class NotFoundPlatformException extends CustomException {
    public NotFoundPlatformException() {
        super(USER_NOT_FOUND_PLATFORM);
    }
}
