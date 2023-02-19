package com.andes.preat.exception.badRequest;

import com.andes.preat.exception.CustomException;
import com.andes.preat.exception.ErrorStatusCode;

import static com.andes.preat.exception.ErrorStatusCode.FOLLOW_CANT_SELF_FOLLOW;

public class SelfFollowException extends CustomException {
    public SelfFollowException() {
        super(FOLLOW_CANT_SELF_FOLLOW);
    }
}
