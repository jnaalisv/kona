import Layout from '../../components/Layout'
import ProductListing from '../../components/ProductListing'
import { getAllProducts, getProductById } from '../../services/ProductService';
import ProductDetails from "../../components/ProductDetails";

const ProductsPage = ({ product, products, errorMessage }) => (
    <Layout error={errorMessage}>
        {product && <ProductDetails product={product}/>}
        {products && <ProductListing products={products}/>}
    </Layout>
);

ProductsPage.getInitialProps = async ({query}) => {
    if (query && query.id) {
        return await getProductById(query.id);
    }

    return await getAllProducts();
};

export default ProductsPage