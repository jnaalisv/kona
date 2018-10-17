import fetch from 'isomorphic-unfetch'
const productsUrl = 'http://localhost:8080/products';

export const getProductById = async productId => {
    const url = `${productsUrl}/${productId}`;
    try {
        const product = await httpGET(url);
        return { product }
    } catch (err) {
        console.log('err ', err);
        return { product: {}, errorMessage: `Http Request to ${url} failed, ${err}` }
    }
};

export const getAllProducts = async () => {
    try {
        const products = await httpGET(productsUrl);
        return { products }
    } catch (err) {
        console.log('err ', err);
        return { products: [], errorMessage: `Http Request to ${productsUrl} failed, ${err}` }
    }
};

const httpGET = (url) => fetch(url).then(response => {
    if (response.status === 200) {
        return response.json();
    } else {
        console.log('failed ', response);
        throw `Error ${response.status}`
    }
});

const defaultOptions = {
    headers: {
        'Content-Type': 'application/json;charset=UTF-8',
    }
};

const handleResponse = response => {
    if (response.status < 300) {

        const contentTypeHeader = response.headers.get('content-type');

        if (contentTypeHeader && contentTypeHeader.toLowerCase().startsWith('application/json')) {
            return response.json();
        }
        return response.text();
    }
    return Promise.reject(new Error(response));
};

const doHttp = (method, url, body) => {
    const options = {...defaultOptions};
    options.method = method;

    if (body) {
        options.body = JSON.stringify(body);
    }

    return fetch(url, options).then(handleResponse)
};

export const httpPOST = (url, body) => doHttp('POST', url, body);
export const httpPUT = (url, body) => doHttp('PUT', url, body);


export const saveOrUpdateProduct = product => {
    if (product.id > 0) {
        return httpPUT(`${productsUrl}/${product.id}`, product);
    } else {
        return httpPOST(productsUrl, product);
    }
};