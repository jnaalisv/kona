import React from 'react'
import PropTypes from 'prop-types'
import { Link } from 'react-router-dom'

class ProductRow extends React.Component {

    render() {
        const product = this.props.product;
        const productId = product.id;

        return (
            <tr>
                <td>
                    <Link to={`/products/${productId}`}>{product.productCode}</Link>
                </td>
                <td>{product.name}</td>
                <td>{product.version}</td>
                <td>{product.productType}</td>
                <td>
                    <button onClick={this.props.deleteProduct}>delete</button>
                </td>
            </tr>
        )
    }
}

ProductRow.propTypes = {
    product: PropTypes.object.isRequired,
    deleteProduct: PropTypes.func.isRequired
};

export default ProductRow;
