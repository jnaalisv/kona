package kona.model.application;

import kona.AbstractIntegrationTest;
import kona.model.domain.orderhandling.DeliveryOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class OrderHandlingServiceIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private OrderHandlingService orderHandlingService;

    @Sql({"classpath:init-database.sql", "classpath:products.sql", "classpath:customers.sql", "classpath:orders.sql"})
    @Test
    public void shouldGetAllOrders() {
        List<DeliveryOrder> orders = orderHandlingService.getAll();

        assertThat(orders.size()).isEqualTo(1);
    }
}
