import Link from 'next/link'
import { withRouter } from 'next/router'

const linkStyle = {
    marginRight: 15
};

const Header = withRouter(({router}) => (
    <div>
        <Link href="/">
            <a style={linkStyle}>Home</a>
        </Link>
        <Link href="/products">
            <a style={linkStyle}>Products</a>
        </Link>
        <div>Location: {router.asPath}</div>
    </div>
));

export default Header