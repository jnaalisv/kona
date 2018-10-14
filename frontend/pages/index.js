import fetch from 'isomorphic-unfetch'
import Link from 'next/link'

const Page = ({ products }) => {
    return (
    <div>
        <ul>
            {products.map(product => (
                <li key={product.id}>
                    <Link as={`/products/${product.id}`} href={`/products/${product.id}`}>
                    <a>{product.name}</a>
                    </Link>
                </li>
            ))}
        </ul>
    </div>);
};

Page.getInitialProps = async ({ req }) => {
    const res = await fetch('http://localhost:8080/products');
    const json = await res.json();
    return { products: json }
};

export default Page