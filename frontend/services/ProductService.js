import fetch from 'isomorphic-unfetch'

const productsUrl = 'http://localhost:8080/products';

export const getProductById = async productId => {
    let response;
    const url = `${productsUrl}/${productId}`;
    try {
        response = await fetch(url);
        if (response.status === 404) {
            return { product: {}, errorMessage: `Product Not found by id=${productId}, url was ${url}`};
        }
    } catch (err) {
        console.log('err ', err);
        return { product: {}, errorMessage: `Http Request to ${url} failed, is the backend down?` }
    }

    const product = await response.json();
    return { product }
};

export const getAllProducts = async () => {
    let response;
    try {
        response = await fetch(productsUrl);
    } catch (err) {
        return { products: [], errorMessage: `request to ${productsUrl} failed, is the backend down?` }
    }

    const json = await response.json();
    return { products: json }
};


