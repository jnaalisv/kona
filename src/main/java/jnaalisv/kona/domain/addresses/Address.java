package jnaalisv.kona.domain.addresses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Address {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long ID;

    private String street;
    private String postalCode;
    private String municipality;

    public Address(String street, String postalCode, String municipality) {
        this.street = street;
        this.postalCode = postalCode;
        this.municipality = municipality;
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

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }
}
