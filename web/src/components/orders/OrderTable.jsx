import React from 'react'

import OrderRow from './OrderRow'
import {getOrders, deleteOrder} from '../../ordersHttp'
import Notifications from '../Notifications'
import HttpError from '../../HttpError'

class OrderTable extends React.Component {
    constructor(props) {
        super(props);
        this.state = {orders: [], notifications:[]};

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
        getOrders()
            .then(
                orders => this.setState({orders}),
                this.addError
            );
    }

    search(event) {
        getOrders(event.target.value)
            .then(
                orders => this.setState({orders}),
                this.addError
            );
    }

    deleteOrder(index) {
        deleteOrder(this.state.orders[index].id)
            .then(
                () => {
                    const orders = [...this.state.orders];
                    orders.splice(index, 1);
                    this.setState({orders});
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
                            .keys(this.state.orders)
                            .map(index => {
                                return <OrderRow key={index} customer={this.state.orders[index]} deleteOrder={() => this.deleteOrder(index)}/>
                            })
                        }
                    </tbody>
                </table>
                <Notifications notifications={this.state.notifications} />
            </div>

        )
    }
}

export default OrderTable;
