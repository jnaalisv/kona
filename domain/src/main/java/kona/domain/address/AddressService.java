package kona.domain.address;

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
    public void save(Address address) {
        addressRepository.save(address);
    }

    @Transactional
    public Address get(long id) {
        return addressRepository
                .get(id)
                .orElseThrow(() -> new RuntimeException("address not found with id=" + id));
    }

}
