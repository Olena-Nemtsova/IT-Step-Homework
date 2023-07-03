package edu.it.step;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserArrayListTest {
    private UserArrayList users;
    private final User user = new User("Mark", "Pearce", 22);

    @BeforeEach
    void setUp() {
        users = new UserArrayList();
    }

    @Test
    void can_add_user() {
        users.add(user);

        assertEquals(user, users.get(0));
    }

    @Test
    void can_add_user_to_start() {
        users.add(new User());

        users.addToStart(user);

        assertEquals(user, users.get(0));
    }

    @Test
    void can_delete_user() {
        users.add(user);

        users.delete();

        assertEquals(0, users.size());
    }

    @Test
    void can_delete_user_from_start() {
        users.add(new User());
        users.add(user);

        users.deleteFromStart();

        assertEquals(user, users.get(0));
        assertEquals(1, users.size());
    }

    @Test
    void can_delete_user_by_index() {
        users.add(new User());
        users.add(new User());
        users.add(user);

        users.delete(1);

        assertEquals(user, users.get(1));
        assertEquals(2, users.size());
    }

    @Test
    void can_get_has_next() {
        boolean expectedFalse = users.iterator().hasNext();
        users.addToStart(user);
        boolean expectedTrue = users.iterator().hasNext();

        assertFalse(expectedFalse);
        assertTrue(expectedTrue);
    }

    @Test
    void can_not_get_next_with_empty_list() {
        var iterator = users.iterator();

        assertThrows(
                NoSuchElementException.class,
                iterator::next
        );
    }

    @Test
    void can_get_next() {
        users.add(user);

        assertEquals(user, users.iterator().next());
    }
}