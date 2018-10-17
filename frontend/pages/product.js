import React from 'react'
import { withRouter } from 'next/router'
import Layout from '../components/Layout'
import ProductForm from '../components/ProductForm'
import { getProductById, saveOrUpdateProduct } from '../services/ProductService';

class ProductPage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            product: {
                name: '',
                productCode: ''
            },
            errorMessage: ''
        };

        this.handleChange = this.handleChange.bind(this);
        this.saveProduct = this.saveProduct.bind(this);
    }

    componentDidMount() {
        getProductById(this.props.router.query.id)
            .then(response => {
                this.setState({
                    product: response.product,
                    errorMessage: response.errorMessage
                });
            });
    }

    handleChange(event) {
        event.preventDefault();
        const product = {...this.state.product};
        product[event.target.name] = event.target.value;
        this.setState({ product });
    }

    saveProduct() {

        saveOrUpdateProduct(this.state.product)
            .then(product => {
                this.setState({ product });
            }, err => {
                console.log('Error ', err);
                this.setState({ errorMessage: err.message });
            });
    }

    render() {
        const { product, errorMessage } = this.state;
        return (
            <Layout error={errorMessage}>
                <ProductForm product={product} handleChange={this.handleChange}/>
                <button onClick={this.saveProduct}>Save</button>
            </Layout>
        );
    }
}

export default withRouter(ProductPage)