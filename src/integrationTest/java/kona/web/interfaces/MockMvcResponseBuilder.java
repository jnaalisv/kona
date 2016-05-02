package kona.web.interfaces;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.ResultActions;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MockMvcResponseBuilder {

    private ObjectMapper objectMapper;
    private ResultActions resultActions;

    public MockMvcResponseBuilder(ObjectMapper objectMapper, ResultActions resultActions) {
        this.objectMapper = objectMapper;
        this.resultActions = resultActions;
    }

    public MockMvcResponseBuilder expect404() throws Exception {
        resultActions.andExpect(status().isNotFound());
        return this;
    }

    public MockMvcResponseBuilder expect200() throws Exception {
        resultActions.andExpect(status().isOk());
        return this;
    }

    public MockMvcResponseBuilder expect403() throws Exception {
        resultActions.andExpect(status().isForbidden());
        return this;
    }

    public MockMvcResponseBuilder expect401() throws Exception {
        resultActions.andExpect(status().isUnauthorized());
        return this;
    }

    public MockMvcResponseBuilder expect400() throws Exception {
        resultActions.andExpect(status().isBadRequest());
        return this;
    }

    public MockMvcResponseBuilder expectStatus(HttpStatus httpStatus) throws Exception {
        resultActions.andExpect(status().is(httpStatus.value()));
        return this;
    }

    public MockMvcResponseBuilder expectHeader(String name, String value) throws Exception {
        resultActions.andExpect(header().string(name, value));
        return this;
    }

    public <T> T returnResponseBody() throws Exception {
        return objectMapper.readValue(responseBody(), new TypeReference<T>() { });
    }

    public <T> T responseBodyAs(Class<T> responseType) throws Exception {
        return objectMapper.readValue(responseBody(), responseType);
    }

    public <T> List<T> responseBodyAsListOf(Class<T> listItemType) throws Exception {
        JavaType responseType = objectMapper.getTypeFactory().constructCollectionType(List.class, listItemType);
        return objectMapper.readValue(responseBody(), responseType);
    }

    private String responseBody() throws UnsupportedEncodingException {
        return resultActions.andReturn().getResponse().getContentAsString();
    }
}
