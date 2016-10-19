import React from 'react'

class LocationHeader extends React.Component {
    render() {
        return (
            <span>You are at {location.pathname}</span>
        )
    }
}

export default LocationHeader;