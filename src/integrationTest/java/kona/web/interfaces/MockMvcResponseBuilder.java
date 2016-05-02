package kona.web.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MockMvcResponseBuilder {

    private MockMvc mockMvc;
    private MockHttpServletRequestBuilder servletRequestBuilder;
    private ObjectMapper objectMapper;

    public MockMvcResponseBuilder(MockMvc mockMvc, ObjectMapper objectMapper, MockHttpServletRequestBuilder servletRequestBuilder) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.servletRequestBuilder = servletRequestBuilder;
    }

    public MockMvcResponseBuilder param(String key, String value) {
        servletRequestBuilder.param(key, value);
        return this;
    }

    public MockMvcResponseBuilder accept(MediaType mediaType) {
        servletRequestBuilder.accept(mediaType);
        return this;
    }

    public MockMvcResponseBuilder acceptApplicationJson() {
        return accept(MediaType.APPLICATION_JSON);
    }


    public MockMvcResponseBuilder contentTypeApplicationJson() {
        servletRequestBuilder.contentType(MediaType.APPLICATION_JSON);
        return this;
    }

    public MockMvcResponseBuilder content(String content) {
        servletRequestBuilder.content(content);
        return this;
    }

    public MockMvcResponseBuilder content(Object content) throws JsonProcessingException {
        servletRequestBuilder.content(objectMapper.writeValueAsString(content));
        return this;
    }

    public MockMvcResponseBuilder content(byte[] content) throws JsonProcessingException {
        servletRequestBuilder.content(content);
        return this;
    }

    /**
     * Use this method to post a list of items
     */
    public <T> MockMvcResponseBuilder contentAsListOf(Class<T> clazz, T...content) throws JsonProcessingException {

        JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);

        servletRequestBuilder.content(
                objectMapper
                        .writerFor(type)
                        .writeValueAsString(Arrays.asList(content)));

        return this;
    }

    public void expect404() throws Exception {
        mockMvc.perform(servletRequestBuilder).andExpect(status().isNotFound());
    }

    public void expect200() throws Exception {
        mockMvc.perform(servletRequestBuilder).andExpect(status().isOk());
    }

    public void expect403() throws Exception {
        mockMvc.perform(servletRequestBuilder).andExpect(status().isForbidden());
    }

    public void expect401() throws Exception {
        mockMvc.perform(servletRequestBuilder).andExpect(status().isUnauthorized());
    }

    public void expect400() throws Exception {
        mockMvc.perform(servletRequestBuilder).andExpect(status().isBadRequest());
    }

    public <T> T returnResponseBody() throws Exception {
        ResultActions resultActions = mockMvc.perform(servletRequestBuilder);

        resultActions.andExpect(status().isOk());

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();

        return objectMapper.readValue(responseBody, new TypeReference<T>() { });
    }

    public <T> T responseBodyAs(Class<T> responseType) throws Exception {

        ResultActions resultActions = mockMvc.perform(servletRequestBuilder);

        resultActions.andExpect(status().isOk());

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();

        return objectMapper.readValue(responseBody, responseType);
    }

    public <T> List<T> responseBodyAsListOf(Class<T> listItemType) throws Exception {

        ResultActions resultActions = mockMvc.perform(servletRequestBuilder);

        resultActions.andExpect(status().isOk());

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();

        JavaType responseType = objectMapper.getTypeFactory().constructCollectionType(List.class, listItemType);

        return objectMapper.readValue(responseBody, responseType);
    }
}