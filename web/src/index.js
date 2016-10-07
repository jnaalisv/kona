import React from 'react';
import { render } from 'react-dom';
import { BrowserRouter, Match, Miss } from 'react-router';
import NotFound from './components/NotFound';
import LoginPage from './components/LoginPage';
import ProductsPage from './components/ProductsPage'

const Root = () => {
    return (
        <BrowserRouter>
            <div>
                <Match exactly pattern="/login" component={LoginPage} />
                <Match exactly pattern="/products" component={ProductsPage} />
                <Miss component={NotFound} />
            </div>
        </BrowserRouter>
    )
};

render(<Root/>, document.querySelector('#main'));