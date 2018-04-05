package kona.model.application;

import kona.AbstractIntegrationTest;
import kona.domain.orderhandling.PurchaseOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class PurchaseOrderServiceIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @Sql({"classpath:init-database.sql", "classpath:products.sql", "classpath:customers.sql", "classpath:orders.sql"})
    @Test
    public void shouldGetAllOrders() {
        List<PurchaseOrder> orders = purchaseOrderService.getAll();

        assertThat(orders.size()).isEqualTo(1);
    }
}
