package com.andes.preat.dto.response.common;

import com.andes.preat.exception.CustomException;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"status", "message", "data"})
public class BaseExceptionResponse<T> {
    private final String message;
    private final int status;
    private T data;

    // 요청에 실패한 경우
    public static BaseExceptionResponse of(CustomException e) {
        return new BaseExceptionResponse(e.getErrorCode().getMessage(), e.getErrorCode().getStatus(), null);
    }
}
