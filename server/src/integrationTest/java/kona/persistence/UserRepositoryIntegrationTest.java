package kona.persistence;

import kona.IntegrationTestConfig;
import kona.persistence.config.PersistenceConfiguration;
import kona.persistence.impl.HibernateUserRepository;
import kona.model.domain.user.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Sql({"classpath:init-database.sql", "classpath:users.sql"})
@ContextConfiguration(classes = {PersistenceConfiguration.class, IntegrationTestConfig.class})
public class UserRepositoryIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private HibernateUserRepository hibernateUserRepository;

    @Test
    public void shouldLoadUserDetailsByUserName() {
        String userName = "admin";

        Optional<User> maybeUser = hibernateUserRepository.loadUserByUsername(userName);

        assertThat(maybeUser.isPresent()).isTrue();
    }
}
