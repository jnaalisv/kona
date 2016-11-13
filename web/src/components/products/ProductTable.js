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
            .then((products => {
                const productRows = products.map((product, index) => <ProductRow key={index} product={product}/>);
                this.setState({products: productRows});
            }));
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
                {this.state.products}
                </tbody>
            </table>
        )
    }
}

export default ProductTable;