package kona.web.address;

import javax.inject.Inject;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kona.model.domain.address.Address;
import kona.model.domain.address.AddressService;

@RestController
@RequestMapping("addresses")
public class AddressController {

    private AddressService addressService;

    @Inject
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AddressDTO> post(@RequestBody AddressDTO addressDTO) {

        Address aNewAddress = new Address(addressDTO.street, addressDTO.postalCode, addressDTO.municipality);

        addressService.save(aNewAddress);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Location", "addresses/"+aNewAddress.getID());
        return new ResponseEntity<>(new AddressDTO(aNewAddress), responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(path = "{addressID}", method = RequestMethod.GET)
    public AddressDTO get(@PathVariable long addressID) {
        return new AddressDTO(addressService.get(addressID));
    }

}
