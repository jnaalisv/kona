import Link from 'next/link'
import Layout from '../components/Layout'
import { getAllProducts } from '../services/ProductService';

const ProductLink = props => (
    <li>
        <Link href={`/product?id=${props.id}`}>
            <a>{props.name}</a>
        </Link>
    </li>
);

const Page = ({ products, errorMessage }) => (
    <Layout error={errorMessage}>
        <h4>Product listing</h4>

        <ul>
            {products.map(product => (
                <ProductLink key={product.id} name={product.name} id={product.id}/>
            ))}
        </ul>
    </Layout>
);

Page.getInitialProps = async ({ req }) => {
    return await getAllProducts();
};

export default Page