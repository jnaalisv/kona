package kona.persistence;

import kona.IntegrationTestConfig;
import kona.persistence.config.PersistenceConfiguration;
import kona.model.domain.customer.Customer;
import kona.model.domain.customer.CustomerRepository;
import kona.model.domain.orderhandling.DeliveryOrder;
import kona.model.domain.orderhandling.DeliveryOrderRepository;
import kona.model.domain.orderhandling.OrderLine;
import kona.model.domain.product.Product;
import kona.model.domain.product.ProductRepository;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Sql({"classpath:init-database.sql", "classpath:products.sql", "classpath:customers.sql"})
@ContextConfiguration(classes = {PersistenceConfiguration.class, IntegrationTestConfig.class})
public class DeliveryOrderRepositoryIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private DeliveryOrderRepository deliveryOrderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void shouldAddDeliveryOrder() {

        List<Customer> customers = customerRepository.getAll();
        List<Product> products = productRepository.getAll();

        DeliveryOrder deliveryOrder = new DeliveryOrder(0l, customers.get(0).getId(), new OrderLine(0l, products.get(0).getProductCode(), new BigDecimal("4.0")));

        deliveryOrderRepository.add(deliveryOrder);

        sessionFactory.getCurrentSession().flush();

        DeliveryOrder fetchedDeliveryOrder = deliveryOrderRepository.get(deliveryOrder.getId());

        assertThat(fetchedDeliveryOrder.getId()).isEqualTo(deliveryOrder.getId());
        assertThat(fetchedDeliveryOrder.getOrderLines()).hasSize(1);
    }
}
