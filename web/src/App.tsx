import React from 'react';
import './App.css';
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
} from "react-router-dom";

import ProductTable from "./components/ProductTable";

const App = () => {
    return (
        <Router>
            <div>
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
                        <ProductTable />
                    </Route>
                    <Route path="/">
                        <Home />
                    </Route>
                </Switch>
            </div>
        </Router>
    );
};

export default App;

const Home = () => (<h2>Home</h2>);