package com.andes.preat.domain.hatefood;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HateFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String food;
    @Column
    private String imgUrl;

    private HateFood(String food, String imgUrl) {
        this.food = food;
        this.imgUrl = imgUrl;
    }

    public HateFood newInstance(String food, String imgUrl) {
        return new HateFood(food, imgUrl);
    }

}
