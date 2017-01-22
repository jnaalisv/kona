import React from 'react'

import CustomerRow from './CustomerRow'
import {getCustomers, deleteCustomer} from '../../customers'
import Notifications from '../Notifications'
import HttpError from '../../HttpError'

class CustomerTable extends React.Component {
    constructor() {
        super();
        this.state = {customers: [], notifications:[]};

        this.addError = this.addError.bind(this);
        this.addInfo = this.addInfo.bind(this);
        this.search = this.search.bind(this);
    };

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

    addInfo(infoMessage) {
        const notifications = [...this.state.notifications];
        notifications.push({type:'info', message:infoMessage});
        this.setState({ notifications });
    }

    componentDidMount() {
        getCustomers()
            .then(
                customers => this.setState({customers}),
                this.addError
            );
    }

    search(event) {
        getCustomers(event.target.value)
            .then(
                customers => this.setState({customers}),
                this.addError
            );
    }

    deleteCustomer(index) {
        deleteCustomer(this.state.customers[index].id)
            .then(
                () => {
                    const customers = [...this.state.customers];
                    customers.splice(index, 1);
                    this.setState({customers});
                    this.addInfo('Deleted');
                },
                this.addError
            );
    }

    render() {
        return (
            <div>
                <table className="productTable">
                    <thead>
                    <tr>
                        <th id="id">id</th>
                        <th id="name">name</th>
                        <th>version</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                            </td>
                            <td><input onChange={this.search}/></td>
                            <td></td>
                        </tr>

                        {Object
                            .keys(this.state.customers)
                            .map(index => {
                                return <CustomerRow key={index} customer={this.state.customers[index]} deleteCustomer={() => this.deleteCustomer(index)}/>
                            })
                        }
                    </tbody>
                </table>
                <Notifications notifications={this.state.notifications} />
            </div>

        )
    }
}

export default CustomerTable;
