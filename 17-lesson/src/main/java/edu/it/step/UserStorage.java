package edu.it.step;

import edu.it.step.exception.NotFoundException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Setter
public class UserStorage {
    private final List<User> users;

    public UserStorage() {
        users = new ArrayList<>();

        User user1 = User.builder().id(1).firstName("John").lastName("Wick").age(54).build();
        User user2 = User.builder().id(2).firstName("Mark").lastName("Pearce").age(45).build();

        users.addAll(List.of(user1, user2));
    }

    public String getUserNameById(int id) {
        return users.stream().filter(user -> user.getId() == id)
                .map(user -> user.getFirstName() + " " + user.getLastName())
                .findFirst()
                .orElseThrow(
                        () -> new NotFoundException("Not found user by id: %s".formatted(id))
                );
    }
}
