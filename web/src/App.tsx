import React from 'react';
import './App.css';
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link,
} from "react-router-dom";

import ProductsComponent from "./components/ProductsComponent";
import Header from "./components/Header";
import Footer from "./components/Footer";

const App = () => {
    return (
        <Router>
            <div>
                <Header />

                <nav>
                    <ul>
                        <li>
                            <Link to="/">Home</Link>
                        </li>
                        <li>
                            <Link to="/products">products</Link>
                        </li>
                    </ul>
                </nav>

                <Switch>
                    <Route path="/products">
                        <ProductsComponent />
                    </Route>
                    <Route path="/">
                        <Home />
                    </Route>
                </Switch>

                <Footer />
            </div>
        </Router>
    );
};

export default App;

const Home = () => (<h2>Home</h2>);
