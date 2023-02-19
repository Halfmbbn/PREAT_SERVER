package com.andes.preat.exception.badRequest;

import com.andes.preat.exception.CustomException;
import com.andes.preat.exception.ErrorStatusCode;

import static com.andes.preat.exception.ErrorStatusCode.HATE_FOOD_NOT_FOUND;

public class NotFoundHateFoodException extends CustomException {
    public NotFoundHateFoodException() {
        super(HATE_FOOD_NOT_FOUND);
    }
}
