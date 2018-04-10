package kona.http.product;

import kona.domain.product.Product;
import kona.domain.product.ProductCode;

public class ProductAssembler {

    public static Product assembleFrom(ProductDTO productDTO) {

        Product product = new Product(
                productDTO.id,
                productDTO.version,
                productDTO.createTime,
                productDTO.name,
                new ProductCode(productDTO.productCode),
                productDTO.price);

        product.setProductType(productDTO.productType);

        return product;
    }

    public static ProductDTO assembleTo(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.id = product.getId();
        productDTO.version = product.getVersion();
        productDTO.name = product.getName();
        productDTO.productCode = product.getProductCode().toString();
        productDTO.createTime = product.getCreateTime();
        productDTO.price = product.getPrice();
        productDTO.productType = product.getProductType();
        return productDTO;
    }

}
