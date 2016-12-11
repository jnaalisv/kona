import http from '../http'

const api = {
    getProductTypes: getProductTypes
};

const url = 'http://localhost:9999/kona/product-types';

let productTypes = [];

function getProductTypes() {

    if (productTypes.length === 0) {
        return http.GET(url).then(fetchedProductTypes => productTypes = fetchedProductTypes);
    }

    return Promise.resolve(productTypes);
}

export default api;
