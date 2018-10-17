import Layout from '../components/Layout'
import ProductListing from '../components/ProductListing'
import { getAllProducts } from '../services/ProductService';
import React from 'react'

class ProductListingPage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            products: []
        }
    }

    componentDidMount() {
        getAllProducts()
            .then(response => {
                this.setState({
                    products: response.products,
                    errorMessage: response.errorMessage
                })
            });
    }

    render() {
        return (
            <Layout error={this.state.errorMessage}>
                <ProductListing products={this.state.products} />
            </Layout>
        );
    }
}

export default ProductListingPage