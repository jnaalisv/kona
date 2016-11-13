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

    saveProduct(e) {
        productService.save(this.state.product);
    }

    render () {
        return (
            <div>
                <div>name: {this.state.product.name}</div>
                <div>productCode: {this.state.product.productCode}</div>
                <button onClick={(e) => this.saveProduct(e)}>Save</button>
            </div>
        )
    }
}

export default Product;