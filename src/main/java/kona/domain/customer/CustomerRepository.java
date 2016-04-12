package kona.domain.customer;

public interface CustomerRepository {

    void add(Customer customer);

    Customer get(long id);
}
