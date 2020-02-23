package kona.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Product {

    private long id;
    private long version;
    private LocalDateTime created;
    private String name;
    private String code;
    private ProductType productType;
    private BigDecimal price;

    @java.beans.ConstructorProperties({"name", "code", "productType", "price"})
    public Product(String name, String code, ProductType productType, BigDecimal price) {
        this.name = name;
        this.code = code;
        this.productType = productType;
        this.price = price;
        this.version =1L;
        this.created = LocalDateTime.now();
    }

    @java.beans.ConstructorProperties({"id", "version", "created", "name", "code", "productType", "price"})
    public Product(long id, long version, LocalDateTime created, String name, String code, ProductType productType, BigDecimal price) {
        this.id = id;
        this.version = version;
        this.created = created;
        this.name = name;
        this.code = code;
        this.productType = productType;
        this.price = price;
    }

    public String name() {
        return name;
    }

    public long id() {
        return this.id;
    }

    public ProductType productType() {
        return productType;
    }

    public BigDecimal price() {
        return price;
    }

    public long version() {
        return version;
    }

    public LocalDateTime created() {
        return created;
    }

    public String code() {
        return code;
    }
}
