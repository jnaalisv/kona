import React from 'react';
import { BrowserRouter, Match, Miss, Link } from 'react-router'
import Header from './Header'
import CustomersRoute from './customers/CustomersRoute'
import ProductsView from './products/ProductsView'
import OrdersRoute from './orders/OrdersRoute'
import Login from './login/Login'
import MatchWhenAuthenticated from './MatchWhenAuthenticated'

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

                    <Header />

                    <nav>
                        <ul>
                            <li><Link to="/">Home</Link></li>
                            <li><Link to="/orders">Orders</Link></li>
                            <li><Link to="/customers">Customers</Link></li>
                            <li><Link to="/products">Products</Link></li>
                            <li><Link to="/login">Login</Link></li>
                        </ul>
                    </nav>

                    <div id="content">
                        <Match exactly pattern="/" component={Home} />
                        <MatchWhenAuthenticated pattern="/customers" component={CustomersRoute} />
                        <MatchWhenAuthenticated pattern="/products" component={ProductsView} />
                        <MatchWhenAuthenticated pattern="/orders" component={OrdersRoute}/>
                        <Match pattern="/login" component={Login}/>
                        <Miss component={NoMatch}/>
                    </div>

                    <footer>footer</footer>

                </div>
            </BrowserRouter>
        )
    }
}

export default App;