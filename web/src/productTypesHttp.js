import { httpGET } from './http'

const url = 'http://localhost:9999/kona/product-types';

let productTypes = [];

export function getProductTypes() {

    if (productTypes.length === 0) {
        return httpGET(url).then(fetchedProductTypes => productTypes = fetchedProductTypes);
    }

    return Promise.resolve(productTypes);
}
