package kona.model.domain.address;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
@DynamicUpdate
public class Address {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long ID;

    @Version
    private long version;

    private String street;
    private String postalCode;
    private String municipality;

    public Address() {}

    public Address(String street, String postalCode, String municipality) {
        this.street = street;
        this.postalCode = postalCode;
        this.municipality = municipality;
    }

    public Address(long ID, String street, String postalCode, String municipality, long version) {
        this.ID = ID;
        this.street = street;
        this.postalCode = postalCode;
        this.municipality = municipality;
        this.version = version;
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

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
