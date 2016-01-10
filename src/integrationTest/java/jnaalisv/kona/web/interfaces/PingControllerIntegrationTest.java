package jnaalisv.kona.web.interfaces;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;

public class PingControllerIntegrationTest extends AbstractSpringRestMvcTest{

    @Test
    public void systemShouldRespondToPingRequest () throws Exception {
        mockMvc.perform(get("/ping"))
                .andExpect(status().is2xxSuccessful());
    }

}
