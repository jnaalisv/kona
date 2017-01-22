import React from 'react'
import {getOrder, saveOrder} from '../../orders'
import Notifications from '../Notifications'
import HttpError from '../../HttpError'

class EditOrder extends React.Component {
    constructor() {
        super();
        this.state = {order: {ordererID:undefined, orderLines:[]},notifications: []};

        this.addError = this.addError.bind(this);
        this.onChange = this.onChange.bind(this);
        this.saveOrder = this.saveOrder.bind(this);
        this.addLineItem = this.addLineItem.bind(this);
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
        if (this.props.params.orderId !== 'new') {
            getOrder(this.props.params.orderId)
                .then(order => this.setState({order}), this.addError);
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

    render () {
        const order = this.state.order;
        return (
            <div>
                <div>id: {order.id}</div>
                <div>version: {order.version}</div>
                <div>created: {order.createTime}</div>

                <button onClick={(e) => this.addLineItem(e)}>Add Line Item</button>
                <button onClick={(e) => this.saveOrder(e)}>Save</button>
                <Notifications notifications={this.state.notifications} />
            </div>
        )
    }
}

export default EditOrder;
