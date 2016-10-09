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

    public Product(String name, String productCode) {
        this.name = name;
        this.productCode = new ProductCode(productCode);
    }

    public String getName() {
        return name;
    }

    public String getProductCode() {
        return productCode.toString();
    }

    /* surrogate key and no arg ctor for hibernate */

    @Id
    @GeneratedValue
    private long id;

    public Product() {}
}
