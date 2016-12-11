import React, {PropTypes} from 'react'
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
