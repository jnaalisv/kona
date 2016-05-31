package kona.infrastructure.persistence;

import kona.model.domain.customer.Customer;
import kona.model.domain.product.Product;
import kona.model.domain.product.ProductRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaProductRepository implements ProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> findBy(String name) {
        return entityManager
                .createQuery("SELECT p FROM Product p where p.name like :name", Product.class)
                .setParameter("name", "%"+name + "%")
                .getResultList();
    }

    @Override
    public List<Product> getAll() {
        return entityManager
                .createQuery("select p from Product p", Product.class)
                .getResultList();
    }
}
