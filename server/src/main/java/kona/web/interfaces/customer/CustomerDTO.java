package kona.web.interfaces.customer;

import kona.model.domain.customer.Customer;

import java.time.LocalDateTime;

public class CustomerDTO {

    public long id;
    public String name;
    public LocalDateTime createTime;
    public long version;

    public CustomerDTO() {}

    public CustomerDTO(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
    }
}
