package com.andes.preat.dto.response.auth.kakao;

import com.andes.preat.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoProfileResponse {
    private String id;
    private Properties properties;
    private KakaoAccount kakaoAccount;

    public User toUser() {
        return User.newInstance(kakaoAccount.getEmail(),
                properties.getNickname(),
                kakaoAccount.getGender(),
                kakaoAccount.getAgeRange(),
                properties.getThumbnailImage());
    }
}
