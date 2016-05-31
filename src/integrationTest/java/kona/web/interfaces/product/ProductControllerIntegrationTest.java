package kona.web.interfaces.product;

import kona.web.interfaces.AbstractSpringRestMvcTest;
import org.junit.Test;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static kona.web.authentication.PreAuthTokenFilter.X_AUTH_TOKEN_HEADERNAME;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductControllerIntegrationTest extends AbstractSpringRestMvcTest {

    @Sql("classpath:products.sql")
    @Test
    public void shouldFindProductByNameSearch() {

        List<ProductDTO> products;

        products = httpGet("/products")
                .header(X_AUTH_TOKEN_HEADERNAME, someUserAuthToken)
                .expect200()
                .responseBodyAsListOf(ProductDTO.class);

        assertThat(products.size()).isEqualTo(4);

        products = httpGet("/products")
                .header(X_AUTH_TOKEN_HEADERNAME, someUserAuthToken)
                .param("name", "Titanium")
                .expect200()
                .responseBodyAsListOf(ProductDTO.class);

        assertThat(products.size()).isEqualTo(2);

        products = httpGet("/products")
                .header(X_AUTH_TOKEN_HEADERNAME, someUserAuthToken)
                .param("name", "unobtanium")
                .expect200()
                .responseBodyAsListOf(ProductDTO.class);

        assertThat(products.size()).isEqualTo(0);

    }
}
