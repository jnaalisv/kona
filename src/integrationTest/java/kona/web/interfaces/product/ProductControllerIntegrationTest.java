package kona.web.interfaces.product;

import kona.web.interfaces.AbstractSpringRestMvcTest;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductControllerIntegrationTest extends AbstractSpringRestMvcTest {

    @Sql({
            "classpath:init-database.sql"
            , "classpath:products.sql"
            , "classpath:customers.sql"
    })
    @Test
    public void shouldFindProductByNameSearch() {

        List<ProductDTO> products;

        products = httpGet("/products")
                .header(HttpHeaders.AUTHORIZATION, someUserAuthToken)
                .expect200()
                .responseBodyAsListOf(ProductDTO.class);

        assertThat(products.size()).isEqualTo(4);

        products = httpGet("/products")
                .header(HttpHeaders.AUTHORIZATION, someUserAuthToken)
                .param("name", "Titanium")
                .expect200()
                .responseBodyAsListOf(ProductDTO.class);

        assertThat(products.size()).isEqualTo(2);

        products = httpGet("/products")
                .header(HttpHeaders.AUTHORIZATION, someUserAuthToken)
                .param("name", "unobtanium")
                .expect200()
                .responseBodyAsListOf(ProductDTO.class);

        assertThat(products.size()).isEqualTo(0);

    }
}
