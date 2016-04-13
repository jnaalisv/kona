package kona;

import kona.configurations.DomainConfiguration;
import kona.configurations.HibernateConfiguration;
import kona.configurations.JpaConfiguration;
import kona.configurations.authentication.SpringSecurityConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        SpringSecurityConfiguration.class,
        DomainConfiguration.class,
        HibernateConfiguration.class,
        JpaConfiguration.class
})
public abstract class AbstractIntegrationTest {

}
