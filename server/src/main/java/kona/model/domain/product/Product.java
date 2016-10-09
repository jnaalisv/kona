package kona.model.domain.product;

import org.hibernate.annotations.NaturalId;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {

    @NaturalId
    @Embedded
    private ProductCode productCode;

    private String name;

    public Product(String name, ProductCode productCode) {
        this.name = name;
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public ProductCode getProductCode() {
        return productCode;
    }

    /* surrogate key and no arg ctor for hibernate */

    @Id
    @GeneratedValue
    private long id;

    public Product() {}
}
