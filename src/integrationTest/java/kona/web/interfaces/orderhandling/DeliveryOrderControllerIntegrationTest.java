package kona.web.interfaces.orderhandling;

import static kona.web.authentication.PreAuthTokenFilter.X_AUTH_TOKEN_HEADERNAME;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.test.context.jdbc.Sql;

import kona.web.interfaces.AbstractSpringRestMvcTest;
import kona.web.interfaces.KonaWebResources;
import kona.web.interfaces.customer.CustomerDTO;
import kona.web.interfaces.product.ProductDTO;

public class DeliveryOrderControllerIntegrationTest extends AbstractSpringRestMvcTest {

    @Sql({
            "classpath:init-database.sql"
            , "classpath:products.sql"
            , "classpath:customers.sql"
    })
    @Test
    public void shouldPostNewDeliveryOrder() {

        List<CustomerDTO> customers =
                httpGet(KonaWebResources.CUSTOMERS)
                        .acceptApplicationJson()
                        .header(X_AUTH_TOKEN_HEADERNAME, someUserAuthToken)
                        .expect200()
                        .responseBodyAsListOf(CustomerDTO.class);

        DeliveryOrderDTO deliveryOrderDTO = new DeliveryOrderDTO();
        deliveryOrderDTO.payerID = customers.get(0).id;

        List<ProductDTO> products =
                httpGet(KonaWebResources.PRODUCTS)
                        .header(X_AUTH_TOKEN_HEADERNAME, someUserAuthToken)
                        .acceptApplicationJson()
                        .expect200()
                        .responseBodyAsListOf(ProductDTO.class);

        deliveryOrderDTO.orderLines = Arrays.asList(new OrderLineDTO(0l, products.get(0).id, new BigDecimal("10")));

        httpPost("/delivery-orders")
                .header(X_AUTH_TOKEN_HEADERNAME, someUserAuthToken)
                .contentTypeApplicationJson()
                .content(deliveryOrderDTO)
                .expect201()
                .expectHeader("Location", "/delivery-orders/1");

        DeliveryOrderDTO postedDeliveryOrder = httpGet("/delivery-orders/1")
                .header(X_AUTH_TOKEN_HEADERNAME, someUserAuthToken)
                .acceptApplicationJson()
                .expect200()
                .responseBodyAs(DeliveryOrderDTO.class);
        
    }
}
