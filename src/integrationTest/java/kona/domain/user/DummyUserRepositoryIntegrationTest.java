package kona.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import javax.inject.Inject;

import kona.infrastructure.persistence.DummyUserRepository;
import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetails;

import kona.AbstractIntegrationTest;

public class DummyUserRepositoryIntegrationTest extends AbstractIntegrationTest {

    @Inject
    private DummyUserRepository dummyUserRepository;

    @Test
    public void shouldLoadUserDetailsByUserName() {
        String userName = "admin";

        UserDetails user = dummyUserRepository.loadUserByUsername(userName);

        assertThat(user).isNotNull();
    }
}
