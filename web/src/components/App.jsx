import React from 'react';
import {
    BrowserRouter as Router,
    Link,
    Route,
    Switch
} from 'react-router-dom'

import Header from './Header'
import Footer from './Footer'
import CustomersRoute from './customers/CustomersRoute'
import ProductsRoute from './products/ProductsRoute'
import OrdersRoute from './orders/OrdersRoute'
import Login from './login/Login'
import AuthenticatedRoute from './AuthenticatedRoute'
import {isAuthenticated} from '../authentication'
import Home from './Home'

const NoMatch = () => (
    <div>
        <h2>Whoops</h2>
        <p>Sorry but didn’t match any pages</p>
    </div>
);

class App extends React.Component {

    render() {
        return (
            <Router>
                <div>
                    <Header />

                    <nav>
                        <ul>
                            <li><Link to="/">Home</Link></li>
                            <li><Link to="/orders">Orders</Link></li>
                            <li><Link to="/customers">Customers</Link></li>
                            <li><Link to="/products">Products</Link></li>
                            {!isAuthenticated() && <li><Link to="/login">Login</Link></li>}
                        </ul>
                    </nav>

                    <div id="content">
                        <Switch>
                            <Route exact path="/" component={Home} />
                            <Route path="/login" component={Login}/>
                            <AuthenticatedRoute path="/customers" component={CustomersRoute} />
                            <AuthenticatedRoute path="/products" component={ProductsRoute} />
                            <AuthenticatedRoute path="/orders" component={OrdersRoute}/>
                            <Route component={NoMatch}/>
                        </Switch>
                    </div>

                    <Footer/>

                </div>
            </Router>
        )
    }
}

export default App;
