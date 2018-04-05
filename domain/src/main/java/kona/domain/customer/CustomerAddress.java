package kona.domain.customer;

import javax.persistence.Embeddable;

@Embeddable
public class CustomerAddress {

    private String street;
    private String postalCode;
    private String municipality;
    private String countryCode;

    public CustomerAddress() {}

    public CustomerAddress(String street, String postalCode, String municipality, String countryCode) {
        this.street = street;
        this.postalCode = postalCode;
        this.municipality = municipality;
        this.countryCode = countryCode;
    }

    public String getStreet() {
        return street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getMunicipality() {
        return municipality;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
