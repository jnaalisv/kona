package kona.web.interfaces.customer;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kona.model.application.CustomerService;
import kona.model.domain.customer.Customer;

@RestController
@RequestMapping("customers")
public class CustomerController {

    private CustomerService customerService;

    @Inject
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<CustomerDTO> getAll() {
        return customerService.loadAll()
                .stream()
                .map(customer -> new CustomerDTO(customer))
                .collect(Collectors.toList());
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CustomerDTO get(@PathVariable long id) {
        return new CustomerDTO(customerService.load(id));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CustomerDTO> post(@RequestBody CustomerDTO customerDTO) {

        Customer customer = new Customer(0l, customerDTO.name);
        customerService.save(customer);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Location", "customers/" + customer.getId());
        return new ResponseEntity<>(new CustomerDTO(customer), responseHeaders, HttpStatus.CREATED);
    }

}
