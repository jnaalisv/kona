package kona.model;

import kona.model.config.DomainComponentsConfiguration;
import kona.model.config.PersistenceConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfiguration.class, DomainComponentsConfiguration.class})
public abstract class AbstractIntegrationTest {

}
