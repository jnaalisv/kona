import React from 'react'
import { Redirect } from 'react-router'
import LoginForm from './LoginForm'
import { authenticate } from '../../auth/authentication'

class Login extends React.Component {
    constructor() {
        super();
        this.state = {
            redirectToReferrer: false
        };
    }

    login = (username, password) => {
        console.log(`login attempt: ${username}/${password}`);

        authenticate(username, password)
            .then(() => {
                this.setState({ redirectToReferrer: true });
            }, error => {
                console.log(`Authentication failed for ${username}/${password}:`, error.statusText);
            });
    };

    render() {
        const { from } = this.props.location.state || '/';
        const { redirectToReferrer } = this.state;

        return (
            <div>
                {redirectToReferrer && <Redirect to={from || '/'}/>}
                {from && <p>You must log in to view <code>{from.pathname}</code></p>}
                <LoginForm login={this.login}/>
            </div>
        )
    }
}

export default Login;