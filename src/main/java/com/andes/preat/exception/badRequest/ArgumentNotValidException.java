package com.andes.preat.exception.badRequest;

import com.andes.preat.exception.CustomException;

import static com.andes.preat.exception.ErrorStatusCode.METHOD_ARGUMENT_NOT_VALID;

public class ArgumentNotValidException extends CustomException {
    public ArgumentNotValidException() {
        super(METHOD_ARGUMENT_NOT_VALID);
    }
}
