package kona.web.customer;

import kona.model.domain.customer.Customer;

public class CustomerDTO {

    public Long id;
    public String name;

    public CustomerDTO() {}

    public CustomerDTO(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
    }
}
