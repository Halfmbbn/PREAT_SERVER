package com.andes.preat.dto.request.user;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ModifyNicknameRequest {
    private String nickname;
}
