
const url = 'http://localhost:9999/kona/products';

const getConfig = {
    method: 'GET',
    mode: 'cors',
    headers: {
        'ContentType': 'application/json'
    }
};

const api = {
    getProducts: getProducts,
    getProduct: getProduct,
    save: save,
};

function save(product) {
    console.log('save product ' + product.productCode);

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
            .then((response) => {
                return response.json();
            });
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
            .then((response) => {
                return response.json();
            });
    }
}


function getProducts() {
    return fetch(url, getConfig)
        .then((response) => {
            return response.json();
        });
}

function getProduct(productId) {
    return fetch(url + '/'+productId, getConfig)
        .then((response) => {
            return response.json();
        });
}

export default api;