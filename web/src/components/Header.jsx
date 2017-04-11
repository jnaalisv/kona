import React from 'react'
import { isAuthenticated, getAuthentication, clearAuthentication } from '../authentication'

class Header extends React.Component {

    onLogOut = () => {
        clearAuthentication();

        // TODO
        //this.props.router.transitionTo('/');
    };

    render() {
        let username = isAuthenticated() ? getAuthentication().username : undefined;

        return (
            <header>
                <div id="location"> {location.pathname} </div>

                <div id="greeting">hello,
                    {username && (
                        <span>
                            <span>{username}</span>
                            <button onClick={this.onLogOut}>log out</button>
                        </span>
                        )
                    }
                    {!username && <span> anonymous</span>}
                </div>
            </header>
        )
    }
}

// TODO propTypes

export default Header;