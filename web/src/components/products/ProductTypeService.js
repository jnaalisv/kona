import http from '../http'

const api = {
    getProductTypes: getProductTypes
};

const url = 'http://localhost:9999/kona/product-types';

function getProductTypes() {
    return http.GET(url);
}

export default api;
