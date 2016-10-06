package kona.infrastructure.persistence;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import kona.model.domain.address.Address;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import kona.model.domain.customer.Customer;
import kona.model.domain.customer.CustomerRepository;

@Repository
public class HibernateCustomerRepository implements CustomerRepository {

    private final SessionFactory sessionFactory;

    @Inject
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
}
