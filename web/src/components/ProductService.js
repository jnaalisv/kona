
const url = 'http://localhost:9999/kona/products';

const httpInit = {
    method: 'GET',
    mode: 'cors',
    headers: {
        'ContentType': 'application/json'
    }
};

class ProductService {

    getProducts() {
        return window
            .fetch(url, httpInit)
            .then((response) => {
                return response.json();
            });
    }

    getProduct(productId) {
        return window
            .fetch(url + '/'+productId, httpInit)
            .then((response) => {
                return response.json();
            });
    }
}

const productService = new ProductService();

export default productService;