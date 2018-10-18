import { httpGET, httpPUT, httpPOST } from './http'
const productsUrl = 'http://localhost:8080/products';

export const getProductById = productId =>
    httpGET(`${productsUrl}/${productId}`);

export const getAllProducts = () =>
    httpGET(productsUrl);

export const saveOrUpdateProduct = product => {
    if (product.id > 0) {
        return httpPUT(`${productsUrl}/${product.id}`, product);
    } else {
        return httpPOST(productsUrl, product);
    }
};