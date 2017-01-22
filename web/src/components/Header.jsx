import React from 'react'
import { isAuthenticated, getAuthentication } from '../authentication'

class Header extends React.Component {
    render() {
        let username = isAuthenticated() ? getAuthentication().username : undefined;

        return (
            <header>
                <div id="location"> {location.pathname} </div>

                <div id="greeting">hello,
                    {username && <span> {username}</span>}
                    {!username && <span> anonymous</span>}
                </div>
            </header>
        )
    }
}

export default Header;