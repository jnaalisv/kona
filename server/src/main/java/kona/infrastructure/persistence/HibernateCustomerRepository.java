package kona.infrastructure.persistence;

import kona.model.domain.customer.Customer;
import kona.model.domain.customer.CustomerRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class HibernateCustomerRepository implements CustomerRepository {

    private final SessionFactory sessionFactory;

    public HibernateCustomerRepository(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void add(Customer customer) {
        getCurrentSession().save(customer);
    }

    @Override
    public Optional<Customer> get(long id) {
        return getCurrentSession()
                .createQuery("select c from Customer c where c.id = :id", Customer.class)
                .setParameter("id", id)
                .uniqueResultOptional();
    }

    @Override
    public List<Customer> getAll() {
        return getCurrentSession()
                .createQuery("select c from Customer c", Customer.class)
                .list();
    }

    @Override
    public List<Customer> findByName(String name) {
        return getCurrentSession()
                .createQuery("SELECT c FROM Customer c where c.name like :name", Customer.class)
                .setParameter("name", "%"+name + "%")
                .list();
    }

    @Override
    public void update(Customer customer) {
        getCurrentSession().update(customer);
    }

    @Override
    public int delete(long customerId) {
        return getCurrentSession()
                .createNativeQuery("DELETE FROM Customer WHERE id = :id")
                .setParameter("id", customerId)
                .executeUpdate();
    }
}
