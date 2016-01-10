package jnaalisv.kona.web.interfaces.addresses;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import jnaalisv.kona.domain.addresses.AddressDTO;
import jnaalisv.kona.web.configuration.WebConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfiguration.class })
public class AddressControllerIntegrationTest {

    @Inject
    private WebApplicationContext webApplicationContext;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void shouldSuccesfullyCreateNewAddress() throws Exception {

        MockMvc mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();

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
