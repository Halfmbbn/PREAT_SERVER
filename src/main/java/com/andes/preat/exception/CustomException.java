package com.andes.preat.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private final ErrorStatusCode errorCode;

    public CustomException(String message, final ErrorStatusCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    public CustomException(final ErrorStatusCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
