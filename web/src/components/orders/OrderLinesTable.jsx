import React from 'react'

class OrderLinesTable extends React.Component {
    constructor(props) {
        super(props);

        this.onChange = this.onChange.bind(this);
    }

    onChange() {

    }

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
                                <td><input name="productCode" value={orderLines[index].productCode} onChange={(e) => this.onChange(e, index)}/></td>
                                <td><input name="amount" type="number" value={orderLines[index].amount} onChange={(e) => this.onChange(e, index)}/></td>
                            </tr>
                        )
                    })
                }
                </tbody>
            </table>
        );
    }
}

export default OrderLinesTable;
