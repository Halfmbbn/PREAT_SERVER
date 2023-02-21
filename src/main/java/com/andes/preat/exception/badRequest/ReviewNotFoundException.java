package com.andes.preat.exception.badRequest;

import com.andes.preat.exception.CustomException;
import com.andes.preat.exception.ErrorStatusCode;

import static com.andes.preat.exception.ErrorStatusCode.REVIEWS_NOT_FOUND;

public class ReviewNotFoundException extends CustomException {
    public ReviewNotFoundException() {
        super(REVIEWS_NOT_FOUND);
    }
}
