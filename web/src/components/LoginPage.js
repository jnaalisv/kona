import React, { Component } from 'react';

class LoginPage extends Component {

    loginHandler(event) {
        event.preventDefault();

        const username = this.username.value;
        const password = this.password.value;

        this.props.login(username, password)
    }

    render() {
        return (
            <div>
                <form onSubmit={(e) => this.loginHandler(e)} >
                    username: <input required type="text" ref={(input) => this.username = input} placeholder="username"/>
                    password: <input required type="password" ref={(input) => this.password = input} placeholder="password"/>
                    <button type="submit">login</button>
                </form>
            </div>
        );
    }
}

LoginPage.propTypes = {
    login: React.PropTypes.func.isRequired
};

export default LoginPage;
