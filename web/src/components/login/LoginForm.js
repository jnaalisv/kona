import React, { Component } from 'react';

class LoginForm extends Component {
    constructor() {
        super();

        this.handleChange = this.handleChange.bind(this);

        this.state = {
            username: "",
            password: ""
        }
    }

    loginHandler(event) {
        event.preventDefault();
        this.props.login(this.state.username, this.state.password)
    }

    handleChange(event) {
        this.setState({[event.target.name]: event.target.value});
    }

    render() {
        return (
            <div>
                <form onSubmit={(e) => this.loginHandler(e)} >
                    username:
                    <input
                        required
                        type="text"
                        name="username"
                        value={this.state.username}
                        placeholder="username"
                        onChange={this.handleChange}
                    />

                    password:
                    <input
                        required
                        type="password"
                        name="password"
                        value={this.state.password}
                        placeholder="password"
                        onChange={this.handleChange}
                    />

                    <button type="submit">login</button>
                </form>
            </div>
        );
    }
}

LoginForm.propTypes = {
    login: React.PropTypes.func.isRequired
};

export default LoginForm;
