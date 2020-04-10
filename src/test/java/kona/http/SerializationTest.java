package kona.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kona.domain.Product;
import kona.domain.ProductType;
import kona.persistance.InMemProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class SerializationTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void serializingProductToJsonSucceeds() throws JsonProcessingException {
        Product product = new InMemProductRepository().getAll().get(0);

        String json = this.objectMapper.writeValueAsString(product);

        assertThat(json).isNotEmpty();
        assertThat(json).containsSequence("\"name\"");
        assertThat(json).containsSequence("\"code\"");
        assertThat(json).containsSequence("\"productType\"");
    }

    @Test
    void deserializationToObjectSucceeds() throws JsonProcessingException {
        String json = """
                {
                    "code": "CCU",
                    "created": "2020-04-10T20:43:45.591918",
                    "id": 2,
                    "name": "Central Computing Unit",
                    "price": 280.0,
                    "productType": "EQUIPMENT",
                    "version": 0
                }
                """;

        Product product = this.objectMapper.readValue(json, Product.class);

        assertThat(product.code()).isEqualTo("CCU");
        assertThat(product.productType()).isEqualTo(ProductType.EQUIPMENT);
        assertThat(product.name()).isEqualTo("Central Computing Unit");
        assertThat(product.id()).isEqualTo(2);
    }
}
