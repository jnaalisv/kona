package kona.web.interfaces.customer;

import kona.model.application.CustomerService;
import kona.model.domain.customer.Customer;
import kona.web.interfaces.KonaWebResources;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(KonaWebResources.CUSTOMERS)
public class CustomerController {

    private CustomerService customerService;

    @Inject
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<CustomerDTO> searchForCustomers(@RequestParam(required = false) String name) {
        return customerService
                .findBy(name)
                .stream()
                .map(CustomerDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CustomerDTO get(@PathVariable long id) {
        return new CustomerDTO(customerService.load(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CustomerDTO> createANewCustomer(@RequestBody CustomerDTO customerDTO) {

        Customer customer = new Customer(0l, customerDTO.name);
        customerService.save(customer);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Location", "customers/" + customer.getId());
        return new ResponseEntity<>(new CustomerDTO(customer), responseHeaders, HttpStatus.CREATED);
    }

}
