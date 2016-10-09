package kona.model.domain.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {

    private String productCode;

    private String name;

    public Product(String name, String productCode) {
        this.name = name;
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public String getProductCode() {
        return productCode;
    }

    /* surrogate key and no arg ctor for hibernate */

    @Id
    @GeneratedValue
    private long id;

    public Product() {}
}
