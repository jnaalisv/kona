package kona.web.interfaces.addresses;

import kona.web.interfaces.AbstractSpringRestMvcTest;
import kona.web.interfaces.address.AddressDTO;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

public class AddressControllerIntegrationTest extends AbstractSpringRestMvcTest {

    @Test
    public void shouldSuccesfullyCreateNewAddress() throws Exception {
        AddressDTO aNewAddress = new AddressDTO();
        aNewAddress.street = "Street Address";
        aNewAddress.postalCode = "99999";
        aNewAddress.municipality = "Some City";

        AddressDTO savedAddress = httpPost("/addresses")
                .contentTypeApplicationJson()
                .content(aNewAddress)
                .perform()
                .expectStatus(HttpStatus.CREATED)
                .expectHeader("Location", "addresses/1")
                .responseBodyAs(AddressDTO.class);

        assertThat(savedAddress.ID).isGreaterThan(0l);
        assertThat(savedAddress.street).isEqualTo(aNewAddress.street);
        assertThat(savedAddress.postalCode).isEqualTo(aNewAddress.postalCode);
        assertThat(savedAddress.municipality).isEqualTo(aNewAddress.municipality);

        AddressDTO requestedAddress =
                httpGet("/addresses/" + savedAddress.ID)
                        .acceptApplicationJson()
                        .perform()
                        .responseBodyAs(AddressDTO.class);


        assertThat(savedAddress.ID).isEqualTo(requestedAddress.ID);
        assertThat(savedAddress.street).isEqualTo(requestedAddress.street);
        assertThat(savedAddress.postalCode).isEqualTo(requestedAddress.postalCode);
        assertThat(savedAddress.municipality).isEqualTo(requestedAddress.municipality);
    }
}
