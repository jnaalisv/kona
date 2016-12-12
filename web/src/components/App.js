import React from 'react';
import { BrowserRouter, Match, Miss, Link } from 'react-router'
import LocationHeader from './LocationHeader'
import CustomersRoute from './customers/CustomersRoute'
import ProductsView from './products/ProductsView'
import OrdersView from './orders/OrdersView'

const Home = () => (
    <div>
        <h2>Home</h2>
    </div>
);

const NoMatch = () => (
    <div>
        <h2>Whoops</h2>
        <p>Sorry but {location.pathname} didn’t match any pages</p>
    </div>
);

class App extends React.Component {

    render() {
        return (
            <BrowserRouter>
                <div>

                    <header>
                        <LocationHeader />
                    </header>

                    <nav>
                        <ul>
                            <li><Link to="/">Home</Link></li>
                            <li><Link to="/orders">Orders</Link></li>
                            <li><Link to="/customers">Customers</Link></li>
                            <li><Link to="/products">Products</Link></li>
                        </ul>
                    </nav>

                    <div id="content">
                        <Match exactly pattern="/" component={Home} />
                        <Match pattern="/customers" component={CustomersRoute} />
                        <Match pattern="/products" component={ProductsView} />
                        <Match pattern="/orders" component={OrdersView}/>
                        <Miss component={NoMatch}/>
                    </div>

                    <footer>footer</footer>

                </div>
            </BrowserRouter>
        )
    }
}

export default App;