import { withRouter } from 'next/router'
import Layout from '../components/Layout'
import { getProductById } from '../services/ProductService';

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
    return await getProductById(props.query.id);
};

export default ProductPage



