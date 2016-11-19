import React from 'react'
import productService from './ProductService'
import Notifications from '../Notifications'
import HttpError from '../HttpError'

class Product extends React.Component {
    constructor() {
        super();
        this.state = {product: {}, notifications: []};

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

        const notifications = [...this.state.notifications];
        notifications.push({type:'error', message: errorMessage});
        this.setState({ notifications });
    }

    componentDidMount() {
        productService
            .getProduct(this.props.params.productId)
            .then(product => this.setState({product: product}), this.addError);
    }

    saveProduct(e) {
        this.setState({ notifications: [] });

        productService
            .save(this.state.product)
            .then(product => {
                this.setState({product: product});
                const notifications = [...this.state.notifications];
                notifications.push({type:'info', message:'Product saved'});
                this.setState({ notifications });
            }, this.addError);
    }

    render () {
        const product = this.state.product;

        return (
            <div>
                <div>name: {product.name}</div>
                <div>productCode: {product.productCode}</div>
                <div>version: {product.version}</div>
                <button onClick={(e) => this.saveProduct(e)}>Save</button>
                <Notifications notifications={this.state.notifications} />
            </div>
        )
    }
}

export default Product;