import fetch from 'isomorphic-unfetch'
import Link from 'next/link'
import Layout from '../components/Layout'

const Page = ({ products, errorMessage }) => {
    return (
        <Layout>
            <h4>Product listing</h4>
            {errorMessage && <p>{errorMessage}</p>}

            <ul>
                {products.map(product => (
                    <li key={product.id}>
                        <Link as={`/products/${product.id}`} href={`/products/${product.id}`}>
                            <a>{product.name}</a>
                        </Link>
                    </li>
                ))}
            </ul>
        </Layout>
    );
};

Page.getInitialProps = async ({ req }) => {
    console.log('request ', req);

    let response;
    const url = 'http://localhost:8080/products';
    try {
        response = await fetch(url);
    } catch (err) {
        return { products: [], errorMessage: `request to ${url} failed, is the backend down?` }
    }

    const json = await response.json();
    return { products: json }
};

export default Page