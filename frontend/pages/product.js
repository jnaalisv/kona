import { withRouter } from 'next/router'
import Layout from '../components/Layout'
import fetch from 'isomorphic-unfetch'

const ProductPage = withRouter(props => {

    // const product = getProduct(props.router.query.id);

    return (
        <Layout>
            <h4>Product Id {props.router.query.id}</h4>
        </Layout>
    )
});

export default ProductPage

const getProduct = async productId => {
    let response;
    const url = `'http://localhost:8080/products/${productId}`;
    try {
        response = await fetch(url);
    } catch (err) {
        return { products: [], errorMessage: `request to ${url} failed, is the backend down?` }
    }

    const json = await response.json();
    return json
};