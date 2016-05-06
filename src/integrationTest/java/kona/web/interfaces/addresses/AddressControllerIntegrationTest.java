package kona.web.interfaces.addresses;

import kona.web.interfaces.AbstractSpringRestMvcTest;
import kona.web.interfaces.address.AddressDTO;
import org.junit.Test;

import static kona.web.authentication.PreAuthTokenFilter.X_AUTH_TOKEN_HEADERNAME;
import static org.assertj.core.api.Assertions.assertThat;

public class AddressControllerIntegrationTest extends AbstractSpringRestMvcTest {

    @Test
    public void shouldSuccesfullyCreateNewAddress() {
        AddressDTO aNewAddress = new AddressDTO();
        aNewAddress.street = "Street Address";
        aNewAddress.postalCode = "99999";
        aNewAddress.municipality = "Some City";

        AddressDTO savedAddress = httpPost("/addresses")
                .contentTypeApplicationJson()
                .content(aNewAddress)
                .header(X_AUTH_TOKEN_HEADERNAME, someUserAuthToken)
                .expect201()
                .expectHeader("Location", "addresses/1")
                .responseBodyAs(AddressDTO.class);

        assertThat(savedAddress.ID).isGreaterThan(0l);
        assertThat(savedAddress.street).isEqualTo(aNewAddress.street);
        assertThat(savedAddress.postalCode).isEqualTo(aNewAddress.postalCode);
        assertThat(savedAddress.municipality).isEqualTo(aNewAddress.municipality);

        AddressDTO requestedAddress =
                httpGet("/addresses/{id}", savedAddress.ID)
                        .acceptApplicationJson()
                        .header(X_AUTH_TOKEN_HEADERNAME, someUserAuthToken)
                        .expect200()
                        .responseBodyAs(AddressDTO.class);


        assertThat(savedAddress.ID).isEqualTo(requestedAddress.ID);
        assertThat(savedAddress.street).isEqualTo(requestedAddress.street);
        assertThat(savedAddress.postalCode).isEqualTo(requestedAddress.postalCode);
        assertThat(savedAddress.municipality).isEqualTo(requestedAddress.municipality);
    }
}
