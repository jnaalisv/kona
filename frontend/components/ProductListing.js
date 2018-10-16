import Link from 'next/link'

const ProductLink = ({ id, name }) => (
    <li>
        <Link href={`/product?id=${id}`}>
            <a>{name}</a>
        </Link>
    </li>
);

const ProductListing = ({ products }) => (
    <div>
        <h4>Product listing</h4>
        <ul>
            {products.map(product => (
                <ProductLink key={product.id} name={product.name} id={product.id}/>
            ))}
        </ul>
    </div>
);

export default ProductListing;