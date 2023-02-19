package com.andes.preat.exception.badRequest;

import com.andes.preat.exception.CustomException;

import static com.andes.preat.exception.ErrorStatusCode.USER_NICKNAME_DUPLICATED;

public class DuplicateNicknameException extends CustomException {
    public DuplicateNicknameException() {
        super(USER_NICKNAME_DUPLICATED);
    }
}
