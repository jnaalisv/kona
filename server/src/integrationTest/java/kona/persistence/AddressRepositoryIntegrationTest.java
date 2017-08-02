package kona.persistence;

import kona.IntegrationTestConfig;
import kona.persistence.config.HibernateConfiguration;
import kona.model.domain.address.Address;
import kona.model.domain.address.AddressRepository;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Sql({"classpath:init-database.sql"})
@ContextConfiguration(classes = {HibernateConfiguration.class, IntegrationTestConfig.class})
public class AddressRepositoryIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void shouldAddAddress() {
        assertThat(countRowsInTable("address")).isEqualTo(0);

        Address mannerheimintieYksi = new Address("Mannerheimintie 1", "00100", "Helsinki");

        addressRepository.add(mannerheimintieYksi);

        sessionFactory.getCurrentSession().flush();

        assertThat(mannerheimintieYksi.getID()).isGreaterThan(0);

        assertThat(countRowsInTableWhere("address", "street='Heikinkatu 1'")).isEqualTo(0);
        assertThat(countRowsInTableWhere("address", "street='Mannerheimintie 1' and postalCode = '00100' and municipality='Helsinki'")).isEqualTo(1);
    }

    @Test
    public void shouldGetAddress() {
        assertThat(countRowsInTable("address")).isEqualTo(0);

        Address mannerheimintieYksi = new Address("Mannerheimintie 1", "00100", "Helsinki");

        addressRepository.add(mannerheimintieYksi);

        sessionFactory.getCurrentSession().flush();

        Address addressFromRepository = addressRepository
                .get(mannerheimintieYksi.getID())
                .orElseThrow(() -> new RuntimeException("test failure"));

        assertThat(addressFromRepository.getMunicipality()).isEqualTo(mannerheimintieYksi.getMunicipality());
    }

    @Test
    public void shouldFindAddressWithNativeQueryMappedToEntity() {
        addressRepository.add(new Address("Mannerheimintie 1", "00100", "Helsinki"));
        sessionFactory.getCurrentSession().flush();

        Optional<Address> address = addressRepository.findAddressWithNativeQueryMappedToEntity("Mannerheimintie 1");

        assertThat(address).isNotEmpty();
    }

}
