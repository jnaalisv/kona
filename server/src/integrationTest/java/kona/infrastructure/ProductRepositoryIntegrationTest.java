package kona.infrastructure;

import kona.infrastructure.config.PersistenceConfiguration;
import kona.model.domain.product.Product;
import kona.model.domain.product.ProductRepository;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@Sql({"classpath:init-database.sql"})
@ContextConfiguration(classes = {PersistenceConfiguration.class})
public class ProductRepositoryIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Inject
    private SessionFactory sessionFactory;

    @Inject
    private ProductRepository productRepository;

    @Test
    public void shouldAddProduct() {
        assertThat(countRowsInTable("product")).isEqualTo(0);

        Product aNewProduct = new Product(0l, "Crude Oil");

        productRepository.add(aNewProduct);

        sessionFactory.getCurrentSession().flush();

        assertThat(aNewProduct.getId()).isGreaterThan(0);

        assertThat(countRowsInTableWhere("product", "name='derp'")).isEqualTo(0);
        assertThat(countRowsInTableWhere("product", "name='Crude Oil'")).isEqualTo(1);
    }
}
