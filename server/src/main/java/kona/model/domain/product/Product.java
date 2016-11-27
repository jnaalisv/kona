package kona.model.domain.product;

import kona.model.domain.CurrencyAmount;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NaturalId;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import java.time.LocalDateTime;

@Entity
@DynamicUpdate
public class Product {

    @Version
    private long version = 0l;

    @Column(insertable = false, updatable = false)
    private LocalDateTime createTime;

    @NaturalId
    @Embedded
    private ProductCode productCode;

    private String name;

    @AttributeOverrides({
        @AttributeOverride(name="amount",column=@Column(name="priceAmount")),
        @AttributeOverride(name="currency",column=@Column(name="priceCurrency"))
    })
    @Embedded
    private CurrencyAmount price;

    public Product(String name, ProductCode productCode) {
        this.name = name;
        this.productCode = productCode;
    }

    public Product(long id, long version, LocalDateTime createTime, String name, ProductCode productCode, CurrencyAmount price) {
        this.id = id;
        this.version = version;
        this.createTime = createTime;
        this.name = name;
        this.productCode = productCode;
        this.price = price;
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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public CurrencyAmount getPrice() {
        return price;
    }
}
