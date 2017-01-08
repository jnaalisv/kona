package kona.infrastructure;

import kona.IntegrationTestConfig;
import kona.infrastructure.config.PersistenceConfiguration;
import kona.infrastructure.persistence.HibernateUserRepository;
import kona.model.domain.user.User;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.inject.Inject;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Sql({"classpath:init-database.sql", "classpath:users.sql"})
@ContextConfiguration(classes = {PersistenceConfiguration.class, IntegrationTestConfig.class})
public class UserRepositoryIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Inject
    private HibernateUserRepository hibernateUserRepository;

    @Test
    public void shouldLoadUserDetailsByUserName() {
        String userName = "admin";

        Optional<User> maybeUser = hibernateUserRepository.loadUserByUsername(userName);

        assertThat(maybeUser.isPresent()).isTrue();
    }
}
