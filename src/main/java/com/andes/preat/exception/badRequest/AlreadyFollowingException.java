package com.andes.preat.exception.badRequest;

import com.andes.preat.exception.CustomException;
import com.andes.preat.exception.ErrorStatusCode;

import static com.andes.preat.exception.ErrorStatusCode.FOLLOW_ALREADY_FOLLOWING;

public class AlreadyFollowingException extends CustomException {
    public AlreadyFollowingException() {
        super(FOLLOW_ALREADY_FOLLOWING);
    }
}
