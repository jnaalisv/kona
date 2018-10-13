package kona.services;

import kona.domain.customer.Customer;
import kona.domain.customer.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public void save(Customer customer) {
        this.customerRepository.add(customer);
    }

    @Transactional
    public Customer load(long id) {
        return customerRepository
                .get(id)
                .orElseThrow(() -> new NotFoundException(Customer.class, " id="+id));
    }

    @Transactional
    public List<Customer> findBy(String name) {
        if (name != null) {
            return customerRepository.findByName(name);
        } else {
            return customerRepository.getAll();
        }
    }

    @Transactional
    public void update(Customer customer) {
        customerRepository.update(customer);
    }

    @Transactional
    public void delete(long customerId) {
        int rowsAffected = customerRepository.delete(customerId);
        if (rowsAffected == 0) {
            throw new NotFoundException(Customer.class, "id="+customerId);
        }
    }
}