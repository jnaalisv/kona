import {
    httpGET,
    httpDELETE,
    httpPUT,
    httpPOST
} from './http'
import {Product} from "../components/Product";

const productsPath = 'products';

export const saveOrUpdateProduct = (product: Product): Promise<Product> => {
    if (product.id > 0) {
        return httpPUT(`${productsPath}/${product.id}`, product);
    } else {
        return httpPOST(productsPath, product);
    }
};

export function getProducts(): Promise<Product[]> {
    return httpGET(productsPath);
}

export function deleteProduct(productId: number) {
    return httpDELETE(`${productsPath}/${productId}`)
}

export function getProduct(productId: number): Promise<Product> {
    return httpGET(`${productsPath}/${productId}`);
}

