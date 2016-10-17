import React from 'react'
import LoginForm from './LoginForm';

class App extends React.Component {
    constructor() {
        super();

        this.login = this.login.bind(this);

        this.state = {
            username: "",
            password: ""
        }
    }

    login(username, password) {
        console.log(`login attempt: ${username}/${password}`);

        this.setState({
            'username': username,
            'password': password
        });
    }

    render() {
        return (
            <div>
                <p>Hello {this.state.username}/{this.state.password}</p>
                <LoginForm login={this.login}/>
            </div>
        )
    }
}

export default App;