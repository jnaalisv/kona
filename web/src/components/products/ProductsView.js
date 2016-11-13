import React from 'react'
import {Match} from 'react-router'

import ProductTable from './ProductTable'
import Product from './Product'

class ProductsView extends React.Component {

    render() {
        return (
            <div>
                <Match pattern={`${this.props.pathname}/:productId`} component={Product}/>
                <Match pattern={this.props.pathname} exactly component={ProductTable}/>
            </div>
        )
    }
}

export default ProductsView;