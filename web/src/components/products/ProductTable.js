import React from 'react'

import ProductRow from './ProductRow'
import productService from './ProductService'

class ProductTable extends React.Component {
    constructor() {
        super();
        this.state = {
            products: []
        }
    };

    componentDidMount() {
        productService.getProducts()
            .then((products => this.setState({products})));
    }

    render() {
        return (
            <table>
                <thead>
                <tr>
                    <th>code</th>
                    <th>name</th>
                </tr>
                </thead>
                <tbody>
                {Object
                    .keys(this.state.products)
                    .map(index => {
                        return <ProductRow key={index} product={this.state.products[index]}/>
                    })
                }
                </tbody>
            </table>
        )
    }
}

export default ProductTable;