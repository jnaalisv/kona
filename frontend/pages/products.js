import Layout from '../components/Layout'
import ProductListing from '../components/ProductListing'
import { getAllProducts } from '../services/ProductService';

const ProductListingPage = ({ products, errorMessage }) => (
    <Layout error={errorMessage}>
        <ProductListing products={products} />
    </Layout>
);

ProductListingPage.getInitialProps = async ({ req }) => {
    return await getAllProducts();
};

export default ProductListingPage