package kona.persistence;

import kona.IntegrationTestConfig;
import kona.persistence.config.HibernateConfiguration;
import kona.model.domain.customer.Customer;
import kona.model.domain.customer.CustomerRepository;
import kona.model.domain.orderhandling.PurchaseOrder;
import kona.model.domain.orderhandling.PurchaseOrderRepository;
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
@ContextConfiguration(classes = {HibernateConfiguration.class, IntegrationTestConfig.class})
public class PurchaseOrderRepositoryIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void shouldAddPurchaseOrder() {

        List<Customer> customers = customerRepository.getAll();
        List<Product> products = productRepository.getAll();

        PurchaseOrder purchaseOrder = new PurchaseOrder(0l, customers.get(0).getId(), new OrderLine(0l, products.get(0).getId(), products.get(0).getProductCode(), new BigDecimal("4.0")));

        purchaseOrderRepository.add(purchaseOrder);

        sessionFactory.getCurrentSession().flush();

        PurchaseOrder fetchedPurchaseOrder = purchaseOrderRepository.get(purchaseOrder.getId());

        assertThat(fetchedPurchaseOrder.getId()).isEqualTo(purchaseOrder.getId());
        assertThat(fetchedPurchaseOrder.getOrderLines()).hasSize(1);
    }

    @Test
    public void getAllShouldNotFail() {
        List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.getAll();
    }

}
