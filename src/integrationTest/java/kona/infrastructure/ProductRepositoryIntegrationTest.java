package kona.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import kona.infrastructure.config.PersistenceConfiguration;
import kona.model.domain.product.Product;
import kona.model.domain.product.ProductRepository;

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
