import React from 'react'

class LineItemsTable extends React.Component {
    constructor(props) {
        super(props);

        this.onChange = this.onChange.bind(this);
    }

    onChange() {

    }

    render() {
        const lineItems = [];

        return (
            <table>
                <tbody>
                <tr>
                    <th>name</th>
                    <th>code</th>
                    <th>amount</th>
                    <th>price</th>
                </tr>
                {Object.keys(lineItems)
                    .map(index => {
                        return (
                            <tr key={index}>
                                <td><input name="name" value={lineItems[index].name} onChange={(e) => this.onChange(e, index)}/></td>
                                <td><input name="code" value={lineItems[index].code} onChange={(e) => this.onChange(e, index)}/></td>
                                <td><input name="amount" value={lineItems[index].amount} onChange={(e) => this.onChange(e, index)}/></td>
                                <td><input name="price" value={lineItems[index].price} onChange={(e) => this.onChange(e, index)}/></td>
                            </tr>
                        )
                    })
                }
                </tbody>
            </table>
        );
    }
}

export default LineItemsTable;
