import React from 'react'
import { Redirect } from 'react-router-dom'
import LoginForm from './LoginForm'
import { authenticate } from '../../authentication'
import Notifications from '../Notifications'
import HttpError from '../../HttpError'

class Login extends React.Component {
    constructor() {
        super();
        this.state = {
            redirectToReferrer: false,
            notifications:[]
        };

        this.addError = this.addError.bind(this);
    }

    addError(error) {
        let errorMessage;

        if (error instanceof HttpError) {
            const response = error.response;
            errorMessage = `Authentication failed ${response.status} ${response.statusText}`;
        } else if (error instanceof Error ) {
            errorMessage = `Authentication failed, network error`
        }

        const notifications = [...this.state.notifications];
        notifications.push({type:'error', message: errorMessage});
        this.setState({ notifications });
    }

    login = (username, password) => {
        authenticate(username, password)
            .then(() => {
                this.setState({ redirectToReferrer: true });
            }, this.addError);
    };

    render() {
        const { from } = this.props.location.state || '/';
        const { redirectToReferrer } = this.state;

        return (
            <div>
                {redirectToReferrer && <Redirect to={from || '/'}/>}
                {from && <p>You must log in to view <code>{from.pathname}</code></p>}
                <LoginForm login={this.login}/>
                <Notifications notifications={this.state.notifications} />
            </div>
        )
    }
}

export default Login;
