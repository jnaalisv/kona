package kona.model.repositories;

import kona.model.AbstractIntegrationTest;
import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetails;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

public class UserRepositoryIntegrationTest extends AbstractIntegrationTest {

    @Inject
    private DummyUserRepository userRepository;

    @Test
    public void shouldLoadUserDetailsByUserName() {
        String userName = "admin";

        UserDetails user = userRepository.loadUserByUsername(userName);

        assertThat(user).isNotNull();
    }
}
