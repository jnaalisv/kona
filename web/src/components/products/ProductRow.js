import React from 'react'
import { Link } from 'react-router'

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
            </tr>
        )
    }
}

ProductRow.propTypes = {
    product: React.PropTypes.object.isRequired
};

export default ProductRow;
