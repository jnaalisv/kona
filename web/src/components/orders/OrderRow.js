import React, {PropTypes} from 'react'
import { Link } from 'react-router'

class OrderRow extends React.Component {

    render() {
        const order = this.props.order;
        const orderId = order.id;

        return (
            <tr>
                <td>
                    <Link to={`/orders/${orderId}`}>{orderId}</Link>
                </td>
                <td>{order.version}</td>
                <td>
                    <button onClick={this.props.deleteOrder}>delete</button>
                </td>
            </tr>
        )
    }
}

OrderRow.propTypes = {
    order: PropTypes.object.isRequired,
    deleteOrder: PropTypes.func.isRequired
};

export default OrderRow;