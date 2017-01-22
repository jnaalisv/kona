import React from 'react'
import {getCustomer, saveCustomer} from './customers'
import Notifications from '../Notifications'
import HttpError from '../HttpError'
import CustomerAddressesTable from './CustomerAddressesTable'

class EditCustomer extends React.Component {
    constructor() {
        super();
        this.state = {customer: {name:'', addresses:[]},notifications: []};

        this.addError = this.addError.bind(this);
        this.onChange = this.onChange.bind(this);
        this.saveCustomer = this.saveCustomer.bind(this);
        this.addAddress = this.addAddress.bind(this);
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

    render () {
        const customer = this.state.customer;
        return (
            <div>
                <div>id: {customer.id}</div>
                <div>version: {customer.version}</div>
                <div>created: {customer.createTime}</div>

                <div>name: <input name="name" value={customer.name} onChange={this.onChange}/></div>

                <CustomerAddressesTable addresses={customer.addresses} />

                <button onClick={(e) => this.addAddress(e)}>Add Address</button>
                <button onClick={(e) => this.saveCustomer(e)}>Save</button>
                <Notifications notifications={this.state.notifications} />
            </div>
        )
    }
}

export default EditCustomer;
