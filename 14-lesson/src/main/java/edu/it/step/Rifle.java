package edu.it.step;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@Setter
public class Rifle implements Weapon {
    private static final double POWER = 3;
    private int bullets;

    @Override
    public double shoot(double hp) {
        bullets--;

        return hp - POWER;
    }
}
