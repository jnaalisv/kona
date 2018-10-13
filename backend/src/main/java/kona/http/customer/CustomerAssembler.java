package kona.http.customer;

import kona.domain.customer.Customer;
import kona.domain.customer.CustomerAddress;

import java.util.stream.Collectors;

public class CustomerAssembler {

    public static Customer assembleFrom(CustomerDTO customerDTO) {
        Customer customer = new Customer(customerDTO.id, customerDTO.name);
        customer.setVersion(customerDTO.version);
        customer.setCreateTime(customerDTO.createTime);

        customer.setAddresses(customerDTO
                .addresses
                .stream()
                .map(dto -> new CustomerAddress(dto.street, dto.postalCode, dto.municipality, dto.countryCode))
                .collect(Collectors.toList()));

        return customer;
    }

    public static CustomerDTO assembleTo(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.id = customer.getId();
        customerDTO.name = customer.getName();
        customerDTO.createTime = customer.getCreateTime();
        customerDTO.version = customer.getVersion();
        customerDTO.addresses = customer
                .getAddresses()
                .stream()
                .map(CustomerAddressDTO::new)
                .collect(Collectors.toList());
        return customerDTO;
    }
}
