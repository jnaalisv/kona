package kona.model.domain.product;

import org.hibernate.annotations.NaturalId;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Product {

    @Version
    private long version = 0l;

    @NaturalId
    @Embedded
    private ProductCode productCode;

    private String name;

    public Product(String name, ProductCode productCode) {
        this.name = name;
        this.productCode = productCode;
    }

    public Product(long id, long version, String name, ProductCode productCode) {
        this.id = id;
        this.version = version;
        this.name = name;
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public ProductCode getProductCode() {
        return productCode;
    }

    public long getVersion() {
        return version;
    }

    /* surrogate key and no arg ctor for hibernate */
    @Id
    @GeneratedValue
    private long id;

    public Product() {}

    public long getId() {
        return id;
    }
}
