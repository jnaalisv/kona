import http from '../http'

const api = {
    getProducts: getProducts,
    getProduct: getProduct,
    save: save,
};

const productsUrl = 'http://localhost:9999/kona/products';

function save(product) {
    if (product.id) {
        return http.PUT(`${productsUrl}/${product.id}`, product);
    } else {
        return http.POST(productsUrl, product);
    }
}

function getProducts() {
    return http.GET(productsUrl);
}

function getProduct(productId) {
    return http.GET(`${productsUrl}/${productId}`);
}

export default api;