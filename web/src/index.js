import React from 'react';
import { render } from 'react-dom';
import { BrowserRouter, Match, Miss } from 'react-router';
import NotFound from './components/NotFound';
import LoginPage from './components/LoginPage';

const Root = () => {
    return (
        <BrowserRouter>
            <div>
                <Match exactly pattern="/login" component={LoginPage} />
                <Miss component={NotFound} />
            </div>
        </BrowserRouter>
    )
};

render(<Root/>, document.querySelector('#main'));