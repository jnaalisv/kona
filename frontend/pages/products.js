import fetch from 'isomorphic-unfetch'
import Link from 'next/link'
import Layout from '../components/Layout'

const ProductLink = (props) => (
    <li>
        <Link href={`/product?id=${props.id}`}>
            <a>{props.name}</a>
        </Link>
    </li>
);

const Page = ({ products, errorMessage }) => {
    return (
        <Layout>
            <h4>Product listing</h4>
            {errorMessage && <p>{errorMessage}</p>}

            <ul>
                {products.map(product => (
                    <ProductLink key={product.id} name={product.name} id={product.id}/>
                ))}
            </ul>
        </Layout>
    );
};

Page.getInitialProps = async ({ req }) => {
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