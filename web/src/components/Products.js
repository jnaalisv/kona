import React from 'react'
import {Match} from 'react-router'

import ProductRow from './ProductRow'
import Product from './Product'

const url = 'http://localhost:9999/kona/products';

const httpInit = {
    method: 'GET',
    mode: 'cors',
    headers: {
        'ContentType': 'application/json'
    }
};

class Products extends React.Component {
    constructor() {
        super();
        this.getProducts = this.getProducts.bind(this);

        this.state = {
            products: [],
            lastError: null
        }
    };

    getProducts() {
        return window
            .fetch(url, httpInit)
            .then((response) => {
                return response.json();
            }).catch((error) => {
                this.setState({lastError: error.message});
                return [];
            });
    }

    componentDidMount() {
        this.getProducts()
            .then((products => {
                const productRows = products.map((product, index) => <ProductRow key={index} product={product}/>);
                this.setState({products: productRows});
            }));
    }

    render() {

        const lastError = this.state.lastError;

        return (
            <div>
                <Match pattern={`${this.props.pathname}/:productId`} component={Product}/>

                {lastError && <p >{lastError}</p>}
                <Match pattern={this.props.pathname} exactly render={() => (
                    <table>
                        <thead>
                        <tr>
                            <th>code</th>
                            <th>name</th>
                        </tr>
                        </thead>
                        <tbody>
                        {this.state.products}
                        </tbody>
                    </table>
                )}/>
            </div>
        )
    }
}

export default Products;