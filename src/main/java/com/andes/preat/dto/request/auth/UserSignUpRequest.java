package com.andes.preat.dto.request.auth;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserSignUpRequest {
    @NotBlank(message = "nickname은 빈값이 될 수 없습니다.")
    private String nickname;
    @NotNull(message = "입맛 정보는 null 값이 될 수 없습니다.")
    private Integer salty;
    @NotNull(message = "입맛 정보는 null 값이 될 수 없습니다.")
    private Integer sweet;
    @NotNull(message = "입맛 정보는 null 값이 될 수 없습니다.")
    private Integer spicy;
    private List<Long> hateFoods;
}
