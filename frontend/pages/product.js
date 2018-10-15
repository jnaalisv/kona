import { withRouter } from 'next/router'
import Layout from '../components/Layout'
import fetch from 'isomorphic-unfetch'

const getProduct = async productId => {
    let response;
    const url = `http://localhost:8080/products/${productId}`;
    try {
        response = await fetch(url);
        if (response.status === 404) {
            return { product: {}, errorMessage: `Product Not found by id=${productId}, url was ${url}`};
        }
    } catch (err) {
        console.log('err ', err);
        return { product: {}, errorMessage: `Http Request to ${url} failed, is the backend down?` }
    }

    const product = await response.json();
    return { product }
};

const ProductPage = withRouter(({product, errorMessage, router, url}) => {
    return (
        <Layout>
            <p>Location: {router.asPath}</p>
            {errorMessage && <p>{errorMessage}</p>}
            <h4>Product</h4>
            <p>id: {product.id}</p>
            <p>name: {product.name}</p>
            <p>productCode: {product.productCode}</p>
            <p>productType: {product.productType}</p>
            <p>version: {product.version}</p>
            <p>createTime: {product.createTime}</p>
            {product.price && <p>price: {product.price.amount} {product.price.currency}</p>}
        </Layout>
    )
});

ProductPage.getInitialProps = async props => {
    return await getProduct(props.query.id);
};

export default ProductPage



