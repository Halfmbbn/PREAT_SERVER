package com.andes.preat.exception.badRequest;

import com.andes.preat.exception.CustomException;
import com.andes.preat.exception.ErrorStatusCode;

import static com.andes.preat.exception.ErrorStatusCode.RESTAURANT_NOT_FOUND;

public class RestaurantNotFoundException extends CustomException {
    public RestaurantNotFoundException() {
        super(RESTAURANT_NOT_FOUND);
    }
}
