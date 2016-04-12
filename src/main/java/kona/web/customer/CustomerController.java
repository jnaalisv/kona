package kona.web.customer;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("customers")
public class CustomerController {

    public CustomerController() {
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<CustomerDTO> getAll() {
        return Collections.emptyList();
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public CustomerDTO get(@PathVariable long id) {
        return new CustomerDTO();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CustomerDTO> post(@RequestBody CustomerDTO addressDTO) {

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Location", "customers/");
        return new ResponseEntity<>(new CustomerDTO(), responseHeaders, HttpStatus.CREATED);
    }

}
