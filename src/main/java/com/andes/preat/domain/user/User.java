package com.andes.preat.domain.user;

import com.andes.preat.domain.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    @Column
    private String profileImgUrl;

    private User(String email, String nickname, String gender, String ageRange, String profileImgUrl) {
        this.email = email;
        this.nickname = nickname;
        this.gender = gender;
        this.ageRange = ageRange;
        this.profileImgUrl = profileImgUrl;
    }

    public static User newInstance(String email, String nickname, String gender, String ageRange, String profileImgUrl) {
        return new User(email, nickname, gender, ageRange, profileImgUrl);
    }

    public void update(final User updateUser) {
        updateNickname(updateUser.nickname);
        updateGender(updateUser.gender);
        updateAgeRange(updateUser.ageRange);
        updateProfileImgUrl(updateUser.profileImgUrl);
    }

    public void updateNickname(String nickname) {
        if (nickname != null) {
            this.nickname = nickname;
        }
    }
    public void updateGender(String gender) {
        if (gender != null) {
            this.gender = gender;
        }
    }
    public void updateAgeRange(String ageRange) {
        if (ageRange != null) {
            this.ageRange = ageRange;
        }
    }
    public void updateProfileImgUrl(String profileImgUrl) {
        if (profileImgUrl != null) {
            this.profileImgUrl = profileImgUrl;
        }
    }
}
