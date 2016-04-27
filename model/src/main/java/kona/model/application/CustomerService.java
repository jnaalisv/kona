package kona.model.application;

import kona.model.domain.customer.Customer;
import kona.model.domain.customer.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

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
        return this.customerRepository.get(id);
    }

    @Transactional
    public List<Customer> loadAll() {
        return this.customerRepository.getAll();
    }
}
