package com.andes.preat.exception.badRequest;

import com.andes.preat.exception.CustomException;
import com.andes.preat.exception.ErrorStatusCode;

import static com.andes.preat.exception.ErrorStatusCode.REVIEWS_NOT_AUTHOR;

public class NotAuthorException extends CustomException {
    public NotAuthorException() {
        super(REVIEWS_NOT_AUTHOR);
    }
}
