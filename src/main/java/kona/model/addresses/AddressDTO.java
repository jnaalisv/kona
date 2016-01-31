package kona.model.addresses;

public class AddressDTO {

    public long ID;
    public String street;
    public String postalCode;
    public String municipality;

    public AddressDTO() {}

    public AddressDTO(Address address) {
        this.ID = address.getID();
        this.street = address.getStreet();
        this.postalCode = address.getPostalCode();
        this.municipality = address.getMunicipality();
    }
}
