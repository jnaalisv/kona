package kona.infrastructure;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import kona.domain.customer.Customer;
import kona.domain.customer.CustomerRepository;

@Repository
public class HibernateCustomerRepository implements CustomerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Customer customer) {
        entityManager.persist(customer);
    }

    @Override
    public Customer get(long id) {
        return entityManager.find(Customer.class, id);
    }

    @Override
    public List<Customer> getAll() {
        return entityManager.createQuery("from Customer").getResultList();
    }
}
