package kona.infrastructure;

import kona.infrastructure.config.PersistenceConfiguration;
import kona.model.domain.customer.Customer;
import kona.model.domain.customer.CustomerRepository;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = {PersistenceConfiguration.class})
public class CustomerRepositoryIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private CustomerRepository customerRepository;

    @Sql("classpath:customers.sql")
    @Test
    public void shouldGetAllCustomers() {
        assertThat(customerRepository.getAll().size()).isEqualTo(2);
    }

    @Test
    public void shouldAddCustomer() {
        Customer aNewCustomer = new Customer(0, "A Brave New Customer");

        customerRepository.add(aNewCustomer);

        entityManager.flush();
        assertThat(customerRepository.getAll().size()).isEqualTo(1);
    }
}
