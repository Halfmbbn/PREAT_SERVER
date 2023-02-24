package com.andes.preat.domain.user;

import com.andes.preat.domain.common.BaseEntity;
import com.andes.preat.dto.request.auth.UserSignUpTastyInfoRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USERS")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String email;
    @Column
    private String nickname;
    @Column
    private String gender;
    @Column
    private String ageRange;
    // TODO : 이미지 url 링크 제한
    @Column
    private String profileImgUrl;
    @Column
    private Integer spicy;
    @Column
    private Integer sweet;
    @Column
    private Integer salty;
    @Column
    @Enumerated(EnumType.STRING)
    private UserState status;
    private User(String email, String nickname, String gender, String ageRange, String profileImgUrl, Integer spicy, Integer sweet, Integer salty) {
        this.email = email;
        this.nickname = nickname;
        this.gender = gender;
        this.ageRange = ageRange;
        this.profileImgUrl = profileImgUrl;
        this.spicy = spicy;
        this.sweet = sweet;
        this.salty = salty;
        this.status = UserState.CREATING;
    }

    public static User newInstance(String email, String nickname, String gender, String ageRange, String profileImgUrl) {
        return new User(email, nickname, gender, ageRange, profileImgUrl, 0, 0, 0);
    }

    public void update(final User updateUser) {
        updateNickname(updateUser.nickname);
        updateGender(updateUser.gender);
        updateAgeRange(updateUser.ageRange);
        updateProfileImgUrl(updateUser.profileImgUrl);
    }
    public void updateTastyInfo(final UserSignUpTastyInfoRequest tastyInfoRequest) {
        updateSpicy(tastyInfoRequest.getSpicy());
        updateSweet(tastyInfoRequest.getSweet());
        updateSalty(tastyInfoRequest.getSalty());
    }

    public void updateNickname(final String nickname) {
        if (nickname != null) {
            this.nickname = nickname;
        }
    }
    public void updateGender(final String gender) {
        if (gender != null) {
            this.gender = gender;
        }
    }
    public void updateAgeRange(final String ageRange) {
        if (ageRange != null) {
            this.ageRange = ageRange;
        }
    }
    public void updateProfileImgUrl(final String profileImgUrl) {
        if (profileImgUrl != null) {
            this.profileImgUrl = profileImgUrl;
        }
    }
    public void updateSpicy(final Integer spicy) {
        if (spicy != null) {
            this.spicy = spicy;
        }
    }public void updateSweet(final Integer sweet) {
        if (sweet != null) {
            this.sweet = sweet;
        }
    }
    public void updateSalty(final Integer salty) {
        if (salty != null) {
            this.salty = salty;
        }
    }
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        final User user = (User) o;
        return Objects.equals(id, user.getId());
    }
    public void deleteUser() {
        this.status = UserState.DELETED;
    }
    public void updateUserToRegistered() {
        this.status = UserState.COMPLETE;
    }

    public boolean isRegistered() {
        if (this.status != UserState.COMPLETE) {
            return false;
        }
        return true;
    }
    public void Testing() {
        this.status = UserState.CREATING;
    }
}
