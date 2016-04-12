package kona;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kona.configurations.DomainConfiguration;
import kona.configurations.PersistenceConfiguration;
import kona.configurations.authentication.SpringSecurityConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringSecurityConfiguration.class, DomainConfiguration.class, PersistenceConfiguration.class})
public abstract class AbstractIntegrationTest {

}
