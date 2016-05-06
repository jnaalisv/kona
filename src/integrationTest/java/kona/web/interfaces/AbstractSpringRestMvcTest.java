package kona.web.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import kona.AbstractIntegrationTest;
import kona.web.config.WebConfiguration;
import kona.web.config.authentication.WebSecurityConfig;
import org.junit.Before;
import org.springframework.security.core.token.KeyBasedPersistenceTokenService;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebAppConfiguration
@ContextConfiguration(classes = {WebSecurityConfig.class, WebConfiguration.class })
public abstract class AbstractSpringRestMvcTest extends AbstractIntegrationTest {

    @Inject
    private FilterChainProxy springSecurityFilterChain;

    @Inject
    private WebApplicationContext webApplicationContext;

    @Inject
    protected KeyBasedPersistenceTokenService keyBasedPersistenceTokenService;

    protected String someUserAuthToken;

    protected final ObjectMapper objectMapper = new ObjectMapper();

    protected MockMvc mockMvc;

    @Before
    public void initMockMvc() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilters(springSecurityFilterChain)
                .build();

        someUserAuthToken = keyBasedPersistenceTokenService.allocateToken("someUser").getKey();
    }

    protected MockMvcRequestBuilder httpGet(String urlTemplate, Object... urlVars) {
        return new MockMvcRequestBuilder(mockMvc, objectMapper, get(urlTemplate, urlVars));
    }

    protected MockMvcRequestBuilder httpPost(String urlTemplate, Object... urlVars) {
        return new MockMvcRequestBuilder(mockMvc, objectMapper, post(urlTemplate, urlVars));
    }

    protected MockMvcRequestBuilder httpPut(String urlTemplate, Object... urlVars) {
        return new MockMvcRequestBuilder(mockMvc, objectMapper, put(urlTemplate, urlVars));
    }
}
