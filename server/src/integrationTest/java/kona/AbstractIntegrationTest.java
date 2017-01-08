package kona;

import kona.persistence.config.HibernateConfiguration;
import kona.model.config.DomainConfiguration;
import kona.web.config.authentication.SpringSecurityConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Sql({"classpath:init-database.sql"})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        SpringSecurityConfiguration.class,
        DomainConfiguration.class,
        HibernateConfiguration.class,
        IntegrationTestConfig.class
})
public abstract class AbstractIntegrationTest {

}
