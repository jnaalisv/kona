package kona.web.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import kona.AbstractIntegrationTest;
import kona.web.config.WebConfiguration;
import org.junit.Before;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebAppConfiguration
@ContextConfiguration(classes = {WebConfiguration.class })
public abstract class AbstractSpringRestMvcTest extends AbstractIntegrationTest {

    @Inject
    private WebApplicationContext webApplicationContext;

    protected final ObjectMapper objectMapper = new ObjectMapper();

    protected MockMvc mockMvc;

    @Before
    public void initMockMvc() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }


    protected MockMvcRequestBuilder httpGet(String url) {
        return new MockMvcRequestBuilder(mockMvc, objectMapper, get(url));
    }

    protected MockMvcRequestBuilder httpPost(String url) {
        return new MockMvcRequestBuilder(mockMvc, objectMapper, post(url));
    }

    protected MockMvcRequestBuilder httpPut(String url) {
        return new MockMvcRequestBuilder(mockMvc, objectMapper, put(url));
    }
}
