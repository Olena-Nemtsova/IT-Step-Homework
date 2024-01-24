package edu.it.step;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
public class AppConfig {
    @Value("${player.race}")
    private String raceValue;

    @Bean
    public Race race() {
        return Race.valueOf(raceValue.toUpperCase());
    }
}
