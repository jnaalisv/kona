package kona.application;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kona.domain.customer.Customer;
import kona.domain.customer.CustomerRepository;

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
}
