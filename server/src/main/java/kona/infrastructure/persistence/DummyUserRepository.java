package kona.infrastructure.persistence;

import kona.model.domain.user.User;
import org.springframework.stereotype.Repository;

@Repository
public class DummyUserRepository {

    public DummyUserRepository() {}

    public User loadUserByUsername(String username) {
        return new User(username, null);
    }
}
