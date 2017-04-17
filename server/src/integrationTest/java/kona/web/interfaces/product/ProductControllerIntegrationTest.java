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
    })
    @Test
    public void shouldFindProductByNameSearch() {

        List<ProductDTO> products;

        products = httpGet("/products")
                .header(HttpHeaders.AUTHORIZATION, adminAuthToken)
                .param("name", "Device")
                .expect200()
                .responseBodyAsListOf(ProductDTO.class);

        assertThat(products.size()).isEqualTo(2);

        products = httpGet("/products")
                .header(HttpHeaders.AUTHORIZATION, adminAuthToken)
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
                .header(HttpHeaders.AUTHORIZATION, adminAuthToken)
                .expect200()
                .responseBodyAsListOf(ProductDTO.class);

        assertThat(products.size()).isEqualTo(5);
    }

    @Sql({"classpath:init-database.sql"})
    @Test
    public void shouldAddNewProduct() {

        ProductDTO newProduct = new ProductDTO();
        newProduct.name = "Portable Video Recording Device";
        newProduct.productCode = "PVRD";

        ProductDTO postedProduct = httpPost("/products")
                .header(HttpHeaders.AUTHORIZATION, adminAuthToken)
                .contentTypeApplicationJson()
                .content(newProduct)
                .expect201()
                .responseBodyAs(ProductDTO.class);

        assertThat(postedProduct.productCode).isEqualTo(newProduct.productCode);

        httpGet("/products/" + postedProduct.id)
                .header(HttpHeaders.AUTHORIZATION, adminAuthToken)
                .acceptApplicationJson()
                .expect200();
    }

    @Sql({"classpath:init-database.sql"})
    @Test
    public void getNonExistingProductReturns404() {
        httpGet("/products/999")
                .header(HttpHeaders.AUTHORIZATION, adminAuthToken)
                .acceptApplicationJson()
                .expect404();
    }
}
