import React from 'react'

class LocationHeader extends React.Component {
    render() {
        return (
            <span id="location"> {location.pathname}</span>
        )
    }
}

export default LocationHeader;