package kona.web.interfaces;

import javax.inject.Inject;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kona.domain.addresses.AddressDTO;
import kona.domain.addresses.AddressService;

@RestController
@RequestMapping("addresses")
public class AddressController {

    private AddressService addressService;

    @Inject
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AddressDTO> post(@RequestBody AddressDTO aNewAddress) {
        AddressDTO savedAddress = addressService.save(aNewAddress);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Location", "addresses/"+savedAddress.ID);
        return new ResponseEntity<>(savedAddress, responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(path = "{addressID}", method = RequestMethod.GET)
    public AddressDTO get(@PathVariable long addressID) {
        return addressService.get(addressID);
    }

}
