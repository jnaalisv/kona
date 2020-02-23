import React from 'react'
import {Product} from "./Product";

export interface ProductRowProps {
    product: Product
}

const ProductRow = (props: ProductRowProps) => {
    return (
        <tr>
            <td>{props.product.id}</td>
            <td>{props.product.name}</td>
            <td>{props.product.code}</td>
        </tr>
    )
};

export default ProductRow;
