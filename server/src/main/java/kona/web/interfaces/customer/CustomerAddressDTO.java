package kona.web.interfaces.customer;

import kona.model.domain.customer.CustomerAddress;

public class CustomerAddressDTO {

    public String street;
    public String postalCode;
    public String municipality;
    public String countryCode;

    public CustomerAddressDTO() {}

    public CustomerAddressDTO(CustomerAddress customerAddress) {
        this.street = customerAddress.getStreet();
        this.postalCode = customerAddress.getPostalCode();
        this.municipality = customerAddress.getMunicipality();
        this.countryCode = customerAddress.getCountryCode();
    }
}
