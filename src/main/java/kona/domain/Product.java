package kona.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Product {

    private long id;
    private long version;
    private LocalDateTime created;
    private String name;
    private ProductType productType;
    private BigDecimal price;

    @java.beans.ConstructorProperties({"name", "productType", "price"})
    public Product(String name, ProductType productType, BigDecimal price) {
        this.name = name;
        this.productType = productType;
        this.price = price;
        this.version =1L;
        this.created = LocalDateTime.now();
    }

    @java.beans.ConstructorProperties({"id", "version", "created", "name", "productType", "price"})
    public Product(long id, long version, LocalDateTime created, String name, ProductType productType, BigDecimal price) {
        this.id = id;
        this.version = version;
        this.created = created;
        this.name = name;
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
}
