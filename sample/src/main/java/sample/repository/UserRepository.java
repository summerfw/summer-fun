package sample.repository;

import sample.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    private static List<User> users = new ArrayList<>();

    static {
        User user1 = new User();
        user1.setId(1);
        user1.setLastName("Rizal");
        user1.setFirstName("Jose");
        users.add(user1);

        User user2 = new User();
        user2.setId(2);
        user2.setLastName("Bonifacio");
        user2.setFirstName("Andres");
        users.add(user2);

        User user3 = new User();
        user3.setId(3);
        user3.setLastName("Mabini");
        user3.setFirstName("Apolinario");
        users.add(user3);
    }

    public List<User> findAll() {
        return users;
    }

    public Optional<User> findById(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }
}
