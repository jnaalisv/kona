package kona.web.interfaces.addresses;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import kona.web.interfaces.AbstractSpringRestMvcTest;
import kona.domain.addresses.AddressDTO;

public class AddressControllerIntegrationTest extends AbstractSpringRestMvcTest {

    @Test
    public void shouldSuccesfullyCreateNewAddress() throws Exception {
        AddressDTO aNewAddress = new AddressDTO();
        aNewAddress.street = "Street Address";
        aNewAddress.postalCode = "99999";
        aNewAddress.municipality = "Some City";

        MvcResult postResult = mockMvc.perform(post("/addresses", aNewAddress)
                .content(objectMapper.writeValueAsString(aNewAddress))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andExpect(header().string("Location", "addresses/1"))
                .andReturn();

        AddressDTO savedAddress = objectMapper.readValue(postResult.getResponse().getContentAsString(), AddressDTO.class);

        assertThat(savedAddress.ID).isGreaterThan(0l);
        assertThat(savedAddress.street).isEqualTo(aNewAddress.street);
        assertThat(savedAddress.postalCode).isEqualTo(aNewAddress.postalCode);
        assertThat(savedAddress.municipality).isEqualTo(aNewAddress.municipality);


        MvcResult getResult =mockMvc.perform(get("/addresses/" + savedAddress.ID))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        AddressDTO requestedAddress = objectMapper.readValue(getResult.getResponse().getContentAsString(), AddressDTO.class);

        assertThat(savedAddress.ID).isEqualTo(requestedAddress.ID);
        assertThat(savedAddress.street).isEqualTo(requestedAddress.street);
        assertThat(savedAddress.postalCode).isEqualTo(requestedAddress.postalCode);
        assertThat(savedAddress.municipality).isEqualTo(requestedAddress.municipality);

    }
}
