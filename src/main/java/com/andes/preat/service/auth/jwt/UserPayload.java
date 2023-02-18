package com.andes.preat.service.auth.jwt;
import lombok.Getter;
@Getter
public class UserPayload {
    private Long id;
    public UserPayload(Long id) {
        this.id = id;
    }
}
