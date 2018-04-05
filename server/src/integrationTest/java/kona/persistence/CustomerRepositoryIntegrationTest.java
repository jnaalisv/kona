package kona.persistence;

import kona.IntegrationTestConfig;
import kona.domain.customer.Customer;
import kona.domain.customer.CustomerRepository;
import kona.persistence.config.HibernateConfiguration;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = {HibernateConfiguration.class, IntegrationTestConfig.class})
public class CustomerRepositoryIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private CustomerRepository customerRepository;

    @Sql({"classpath:init-database.sql", "classpath:customers.sql"})
    @Test
    public void shouldGetAllCustomers() {
        assertThat(customerRepository.getAll().size()).isEqualTo(9);
    }

    @Sql({"classpath:init-database.sql"})
    @Test
    public void shouldAddCustomer() {
        Customer aNewCustomer = new Customer(0, "A Brave New Customer");

        customerRepository.add(aNewCustomer);

        sessionFactory.getCurrentSession().flush();
        assertThat(customerRepository.getAll().size()).isEqualTo(1);
    }
}
