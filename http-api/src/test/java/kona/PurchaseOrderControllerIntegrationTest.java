package kona;

import kona.http.orderhandling.OrderLineDTO;
import kona.http.orderhandling.PurchaseOrderDTO;
import kona.web.interfaces.AbstractSpringRestMvcTest;
import kona.http.KonaWebResources;
import kona.http.customer.CustomerDTO;
import kona.http.product.ProductDTO;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PurchaseOrderControllerIntegrationTest extends AbstractSpringRestMvcTest {

    @Sql({"classpath:products.sql", "classpath:customers.sql"})
    @Test
    public void shouldPostNewPurchaseOrder() {

        List<CustomerDTO> customers =
                httpGet(KonaWebResources.CUSTOMERS)
                        .acceptApplicationJson()
                        .header(HttpHeaders.AUTHORIZATION, adminAuthToken)
                        .expect200()
                        .responseBodyAsListOf(CustomerDTO.class);

        PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO();
        purchaseOrderDTO.ordererID = customers.get(0).id;

        List<ProductDTO> products =
                httpGet(KonaWebResources.PRODUCTS)
                        .header(HttpHeaders.AUTHORIZATION, adminAuthToken)
                        .acceptApplicationJson()
                        .expect200()
                        .responseBodyAsListOf(ProductDTO.class);

        purchaseOrderDTO.orderLines = Arrays.asList(new OrderLineDTO(0l, products.get(0).id, new BigDecimal("10"),  products.get(0).productCode));

        PurchaseOrderDTO postedPurchaseOrder = httpPost("/purchase-orders")
                .header(HttpHeaders.AUTHORIZATION, adminAuthToken)
                .contentTypeApplicationJson()
                .content(purchaseOrderDTO)
                .expect201()
                .expectHeader("Location", "/purchase-orders/[0-9]{1,2}")
                .responseBodyAs(PurchaseOrderDTO.class);

        assertThat(postedPurchaseOrder.orderLines).hasSize(1);

        postedPurchaseOrder = httpGet("/purchase-orders/" + postedPurchaseOrder.id)
                .header(HttpHeaders.AUTHORIZATION, adminAuthToken)
                .acceptApplicationJson()
                .expect200()
                .responseBodyAs(PurchaseOrderDTO.class);

        assertThat(postedPurchaseOrder.orderLines).hasSize(1);
    }
}
