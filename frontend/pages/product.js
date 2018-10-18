import React from 'react'
import { withRouter } from 'next/router'
import Layout from '../components/Layout'
import Notifications from '../components/Notifications'
import ProductForm from '../components/ProductForm'
import { getProductById, saveOrUpdateProduct } from '../services/productsHttp';

class ProductPage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            product: {
                name: '',
                productCode: ''
            },
            errorMessage: '',
            notifications: []
        };

        this.handleChange = this.handleChange.bind(this);
        this.saveProduct = this.saveProduct.bind(this);
        this.addError = this.addError.bind(this);
    }

    componentDidMount() {
        getProductById(this.props.router.query.id)
            .then(product => {
                this.setState({ product });
                this.addInfo('Product info loaded');
            }, this.addError);
    }

    handleChange(event) {
        event.preventDefault();
        const product = {...this.state.product};
        product[event.target.name] = event.target.value;
        this.setState({ product });
    }

    saveProduct() {
        this.setState({ notifications: [] });

        saveOrUpdateProduct(this.state.product)
            .then(product => {
                this.setState({ product });
                this.addInfo('Product saved');
            }, this.addError);
    }

    addInfo(message) {
        this.addNotification('info', message);
    }

    addNotification(type, message) {
        const notifications = [...this.state.notifications];
        notifications.push({ type, message: `${new Date().toLocaleTimeString()}: ${message}` });
        this.setState({ notifications });
    }

    addError(error) {
        let errorMessage;

        if (error instanceof TypeError) {
            errorMessage = error.message;
        } else if (typeof error === 'string') {
            errorMessage = error;
        } else {
            console.log('Unknown error ', error);
            errorMessage = "Unknown error, check console log."
        }
        this.addNotification('error', errorMessage);
    }

    render() {
        const { product, errorMessage, notifications } = this.state;
        return (
            <Layout error={errorMessage}>
                <ProductForm product={product} handleChange={this.handleChange}/>
                <button onClick={this.saveProduct}>Save</button>
                <Notifications notifications={notifications} />
            </Layout>
        );
    }
}

export default withRouter(ProductPage)