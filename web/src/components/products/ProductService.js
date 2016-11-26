import http from '../http'

const api = {
    getProducts: getProducts,
    getProduct: getProduct,
    save: save,
};

const productsUrl = 'http://localhost:9999/kona/products';

function save(product) {
    if (product.version >= 0) {
        return http.PUT(`${productsUrl}/${product.productCode}`, product);
    } else {
        return http.POST(productsUrl, product);
    }
}

function getProducts(name) {

    const queryParams = {};

    if (name) {
        queryParams.name = name;
    }

    return http.GET(productsUrl, {queryParams});
}

function getProduct(productId) {
    return http.GET(`${productsUrl}/${productId}`);
}

export default api;
