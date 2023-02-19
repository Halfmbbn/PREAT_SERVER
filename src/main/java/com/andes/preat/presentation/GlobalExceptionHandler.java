package com.andes.preat.presentation;

import com.andes.preat.dto.response.common.BaseExceptionResponse;
import com.andes.preat.exception.badRequest.*;
import com.andes.preat.exception.invalidToken.ExpiredToken;
import com.andes.preat.exception.invalidToken.InvalidTokenForm;
import com.andes.preat.exception.invalidToken.NotFoundTokenFromHeader;
import com.andes.preat.exception.invalidToken.NotRequiredToken;
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
    @ExceptionHandler(ExpiredToken.class)
    public ResponseEntity<BaseExceptionResponse> handleExpiredTokenException(final ExpiredToken e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(BaseExceptionResponse.of(e));
    }
    @ExceptionHandler(NotRequiredToken.class)
    public ResponseEntity<BaseExceptionResponse> handleNotRequiredTokenException(final NotRequiredToken e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(BaseExceptionResponse.of(e));
    }
    @ExceptionHandler(NotFoundTokenFromHeader.class)
    public ResponseEntity<BaseExceptionResponse> handleNotFoundTokenFromHeaderException(final NotFoundTokenFromHeader e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(BaseExceptionResponse.of(e));
    }
    @ExceptionHandler(NotFoundUser.class)
    public ResponseEntity<BaseExceptionResponse> handleNotFoundException(final NotFoundUser e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BaseExceptionResponse.of(e));
    }
    @ExceptionHandler(NotFoundPlatform.class)
    public ResponseEntity<BaseExceptionResponse> handleNotFoundPlatformException(final NotFoundPlatform e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BaseExceptionResponse.of(e));
    }
    @ExceptionHandler(SelfFollowException.class)
    public ResponseEntity<BaseExceptionResponse> handleSelfFollowException(final SelfFollowException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BaseExceptionResponse.of(e));
    }
    @ExceptionHandler(AlreadyFollowingException.class)
    public ResponseEntity<BaseExceptionResponse> handleAlreadyFollowingException(final AlreadyFollowingException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BaseExceptionResponse.of(e));
    }
    @ExceptionHandler(NotFollowingException.class)
    public ResponseEntity<BaseExceptionResponse> handleNotFollowingException(final NotFollowingException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BaseExceptionResponse.of(e));
    }

}
