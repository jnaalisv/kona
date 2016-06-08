package kona.infrastructure.persistence;

import kona.model.domain.customer.Customer;
import kona.model.domain.product.Product;
import kona.model.domain.product.ProductRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class HibernateProductRepository implements ProductRepository {

    private final SessionFactory sessionFactory;

    @Inject
    public HibernateProductRepository(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Product> findBy(String name) {
        return getCurrentSession()
                .createQuery("SELECT p FROM Product p where p.name like :name")
                .setParameter("name", "%"+name + "%")
                .list();
    }

    @Override
    public List<Product> getAll() {
        return getCurrentSession()
                .createQuery("select p from Product p")
                .list();
    }
}
