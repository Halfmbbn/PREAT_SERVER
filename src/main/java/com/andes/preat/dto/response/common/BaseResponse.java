package com.andes.preat.dto.response.common;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"status", "message", "data"})
public class BaseResponse<T> {
    private final String message;
    private final int status;
    private T data;

    // 요청에 성공한 경우
    public BaseResponse(T data) {
        this.message = "OK";
        this.status = 200;
        this.data = data;
    }
}
