package edu.it.step;


import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@Setter
public class Player {
    @Autowired
    @Qualifier(value = "pistol")
    private Weapon weapon;
    @Value("${player.nickname}")
    private String nickname;
    @Autowired
    private Race race;
}
