import React from 'react'
import productService from './ProductService'
import ErrorBox from '../ErrorBox'
import HttpError from '../HttpError'

class Product extends React.Component {
    constructor() {
        super();
        this.state = {product: {}, errors:[]};

        this.addError = this.addError.bind(this);
    }

    addError(error) {
        let errorMessage;

        if (error instanceof TypeError) {
            errorMessage = error.message;
        } else if (error instanceof HttpError) {
            const response = error.response;
            errorMessage = `${response.status} ${response.statusText}`;
        }

        const errors = [...this.state.errors];
        errors.push(errorMessage);
        this.setState({ errors: errors });
    }

    componentDidMount() {
        productService
            .getProduct(this.props.params.productId)
            .then(product => this.setState({product: product}))
            .catch(this.addError);
    }

    saveProduct(e) {
        this.setState({ errors: [] });

        productService
            .save(this.state.product)
            .then((product => this.setState({product: product})))
            .catch(this.addError);
    }

    render () {
        return (
            <div>
                <div>name: {this.state.product.name}</div>
                <div>productCode: {this.state.product.productCode}</div>
                <button onClick={(e) => this.saveProduct(e)}>Save</button>
                <ErrorBox errors={this.state.errors}/>
            </div>
        )
    }
}

export default Product;