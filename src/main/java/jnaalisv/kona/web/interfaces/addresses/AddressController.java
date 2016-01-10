package jnaalisv.kona.web.interfaces.addresses;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jnaalisv.kona.domain.addresses.AddressDTO;

@RestController
@RequestMapping("addresses")
public class AddressController {

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AddressDTO> post(@RequestBody AddressDTO aNewAddress) {
        aNewAddress.ID = 99l;
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Location", "addresses/"+aNewAddress.ID);
        return new ResponseEntity<>(aNewAddress, responseHeaders, HttpStatus.CREATED);
    }

}
