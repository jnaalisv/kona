import {
    httpGET,
    httpDELETE,
    httpPUT,
    httpPOST
} from './http'

const productsUrl = 'http://localhost:9999/kona/products';

export function saveOrUpdateProduct(product) {
    if (product.id > 0) {
        return httpPUT(`${productsUrl}/${product.id}`, product);
    } else {
        return httpPOST(productsUrl, product);
    }
}

export function getProducts(name) {

    const queryParams = {};

    if (name) {
        queryParams.name = name;
    }

    return httpGET(productsUrl, queryParams);
}

export function deleteProduct(productId) {
    return httpDELETE(`${productsUrl}/${productId}`)
}

export function getProduct(productId) {
    return httpGET(`${productsUrl}/${productId}`);
}

