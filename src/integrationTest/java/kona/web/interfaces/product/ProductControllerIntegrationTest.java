package kona.web.interfaces.product;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.jdbc.Sql;

import kona.web.interfaces.AbstractSpringRestMvcTest;

public class ProductControllerIntegrationTest extends AbstractSpringRestMvcTest {

    @Sql({
            "classpath:init-database.sql"
            , "classpath:products.sql"
    })
    @Test
    public void shouldFindProductByNameSearch() {

        List<ProductDTO> products;

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

    @Sql({
            "classpath:init-database.sql"
            , "classpath:products.sql"
    })
    @Test
    public void shouldListAllProducts() {

        List<ProductDTO> products = httpGet("/products")
                .header(HttpHeaders.AUTHORIZATION, someUserAuthToken)
                .expect200()
                .responseBodyAsListOf(ProductDTO.class);

        assertThat(products.size()).isEqualTo(4);
    }

    @Sql({"classpath:init-database.sql"})
    @Test
    public void shouldAddNewProduct() {

        ProductDTO aNewProduct = new ProductDTO();
        aNewProduct.name = "Crude Oil";

        ProductDTO postedProduct = httpPost("/products")
                .header(HttpHeaders.AUTHORIZATION, someUserAuthToken)
                .contentTypeApplicationJson()
                .content(aNewProduct)
                .expect201()
                .responseBodyAs(ProductDTO.class);

        assertThat(postedProduct.id).isGreaterThan(0);

        httpGet("/products/" + postedProduct.id)
                .header(HttpHeaders.AUTHORIZATION, someUserAuthToken)
                .acceptApplicationJson()
                .expect200();
    }

    @Sql({"classpath:init-database.sql"})
    @Test
    public void getNonExistingProductReturns404() {
        httpGet("/products/999")
                .header(HttpHeaders.AUTHORIZATION, someUserAuthToken)
                .acceptApplicationJson()
                .expect404();
    }
}
