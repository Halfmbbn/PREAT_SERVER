package com.andes.preat.exception.badRequest;

import com.andes.preat.exception.CustomException;
import com.andes.preat.exception.ErrorStatusCode;

import static com.andes.preat.exception.ErrorStatusCode.REVIEWS_INVALID_RATING_VALUE;

public class InvalidRatingValueException extends CustomException {
    public InvalidRatingValueException() {
        super(REVIEWS_INVALID_RATING_VALUE);
    }
}
