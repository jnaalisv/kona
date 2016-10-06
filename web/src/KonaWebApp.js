import React, { Component } from 'react';

class KonaWebApp extends Component {

    login(event) {
        event.preventDefault();
        console.log('this.username='+ this.username.value);
        console.log('this.password='+ this.password.value);
        if (this.username.value && this.password.value) {

        }
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
