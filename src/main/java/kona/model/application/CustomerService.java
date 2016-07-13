package kona.model.application;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kona.model.domain.customer.Customer;
import kona.model.domain.customer.CustomerRepository;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    @Inject
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
                .orElseThrow(() -> NotFoundException.byId(Customer.class, id));
    }

    @Transactional
    public List<Customer> findBy(String name) {
        if (name != null) {
            return customerRepository.findByName(name);
        } else {
            return customerRepository.getAll();
        }
    }
}
