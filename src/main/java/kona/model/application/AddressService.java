package kona.model.application;

import kona.model.domain.address.Address;
import kona.model.domain.address.AddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

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

    @Transactional
    public List<Address> loadAll() {
        return this.addressRepository.getAll();
    }
}
