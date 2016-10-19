import React from 'react'
import ProductRow from './ProductRow'

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
            products: []
        }
    };

    getProducts() {
        return window
            .fetch(url, httpInit)
            .then((response) => {
                return response.json();
            }).catch((error) => {
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
        return (
            <div>
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
            </div>
        )
    }
}

export default Products;