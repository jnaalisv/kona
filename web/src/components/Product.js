import React from 'react'
import productService from './ProductService'

class Product extends React.Component {
    constructor() {
        super();
        this.state = {product: {}};
    }

    componentDidMount() {
        productService.getProduct(this.props.params.productId)
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