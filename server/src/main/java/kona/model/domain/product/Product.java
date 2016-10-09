package kona.model.domain.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private long id;

    private String productCode;

    private String name;

    public Product() {/* hibernate */}

    public Product(long id, String name, String productCode) {
        this.id = id;
        this.name = name;
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public String getProductCode() {
        return productCode;
    }
}
