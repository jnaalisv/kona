package kona.model.domain.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    void add(Customer customer);

    Optional<Customer> get(long id);

    List<Customer> getAll();

    List<Customer> findByName(String name);
}
