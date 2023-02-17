package com.andes.preat.presentation;

import com.andes.preat.dto.response.common.BaseExceptionResponse;
import com.andes.preat.exception.invalidToken.InvalidTokenForm;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidTokenForm.class)
    public ResponseEntity<BaseExceptionResponse> handleInvalidTokenFormException(final InvalidTokenForm e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(BaseExceptionResponse.of(e));
    }
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<BaseExceptionResponse> handleFeignException(final FeignException e) {
        return ResponseEntity.status(e.status()).body(new BaseExceptionResponse(e.getMessage(), e.status(), null));
    }

}
