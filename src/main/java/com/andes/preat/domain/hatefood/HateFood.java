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

    private HateFood(String food) {
        this.food = food;
    }
    public HateFood newInstance(String food) {
        return new HateFood(food);
    }

}
