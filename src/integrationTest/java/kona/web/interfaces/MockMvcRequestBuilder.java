package kona.web.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Arrays;
import java.util.List;

public class MockMvcRequestBuilder {

    private MockMvc mockMvc;
    private MockHttpServletRequestBuilder servletRequestBuilder;
    private ObjectMapper objectMapper;

    public MockMvcRequestBuilder(MockMvc mockMvc, ObjectMapper objectMapper, MockHttpServletRequestBuilder servletRequestBuilder) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.servletRequestBuilder = servletRequestBuilder;
    }

    public MockMvcRequestBuilder param(String key, String value) {
        servletRequestBuilder.param(key, value);
        return this;
    }

    public MockMvcRequestBuilder accept(MediaType mediaType) {
        servletRequestBuilder.accept(mediaType);
        return this;
    }

    public MockMvcRequestBuilder acceptApplicationJson() {
        return accept(MediaType.APPLICATION_JSON);
    }


    public MockMvcRequestBuilder contentTypeApplicationJson() {
        servletRequestBuilder.contentType(MediaType.APPLICATION_JSON);
        return this;
    }

    public MockMvcRequestBuilder content(String content) {
        servletRequestBuilder.content(content);
        return this;
    }

    public MockMvcRequestBuilder content(Object content) throws JsonProcessingException {
        servletRequestBuilder.content(objectMapper.writeValueAsString(content));
        return this;
    }

    public MockMvcRequestBuilder content(byte[] content) throws JsonProcessingException {
        servletRequestBuilder.content(content);
        return this;
    }

    /**
     * Use this method to post a list of items
     */
    public <T> MockMvcRequestBuilder contentAsListOf(Class<T> clazz, T...content) throws JsonProcessingException {

        JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);

        servletRequestBuilder.content(
                objectMapper
                        .writerFor(type)
                        .writeValueAsString(Arrays.asList(content)));

        return this;
    }

    public MockMvcResponseBuilder perform() throws Exception {
        return new MockMvcResponseBuilder(objectMapper, mockMvc.perform(servletRequestBuilder));
    }
}