package kona.http.customer;

import kona.domain.customer.Customer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CustomerDTO {

    public long id;
    public String name;
    public LocalDateTime createTime;
    public long version;

    public List<CustomerAddressDTO> addresses;

    public CustomerDTO() {
        addresses = new ArrayList<>();
    }

    public CustomerDTO(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
    }
}
