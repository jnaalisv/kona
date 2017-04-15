import React from 'react'
import PropTypes from 'prop-types'
import productsHttp from '../productsHttp'
import AutoComplete from './AutoComplete'

class ProductAutoComplete extends React.Component {

    renderProductChoice(product) {
        return (
            <span>{product.productCode} {product.name}</span>
        )
    }

    render() {
        return (
            <AutoComplete  renderResult={this.renderProductChoice} searchCallback={productsHttp.getProducts} selectCallback={this.props.selectCallback}/>
        )
    }
}

ProductAutoComplete.propTypes = {
    selectCallback: PropTypes.func.isRequired,
};

export default ProductAutoComplete;