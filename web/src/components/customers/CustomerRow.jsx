import React, {PropTypes} from 'react'
import { Link } from 'react-router'

class CustomerRow extends React.Component {

    render() {
        const customer = this.props.customer;
        const customerId = customer.id;

        return (
            <tr>
                <td>
                    <Link to={`/customers/${customerId}`}>{customerId}</Link>
                </td>
                <td>{customer.name}</td>
                <td>{customer.version}</td>
                <td>
                    <button onClick={this.props.deleteCustomer}>delete</button>
                </td>
            </tr>
        )
    }
}

CustomerRow.propTypes = {
    customer: PropTypes.object.isRequired,
    deleteCustomer: PropTypes.func.isRequired
};

export default CustomerRow;
