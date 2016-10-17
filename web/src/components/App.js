import React from 'react'
import LoginPage from './LoginPage';

class App extends React.Component {

    login(username, password) {
        console.log(`login attempt: ${username}/${password}`);
    }

    render() {
        return (
            <div>
                <p>Hello, world</p>
                <LoginPage login={this.login}/>
            </div>
        )
    }
}

export default App;