package kona.infrastructure.persistence;

import kona.model.domain.product.Product;
import kona.model.domain.product.ProductRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class HibernateProductRepository implements ProductRepository {

    private final SessionFactory sessionFactory;

    public HibernateProductRepository(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Product> findBy(String name) {
        return getCurrentSession()
                .createQuery("SELECT p FROM Product p where p.name like :name", Product.class)
                .setParameter("name", "%"+name + "%")
                .list();
    }

    @Override
    public List<Product> getAll() {
        return getCurrentSession()
                .createQuery("select p from Product p", Product.class)
                .list();
    }

    @Override
    public void add(Product product) {
        getCurrentSession().save(product);
    }

    @Override
    public Optional<Product> findBy(long id) {
        return getCurrentSession().byId(Product.class).loadOptional(id);
    }

    @Override
    public void update(Product product) {
        getCurrentSession().saveOrUpdate(product);
    }

    @Override
    public int delete(long productId) {
        return getCurrentSession()
                .createNativeQuery("DELETE FROM Product WHERE id = :id")
                .setParameter("id", productId)
                .executeUpdate();
    }
}
