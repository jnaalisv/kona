import http from '../http'

const api = {
    getProducts: getProducts,
    getProduct: getProduct,
    save: save,
    deleteProduct: deleteProduct
};

const productsUrl = 'http://localhost:9999/kona/products';

function save(product) {
    if (product.id > 0) {
        return http.PUT(`${productsUrl}/${product.id}`, product);
    } else {
        return http.POST(productsUrl, product);
    }
}

function getProducts(name) {

    const queryParams = {};

    if (name) {
        queryParams.name = name;
    }

    return http.GET(productsUrl, queryParams);
}

function deleteProduct(productId) {
    return http.DELETE(`${productsUrl}/${productId}`)
}

function getProduct(productId) {
    return http.GET(`${productsUrl}/${productId}`);
}

export default api;
