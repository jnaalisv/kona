import errors from '../errors'

const api = {
    getProducts: getProducts,
    getProduct: getProduct,
    save: save,
};

const url = 'http://localhost:9999/kona/products';

const getConfig = {
    method: 'GET',
    mode: 'cors',
    headers: {
        'ContentType': 'application/json'
    }
};

function parseJSON(response) {
    return response.json();
}

function checkStatus(response) {
    if (response.status >= 200 && response.status < 300) {
        return response;
    } else {
        var error = new Error(response.statusText);
        error.response = response;
        throw error;
    }
}

function save(product) {
    if (product.id) {
        return fetch(url +'/' + product.id, {
                method: 'PUT',
                mode: 'cors',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(product)
            })
            .then(checkStatus)
            .then(parseJSON)
            .catch(errors.handleError);
    } else {
        return fetch(url, {
                method: 'POST',
                mode: 'cors',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(product)
            })
            .then(checkStatus)
            .then(parseJSON)
            .catch(errors.handleError);
    }
}

function getProducts() {
    return fetch(url, getConfig)
        .then(checkStatus)
        .then(parseJSON)
        .catch(errors.handleError);
}

function getProduct(productId) {
    return fetch(url + '/'+productId, getConfig)
        .then(checkStatus)
        .then(parseJSON)
        .catch(errors.handleError);
}

export default api;