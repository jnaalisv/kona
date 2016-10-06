import React, { Component } from 'react';

class KonaWebApp extends Component {

    login(event) {
        event.preventDefault();

        const username = this.username.value;
        const password = this.password.value;

        const authenticationUrl = 'http://localhost:9999/kona/authenticate';

        fetch(authenticationUrl, {
            method: 'POST',
            mode: 'cors',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username: username,
                password: password,
            })
        }).then(function (response) {
            console.log('response ' + response)
        }).catch(function(error) {
            console.log('There has been a problem with your fetch operation: ' + error.message);
        });
    }

    render() {
        return (
            <div>
                <form onSubmit={(e) => this.login(e)} >
                    username: <input required type="text" ref={(input) => this.username = input} placeholder="username"/>
                    password: <input required type="password" ref={(input) => this.password = input} placeholder="password"/>
                    <button type="submit">login</button>
                </form>
            </div>
        );
    }
}

export default KonaWebApp;
