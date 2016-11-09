
const url = 'http://localhost:9999/kona/products';

const httpInit = {
    method: 'GET',
    mode: 'cors',
    headers: {
        'ContentType': 'application/json'
    }
};

const api = {
    getProducts: getProducts,
    getProduct: getProduct
};


function getProducts() {
    return window
        .fetch(url, httpInit)
        .then((response) => {
            return response.json();
        });
}

function getProduct(productId) {
    return window
        .fetch(url + '/'+productId, httpInit)
        .then((response) => {
            return response.json();
        });
}

export default api;