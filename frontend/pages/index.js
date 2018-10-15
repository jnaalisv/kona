import Link from 'next/link'

const Index = () => (
    <div>
        <Link href="/products">
            <a>Product listing</a>
        </Link>
        <p>Hello,  World!</p>
    </div>
);

export default Index;