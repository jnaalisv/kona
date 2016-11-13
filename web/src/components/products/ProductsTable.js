import React from 'react'
import {Match} from 'react-router'

import ProductRow from './ProductRow'
import Product from './Product'
import productService from './ProductService'

class Products extends React.Component {
    constructor() {
        super();
        this.state = {
            products: [],
            lastError: null
        }
    };

    componentDidMount() {
        productService.getProducts()
            .then((products => {
                const productRows = products.map((product, index) => <ProductRow key={index} product={product}/>);
                this.setState({products: productRows});
            }))
            .catch((error) => {
                this.setState({lastError: error.message});
                return [];
            })
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