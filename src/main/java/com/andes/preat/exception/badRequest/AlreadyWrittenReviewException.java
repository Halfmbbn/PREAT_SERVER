package com.andes.preat.exception.badRequest;

import com.andes.preat.exception.CustomException;
import com.andes.preat.exception.ErrorStatusCode;

import static com.andes.preat.exception.ErrorStatusCode.REVIEWS_ALREADY_EXISTS;

public class AlreadyWrittenReviewException extends CustomException {
    public AlreadyWrittenReviewException() {
        super(REVIEWS_ALREADY_EXISTS);
    }
}
