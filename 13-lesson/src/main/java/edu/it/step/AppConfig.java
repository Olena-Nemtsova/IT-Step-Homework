package edu.it.step;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Address address() {
        return new Address("Some Street", "1", "10");
    }

    @Bean
    public User user(Address address) {
        return new User(address, "John", "Whick");
    }
}
