package kona.domain.addresses;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Inject
    public AddressService(final AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Transactional
    public AddressDTO save(AddressDTO addressDTO) {
        Address address = new Address(addressDTO.street, addressDTO.postalCode, addressDTO.municipality);
        addressRepository.save(address);
        return new AddressDTO(address);
    }

    @Transactional
    public AddressDTO get(long id) {
        Address address = addressRepository
                .get(id)
                .orElseThrow(() -> new RuntimeException("address not found with id=" + id));

        return new AddressDTO(address);
    }

}
