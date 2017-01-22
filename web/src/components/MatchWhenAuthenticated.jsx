import React from 'react';
import { Match, Redirect } from 'react-router'
import { isAuthenticated } from '../authentication'

const MatchWhenAuthenticated = ({ component: Component, ...rest }) => (
    <Match {...rest} render={props => (
        isAuthenticated()
            ? <Component {...props}/>
            : <Redirect to={{pathname: '/login',state: { from: props.location }}}/>
    )}/>
);

export default MatchWhenAuthenticated;