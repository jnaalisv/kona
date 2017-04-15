import React from 'react'
import { getOrder, saveOrder } from '../../ordersHttp'

import Notifications from '../Notifications'
import HttpError from '../../HttpError'
import CustomerAutoComplete from '../CustomerAutoComplete'

class EditOrder extends React.Component {
    constructor() {
        super();
        this.state = {order: undefined,notifications: []};

        this.addError = this.addError.bind(this);
        this.onChange = this.onChange.bind(this);
        this.saveOrder = this.saveOrder.bind(this);
        this.addLineItem = this.addLineItem.bind(this);
        this.ordererSelected = this.ordererSelected.bind(this);
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
        const orderId = this.props.match.params.orderId;
        if (orderId !== 'new') {
            getOrder(orderId)
                .then(order => this.setState({order}),
                    this.addError
                );
        } else {
            this.setState({order: {orderLines:[]}});
        }
    }

    saveOrder(e) {
        this.setState({ notifications: [] });

        saveOrder(this.state.order)
            .then(order => {
                this.setState({order});
                const notifications = [...this.state.notifications];
                notifications.push({type:'info', message:'Order saved'});
                this.setState({ notifications });
            }, this.addError);
    }

    addLineItem(e) {
        const order = {...this.state.order};
        order.orderLines.push({});
        this.setState({order});
    }

    onChange(event) {
        event.preventDefault();
        const order = {...this.state.order};
        order[event.target.name] = event.target.value;
        this.setState({order});
    }

    ordererSelected(customer) {
        const order = {...this.state.order};
        order.ordererID = customer.id;
        this.setState({order});
    }

    render () {
        const order = this.state.order;
        if (order) {
            return (
                <div>
                    <div>id: {order.id}</div>
                    <div>version: {order.version}</div>
                    <div>created: {order.createTime}</div>
                    <div>ordererID: {order.ordererID + ', '}
                        <CustomerAutoComplete selectCallback={this.ordererSelected} selectedId={order.ordererID}/>
                    </div>

                    <button onClick={(e) => this.addLineItem(e)}>Add Line Item</button>
                    <button onClick={(e) => this.saveOrder(e)}>Save</button>
                    <Notifications notifications={this.state.notifications} />
                </div>
            )
        }
        return (<div>loading...</div>)
    }
}

export default EditOrder;
