import React from 'react'

const url = 'http://localhost:9999/kona/products/';

const httpInit = {
    method: 'GET',
    mode: 'cors',
    headers: {
        'ContentType': 'application/json'
    }
};

class Product extends React.Component {
    constructor() {
        super();
        this.state = {product: {}};

        this.getProduct = this.getProduct.bind(this);
    }

    getProduct(productId) {
        return window
            .fetch(url + productId, httpInit)
            .then((response) => {
                return response.json();
            }).catch((error) => {
                console.log('error: ' + error);
                return null;
            });
    }

    componentDidMount() {
        this.getProduct(this.props.params.productId)
            .then((product => {
                this.setState({product: product});
            }));
    }

    render () {
        return (
            <div>
                <div>name: {this.state.product.name}</div>
                <div>productCode: {this.state.product.productCode}</div>
            </div>
        )
    }
}

export default Product;