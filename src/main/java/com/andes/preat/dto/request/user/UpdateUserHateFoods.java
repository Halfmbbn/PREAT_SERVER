package com.andes.preat.dto.request.user;

import lombok.*;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateUserHateFoods {
    private List<Long> hateFoods;
}
