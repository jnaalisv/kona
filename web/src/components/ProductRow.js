import React from 'react'

class ProductRow extends React.Component {

    render() {
        const product = this.props.product;
        return (
            <tr>
                <td>{product.productCode}</td>
                <td>{product.name}</td>
            </tr>
        )
    }
}

ProductRow.propTypes = {
    product: React.PropTypes.object.isRequired
};

export default ProductRow;