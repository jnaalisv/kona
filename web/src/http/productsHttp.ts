import {
    httpGET,
    httpDELETE,
    httpPUT,
    httpPOST
} from './http'
import {Product} from "../components/Product";

const productsUrl = 'http://localhost:8080/products';

export const saveOrUpdateProduct = (product: Product) => {
    if (product.id > 0) {
        return httpPUT(`${productsUrl}/${product.id}`, product);
    } else {
        return httpPOST(productsUrl, product);
    }
};

export function getProducts(): Promise<Product[]> {
    return httpGET(productsUrl);
}

export function deleteProduct(productId: number) {
    return httpDELETE(`${productsUrl}/${productId}`)
}

export function getProduct(productId: number): Promise<Product> {
    return httpGET(`${productsUrl}/${productId}`);
}

