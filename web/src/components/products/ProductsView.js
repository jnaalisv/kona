import React from 'react'
import {Match, Link} from 'react-router'

import ProductTable from './ProductTable'
import Product from './Product'

const ProductsView = (props) => (
    <div>
        <Link to={`${props.pathname}/new`}><button>create a new product</button></Link>
        <Match exactly pattern={`${props.pathname}/:productId`} component={Product}/>
        <Match exactly pattern={props.pathname} component={ProductTable}/>
    </div>
);

export default ProductsView;