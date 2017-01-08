package kona.web.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import kona.IntegrationTestConfig;
import kona.infrastructure.config.PersistenceConfiguration;
import kona.model.config.DomainConfiguration;
import kona.web.config.WebConfiguration;
import kona.web.config.authentication.SpringSecurityConfiguration;
import kona.web.config.authentication.WebSecurityConfig;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.security.core.token.KeyBasedPersistenceTokenService;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Sql({"classpath:init-database.sql"})
@ContextConfiguration(classes = {
        DomainConfiguration.class,
        PersistenceConfiguration.class,
        SpringSecurityConfiguration.class,
        WebConfiguration.class,
        WebSecurityConfig.class,
        IntegrationTestConfig.class
})
public abstract class AbstractSpringRestMvcTest {

    @Inject
    private FilterChainProxy springSecurityFilterChain;

    @Inject
    private WebApplicationContext webApplicationContext;

    @Inject
    protected KeyBasedPersistenceTokenService keyBasedPersistenceTokenService;

    protected String adminAuthToken;

    @Inject
    protected ObjectMapper objectMapper;

    protected MockMvc mockMvc;

    @Before
    public void initMockMvc() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilters(springSecurityFilterChain)
                .build();

        adminAuthToken = keyBasedPersistenceTokenService.allocateToken("admin").getKey();
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
