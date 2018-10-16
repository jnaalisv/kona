const CurrencyAmount = ({currencyAmount}) => (<p>price: {currencyAmount.amount} {currencyAmount.currency}</p>);

const ProductDetails = ({product}) => (
    <div>
        <h4>Product</h4>
        <p>id: {product.id}</p>
        <p>name: {product.name}</p>
        <p>productCode: {product.productCode}</p>
        <p>productType: {product.productType}</p>
        <p>version: {product.version}</p>
        <p>createTime: {product.createTime}</p>
        {product.price && <CurrencyAmount currencyAmount={product.price} />}
    </div>
);

export default ProductDetails;