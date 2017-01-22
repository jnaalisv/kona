package kona.web.interfaces.orderhandling;

import kona.web.interfaces.AbstractSpringRestMvcTest;
import kona.web.interfaces.KonaWebResources;
import kona.web.interfaces.customer.CustomerDTO;
import kona.web.interfaces.product.ProductDTO;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DeliveryOrderControllerIntegrationTest extends AbstractSpringRestMvcTest {

    @Sql({"classpath:products.sql", "classpath:customers.sql"})
    @Test
    public void shouldPostNewDeliveryOrder() {

        List<CustomerDTO> customers =
                httpGet(KonaWebResources.CUSTOMERS)
                        .acceptApplicationJson()
                        .header(HttpHeaders.AUTHORIZATION, adminAuthToken)
                        .expect200()
                        .responseBodyAsListOf(CustomerDTO.class);

        DeliveryOrderDTO deliveryOrderDTO = new DeliveryOrderDTO();
        deliveryOrderDTO.ordererID = customers.get(0).id;

        List<ProductDTO> products =
                httpGet(KonaWebResources.PRODUCTS)
                        .header(HttpHeaders.AUTHORIZATION, adminAuthToken)
                        .acceptApplicationJson()
                        .expect200()
                        .responseBodyAsListOf(ProductDTO.class);

        deliveryOrderDTO.orderLines = Arrays.asList(new OrderLineDTO(0l, products.get(0).productCode, new BigDecimal("10")));

        DeliveryOrderDTO postedDeliveryOrder = httpPost("/delivery-orders")
                .header(HttpHeaders.AUTHORIZATION, adminAuthToken)
                .contentTypeApplicationJson()
                .content(deliveryOrderDTO)
                .expect201()
                .expectHeader("Location", "/delivery-orders/[0-9]{1,2}")
                .responseBodyAs(DeliveryOrderDTO.class);

        assertThat(postedDeliveryOrder.orderLines).hasSize(1);

        postedDeliveryOrder = httpGet("/delivery-orders/" + postedDeliveryOrder.id)
                .header(HttpHeaders.AUTHORIZATION, adminAuthToken)
                .acceptApplicationJson()
                .expect200()
                .responseBodyAs(DeliveryOrderDTO.class);

        assertThat(postedDeliveryOrder.orderLines).hasSize(1);
    }
}
