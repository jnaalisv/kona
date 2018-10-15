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