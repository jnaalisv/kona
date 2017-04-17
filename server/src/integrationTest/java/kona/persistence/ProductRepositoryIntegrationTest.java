package kona.persistence;

import kona.IntegrationTestConfig;
import kona.persistence.config.HibernateConfiguration;
import kona.model.domain.product.Product;
import kona.model.domain.product.ProductCode;
import kona.model.domain.product.ProductRepository;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.assertj.core.api.Assertions.assertThat;

@Sql({"classpath:init-database.sql"})
@ContextConfiguration(classes = {HibernateConfiguration.class, IntegrationTestConfig.class})
public class ProductRepositoryIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void shouldAddProduct() {
        assertThat(countRowsInTable("product")).isEqualTo(0);

        Product thoriumCapsules = new Product("Thorium Capsules", new ProductCode("TC"));

        productRepository.add(thoriumCapsules);

        sessionFactory.getCurrentSession().flush();

        assertThat(productRepository.findBy(thoriumCapsules.getId()).isPresent()).isTrue();

        assertThat(countRowsInTableWhere("product", "name='NotThere'")).isEqualTo(0);
        assertThat(countRowsInTableWhere("product", "name='Thorium Capsules' and productCode='TC' and id > 0")).isEqualTo(1);
    }
}
