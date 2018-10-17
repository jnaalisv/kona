import React from 'react'
import { withRouter } from 'next/router'
import Layout from '../components/Layout'
import ProductForm from '../components/ProductForm'
import { getProductById } from '../services/ProductService';

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

    render() {
        const { product, errorMessage } = this.state;
        return (
            <Layout error={errorMessage}>
                <ProductForm product={product} handleChange={this.handleChange}/>
            </Layout>
        );
    }
}

export default withRouter(ProductPage)