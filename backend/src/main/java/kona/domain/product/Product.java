package kona.domain.product;

import kona.domain.CurrencyAmount;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Product {

    @Version
    private long version = 0L;

    @Column(insertable = false, updatable = false)
    private LocalDateTime createTime;

    @Embedded
    private ProductCode productCode;

    private String name;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

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

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}
