package com.andes.preat.presentation;

import com.andes.preat.dto.response.common.BaseExceptionResponse;
import com.andes.preat.exception.badRequest.*;
import com.andes.preat.exception.invalidToken.ExpiredTokenException;
import com.andes.preat.exception.invalidToken.InvalidTokenFormException;
import com.andes.preat.exception.invalidToken.NotFoundTokenFromHeaderException;
import com.andes.preat.exception.invalidToken.NotRequiredTokenException;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidTokenFormException.class)
    public ResponseEntity<BaseExceptionResponse> handleInvalidTokenFormException(final InvalidTokenFormException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(BaseExceptionResponse.of(e));
    }
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<BaseExceptionResponse> handleFeignException(final FeignException e) {
        return ResponseEntity.status(e.status()).body(new BaseExceptionResponse(e.getMessage(), e.status(), null));
    }
    @ExceptionHandler(ExpiredTokenException.class)
    public ResponseEntity<BaseExceptionResponse> handleExpiredTokenException(final ExpiredTokenException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(BaseExceptionResponse.of(e));
    }
    @ExceptionHandler(NotRequiredTokenException.class)
    public ResponseEntity<BaseExceptionResponse> handleNotRequiredTokenException(final NotRequiredTokenException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(BaseExceptionResponse.of(e));
    }
    @ExceptionHandler(NotFoundTokenFromHeaderException.class)
    public ResponseEntity<BaseExceptionResponse> handleNotFoundTokenFromHeaderException(final NotFoundTokenFromHeaderException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(BaseExceptionResponse.of(e));
    }
    @ExceptionHandler(NotFoundUserException.class)
    public ResponseEntity<BaseExceptionResponse> handleNotFoundException(final NotFoundUserException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BaseExceptionResponse.of(e));
    }
    @ExceptionHandler(NotFoundPlatformException.class)
    public ResponseEntity<BaseExceptionResponse> handleNotFoundPlatformException(final NotFoundPlatformException e) {
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
    @ExceptionHandler(DuplicateNicknameException.class)
    public ResponseEntity<BaseExceptionResponse> handleDuplicateNicknameException(final DuplicateNicknameException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BaseExceptionResponse.of(e));
    }
    @ExceptionHandler(NotFoundHateFoodException.class)
    public ResponseEntity<BaseExceptionResponse> handleNotFoundHateFoodException(final NotFoundHateFoodException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BaseExceptionResponse.of(e));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseExceptionResponse> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseExceptionResponse(e.getMessage(),400, null));
    }
    @ExceptionHandler(InvalidRatingValueException.class)
    public ResponseEntity<BaseExceptionResponse> handleInvalidRatingValueException(final InvalidRatingValueException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BaseExceptionResponse.of(e));
    }
    @ExceptionHandler(AlreadyWrittenReviewException.class)
    public ResponseEntity<BaseExceptionResponse> handleAlreadyWrittenReviewException(final AlreadyWrittenReviewException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BaseExceptionResponse.of(e));
    }
    @ExceptionHandler(RegisterNotCompletedException.class)
    public ResponseEntity<BaseExceptionResponse> handleRegisterNotCompletedException(final RegisterNotCompletedException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(BaseExceptionResponse.of(e));
    }
    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<BaseExceptionResponse> handleRestaurantNotFoundException(final RestaurantNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BaseExceptionResponse.of(e));
    }
    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<BaseExceptionResponse> handleReviewNotFoundException(final ReviewNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BaseExceptionResponse.of(e));
    }
    @ExceptionHandler(NotAuthorException.class)
    public ResponseEntity<BaseExceptionResponse> handleNotAuthorException(final NotAuthorException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BaseExceptionResponse.of(e));
    }
    @ExceptionHandler(AlreadyWrittenUserWishException.class)
    public ResponseEntity<BaseExceptionResponse> handleAlreadyWrittenUserWishException(final AlreadyWrittenUserWishException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BaseExceptionResponse.of(e));
    }
    @ExceptionHandler(UserWishNotFoundException.class)
    public ResponseEntity<BaseExceptionResponse> handleUserWishNotFoundException(final UserWishNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BaseExceptionResponse.of(e));
    }
}
