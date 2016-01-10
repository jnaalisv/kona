package jnaalisv.kona.web.interfaces.addresses;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import jnaalisv.kona.domain.addresses.AddressDTO;
import jnaalisv.kona.web.interfaces.AbstractSpringRestMvcTest;

public class AddressControllerIntegrationTest extends AbstractSpringRestMvcTest {

    @Test
    public void shouldSuccesfullyCreateNewAddress() throws Exception {
        AddressDTO aNewAddress = new AddressDTO();
        aNewAddress.street = "Street Address";
        aNewAddress.postalCode = "99999";
        aNewAddress.municipality = "Some City";

        MvcResult mvcResult = mockMvc.perform(post("/addresses", aNewAddress)
                .content(objectMapper.writeValueAsString(aNewAddress))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andExpect(header().string("Location", "addresses/99"))
                .andReturn();

        AddressDTO returnedAddress = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), AddressDTO.class);

        assertThat(returnedAddress.ID).isGreaterThan(0l);
        assertThat(returnedAddress.street).isEqualTo(aNewAddress.street);
        assertThat(returnedAddress.postalCode).isEqualTo(aNewAddress.postalCode);
        assertThat(returnedAddress.municipality).isEqualTo(aNewAddress.municipality);
    }
}
