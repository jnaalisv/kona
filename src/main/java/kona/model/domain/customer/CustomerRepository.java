package kona.model.domain.customer;

import java.util.List;

public interface CustomerRepository {

    void add(Customer customer);

    Customer get(long id);

    List<Customer> getAll();
}
