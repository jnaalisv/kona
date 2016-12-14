import React from 'react'
import {getCustomer, saveCustomer} from './customers'
import Notifications from '../Notifications'
import HttpError from '../HttpError'

class EditCustomer extends React.Component {
    constructor() {
        super();
        this.state = {customer: {name:'', addresses:[]},notifications: []};

        this.addError = this.addError.bind(this);
        this.onChange = this.onChange.bind(this);
        this.saveCustomer = this.saveCustomer.bind(this);
        this.addAddress = this.addAddress.bind(this);
        this.onAddressChange = this.onAddressChange.bind(this);
    }

    addError(error) {
        let errorMessage;

        if (error instanceof TypeError) {
            errorMessage = error.message;
        } else if (error instanceof HttpError) {
            const response = error.response;
            errorMessage = `${response.status} ${response.statusText}`;
        }

        const notifications = [...this.state.notifications];
        notifications.push({type:'error', message: errorMessage});
        this.setState({ notifications });
    }

    componentDidMount() {
        if (this.props.params.customerId !== 'new') {
            getCustomer(this.props.params.customerId)
                .then(customer => this.setState({customer}), this.addError);
        }
    }

    saveCustomer(e) {
        this.setState({ notifications: [] });

        saveCustomer(this.state.customer)
            .then(customer => {
                this.setState({customer});
                const notifications = [...this.state.notifications];
                notifications.push({type:'info', message:'Customer saved'});
                this.setState({ notifications });
            }, this.addError);
    }

    addAddress(e) {
        const customer = {...this.state.customer};
        customer.addresses.push({});
        this.setState({customer});
    }

    onChange(event) {
        event.preventDefault();
        const customer = {...this.state.customer};
        customer[event.target.name] = event.target.value;
        this.setState({customer});
    }

    onAddressChange(event, index) {
        event.preventDefault();

        const customer = {...this.state.customer};

        customer.addresses[index][event.target.name] = event.target.value;

        this.setState({customer});
    }

    render () {
        const customer = this.state.customer;
        return (
            <div>
                <div>id: {customer.id}</div>
                <div>version: {customer.version}</div>
                <div>created: {customer.createTime}</div>

                <div>name: <input name="name" value={customer.name} onChange={this.onChange}/></div>

                <table>
                    <tr>
                        <th>street</th>
                        <th>postal code</th>
                        <th>municipality</th>
                        <th>country code</th>
                    </tr>

                    {Object.keys(customer.addresses)
                        .map(index => {
                            return (
                                <tr key={index}>
                                    <td><input name="street" value={this.state.customer.addresses[index].street} onChange={(e) => this.onAddressChange(e, index)}/></td>
                                    <td><input name="postalCode" value={this.state.customer.addresses[index].postalCode} onChange={(e) => this.onAddressChange(e, index)}/></td>
                                    <td><input name="municipality" value={this.state.customer.addresses[index].municipality} onChange={(e) => this.onAddressChange(e, index)}/></td>
                                    <td><input name="countryCode" value={this.state.customer.addresses[index].countryCode} onChange={(e) => this.onAddressChange(e, index)}/></td>
                                </tr>
                            )
                        })
                    }

                </table>

                <button onClick={(e) => this.addAddress(e)}>Add Address</button>

                <button onClick={(e) => this.saveCustomer(e)}>Save</button>
                <Notifications notifications={this.state.notifications} />
            </div>
        )
    }
}

export default EditCustomer;
