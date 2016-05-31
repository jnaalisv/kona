package kona.web.interfaces.order;

import kona.web.interfaces.AbstractSpringRestMvcTest;
import org.junit.Test;
import org.springframework.test.context.jdbc.Sql;

public class DeliveryOrderControllerIntegrationTest extends AbstractSpringRestMvcTest {

    @Sql(scripts = {"classpath:customers.sql", "classpath:products.sql"})
    @Test
    public void shouldPostNewDeliveryOrder() {


    }
}
