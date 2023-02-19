package com.andes.preat.exception.badRequest;

import com.andes.preat.exception.CustomException;
import com.andes.preat.exception.ErrorStatusCode;

import static com.andes.preat.exception.ErrorStatusCode.FOLLOW_NOT_FOUND_FOLLOW;

public class NotFollowingException extends CustomException {
    public NotFollowingException() {
        super(FOLLOW_NOT_FOUND_FOLLOW);
    }
}
