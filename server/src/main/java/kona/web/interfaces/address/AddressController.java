package kona.web.interfaces.address;

import kona.model.application.AddressService;
import kona.model.domain.address.Address;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("addresses")
public class AddressController {

    private AddressService addressService;

    @Inject
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<AddressDTO> post(@RequestBody AddressDTO addressDTO) {

        Address aNewAddress = new Address(addressDTO.street, addressDTO.postalCode, addressDTO.municipality);

        addressService.save(aNewAddress);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Location", "addresses/"+aNewAddress.getID());
        return new ResponseEntity<>(new AddressDTO(aNewAddress), responseHeaders, HttpStatus.CREATED);
    }

    @GetMapping("{addressID}")
    public AddressDTO get(@PathVariable long addressID) {
        return new AddressDTO(addressService.get(addressID));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<AddressDTO> getAll() {
        return addressService
                .loadAll()
                .stream()
                .map(AddressDTO::new)
                .collect(Collectors.toList());
    }

}
