package kona.domain.users;

import static org.assertj.core.api.Assertions.assertThat;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetails;

import kona.AbstractIntegrationTest;

public class UserRepositoryIntegrationTest extends AbstractIntegrationTest {

    @Inject
    private UserRepository userRepository;

    @Test
    public void shouldLoadUserDetailsByUserName() {
        String userName = "admin";

        UserDetails user = userRepository.loadUserByUsername(userName);

        assertThat(user).isNotNull();
    }
}
