package kona.model.repositories;

import kona.model.domain.customer.Customer;
import kona.model.domain.customer.CustomerRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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
        return entityManager.createQuery("from Customer").getResultList();
    }
}
