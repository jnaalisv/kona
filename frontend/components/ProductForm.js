import React from 'react'

const CurrencyAmount = ({currencyAmount}) => (<p>price: {currencyAmount.amount} {currencyAmount.currency}</p>);

const ProductForm = ({ product, handleChange }) => (
    <div>
        <h4>Product</h4>

        <p>name: <input name='name' value={product.name} onChange={handleChange} /></p>
        <p>productCode: <input name='productCode' value={product.productCode} onChange={handleChange}/></p>
        <p>productType: {product.productType}</p>
        {product.price && <CurrencyAmount currencyAmount={product.price} />}

        <p>id: {product.id}</p>
        <p>version: {product.version}</p>
        <p>createTime: {product.createTime}</p>
    </div>
);

export default ProductForm;