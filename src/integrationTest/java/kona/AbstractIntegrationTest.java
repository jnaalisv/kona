package kona;

import kona.model.config.DomainConfiguration;
import kona.web.config.authentication.SpringSecurityConfiguration;
import kona.infrastructure.config.PersistenceConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringSecurityConfiguration.class, DomainConfiguration.class, PersistenceConfiguration.class})
public abstract class AbstractIntegrationTest {

}
