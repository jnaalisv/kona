import React from 'react'
import {Product} from "./Product";
import {getProduct, saveOrUpdateProduct} from "../http/productsHttp";

export interface ProductEditProps {
    productId: number;
}

const initialState: Product = {code: "", created: "", id: 0, name: "", price: 0, productType: "", version: 0};

const ProductEdit = ({ productId }: ProductEditProps) => {

    const [product, setProduct] = React.useState(initialState);

    React.useEffect(() => {getProduct(productId).then(setProduct);}, [productId]);

    const onChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        event.preventDefault();
        const updatedProd: Product = {
            ...product,
            [event.target.name]: event.target.value
        }
        setProduct(updatedProd);
    }

    const saveProduct = (event: React.MouseEvent<HTMLButtonElement>) => {
        saveOrUpdateProduct(product).then(setProduct);
    }

    return (
        <div>
            <div>created: {product.created}</div>
            <div>name: <input name="name" value={product.name} onChange={onChange}/></div>
            <div>productCode: <input name="code" value={product.code} onChange={onChange}/></div>
            <div>price: {product.price}</div>
            <div>version: {product.version}</div>
            <div>product type:{product.productType}
            </div>
            <button onClick={(event: React.MouseEvent<HTMLButtonElement>) => saveProduct(event)}>Save</button>
        </div>
    )
}

export default ProductEdit;