package edu.it.step;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@Setter
public class Pistol implements Weapon {
    private static final double POWER = 1.5;
    private int bullets;

    @Override
    public double shoot(double hp) {
        bullets--;

        return hp - POWER;
    }
}
