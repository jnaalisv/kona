package kona.web.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

public class SerializationConfig {

    @Bean
    public static ObjectMapper objectMapper() {
        return new Jackson2ObjectMapperBuilder()
                .featuresToDisable()
                .featuresToEnable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .indentOutput(true)
                .build();
    }
}
