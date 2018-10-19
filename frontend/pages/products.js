import Layout from '../components/Layout'
import ProductListing from '../components/ProductListing'
import { getAllProducts } from '../services/productsHttp';
import React from 'react'
import Notifications from "../components/Notifications";

class ProductListingPage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            products: [],
            notifications: []
        };
        this.addError = this.addError.bind(this);
    }

    componentDidMount() {
        getAllProducts()
            .then(products => {
                this.setState({ products });
                this.addInfo('Products loaded');
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
        const { products, notifications } = this.state;
        return (
            <Layout>
                <ProductListing products={products} />
                <Notifications notifications={notifications} />
            </Layout>
        );
    }
}

export default ProductListingPage