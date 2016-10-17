import React from 'react'
import LoginForm from './LoginForm';

class App extends React.Component {

    login(username, password) {
        console.log(`login attempt: ${username}/${password}`);
    }

    render() {
        return (
            <div>
                <p>Hello, world</p>
                <LoginForm login={this.login}/>
            </div>
        )
    }
}

export default App;