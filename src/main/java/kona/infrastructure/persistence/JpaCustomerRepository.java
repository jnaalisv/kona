package kona.infrastructure.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import kona.model.domain.customer.Customer;
import kona.model.domain.customer.CustomerRepository;

@Repository
public class JpaCustomerRepository implements CustomerRepository {

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
        return entityManager
                .createQuery("select c from Customer c", Customer.class)
                .getResultList();
    }

    @Override
    public List<Customer> findByName(String name) {
        TypedQuery<Customer> customerQuery = entityManager
                .createQuery("SELECT c FROM Customer c where c.name like :name", Customer.class)
                .setParameter("name", "%"+name + "%");

        return customerQuery.getResultList();
    }
}
