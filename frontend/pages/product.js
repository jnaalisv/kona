import Layout from '../components/Layout'
import ProductDetails from '../components/ProductDetails'
import { getProductById } from '../services/ProductService';

const ProductPage = ({product, errorMessage}) => (
    <Layout error={errorMessage}>
        <ProductDetails product={product} />
    </Layout>
);

ProductPage.getInitialProps = async props => {
    return await getProductById(props.query.id);
};

export default ProductPage



