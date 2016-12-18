import React from 'react'

class CustomerAddressesTable extends React.Component {

    constructor(props) {
        super(props);

        this.state = {addresses: []};

        this.onAddressChange = this.onAddressChange.bind(this);
    }

    componentWillReceiveProps(nextProps) {
        this.setState({addresses: nextProps.addresses});
    }

    onAddressChange(event, index) {
        event.preventDefault();

        const addresses = {...this.state.addresses};

        addresses[index][event.target.name] = event.target.value;

        this.setState({addresses});
    }

    render() {

        const addresses = this.state.addresses;

        return (
            <table>
                <tbody>
                <tr>
                    <th>street</th>
                    <th>postal code</th>
                    <th>municipality</th>
                    <th>country code</th>
                </tr>
                {Object.keys(addresses)
                    .map(index => {
                        return (
                            <tr key={index}>
                                <td><input name="street" value={addresses[index].street} onChange={(e) => this.onAddressChange(e, index)}/></td>
                                <td><input name="postalCode" value={addresses[index].postalCode} onChange={(e) => this.onAddressChange(e, index)}/></td>
                                <td><input name="municipality" value={addresses[index].municipality} onChange={(e) => this.onAddressChange(e, index)}/></td>
                                <td><input name="countryCode" value={addresses[index].countryCode} onChange={(e) => this.onAddressChange(e, index)}/></td>
                            </tr>
                        )
                    })
                }
                </tbody>
            </table>
        )
    }
}

export default CustomerAddressesTable;