package kona.web.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.token.KeyBasedPersistenceTokenService;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@Sql({"classpath:init-database.sql"})
@SpringBootTest
@AutoConfigureMockMvc
public abstract class AbstractSpringRestMvcTest {

    @Autowired
    protected KeyBasedPersistenceTokenService keyBasedPersistenceTokenService;

    protected String adminAuthToken;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected MockMvc mockMvc;

    @Before
    public void initMockMvc() {
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
