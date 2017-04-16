import React from 'react'
import PropTypes from 'prop-types'

class OrderLinesTable extends React.Component {

    render() {
        const orderLines = this.props.orderLines;

        return (
            <table>
                <tbody>
                <tr>
                    <th>productCode</th>
                    <th>amount</th>
                </tr>
                {Object.keys(orderLines)
                    .map(index => {
                        return (
                            <tr key={index}>
                                <td><input name="productCode" value={orderLines[index].productCode} onChange={(e) => this.props.onOrderLineChange(e, index)}/></td>
                                <td><input name="amount" type="number" value={orderLines[index].amount} onChange={(e) => this.props.onOrderLineChange(e, index)}/></td>
                            </tr>
                        )
                    })
                }
                </tbody>
            </table>
        );
    }
}

OrderLinesTable.propTypes = {
    orderLines: PropTypes.array.isRequired,
    onOrderLineChange: PropTypes.func.isRequired
};


export default OrderLinesTable;
