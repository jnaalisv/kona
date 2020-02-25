import React from 'react'

class Header extends React.Component {
    render() {
        return (
            <header>
                <div id="location"> {'this should be location'} </div>
                <div id="greeting">hello, user</div>
            </header>
        )
    }
}

export default Header;