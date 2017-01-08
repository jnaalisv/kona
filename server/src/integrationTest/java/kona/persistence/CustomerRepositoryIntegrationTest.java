package kona.persistence;

import kona.IntegrationTestConfig;
import kona.persistence.config.HibernateConfiguration;
import kona.model.domain.customer.Customer;
import kona.model.domain.customer.CustomerRepository;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.assertj.core.api.Assertions.assertThat;

@Sql({"classpath:init-database.sql"})
@ContextConfiguration(classes = {HibernateConfiguration.class, IntegrationTestConfig.class})
public class CustomerRepositoryIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
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

        sessionFactory.getCurrentSession().flush();
        assertThat(customerRepository.getAll().size()).isEqualTo(1);
    }
}
