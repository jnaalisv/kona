package kona.web.interfaces.customer;

import kona.model.domain.customer.Customer;

public class CustomerAssembler {

    public static Customer assembleFrom(CustomerDTO customerDTO) {
        Customer customer = new Customer(customerDTO.id, customerDTO.name);
        customer.setVersion(customerDTO.version);
        customer.setCreateTime(customerDTO.createTime);
        return customer;
    }

    public static CustomerDTO assembleTo(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.id = customer.getId();
        customerDTO.name = customer.getName();
        customerDTO.createTime = customer.getCreateTime();
        customerDTO.version = customer.getVersion();
        return customerDTO;
    }
}
