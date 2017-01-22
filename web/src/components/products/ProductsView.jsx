import React from 'react'
import {Match, Miss, Link} from 'react-router'

import ProductTable from './ProductTable'
import Product from './Product'

const ProductsView = (props) => (
    <div>
        <Link to={`${props.pathname}/new`}><button>create a new product</button></Link>

        <Match pattern={`${props.pathname}/:productId`} render={(matchProps) => (
            <div>
                <Match exactly pattern={`${props.pathname}/new`} render={() => <Product {...matchProps}/>}/>
                <Miss render={() => <Product {...matchProps}/> }/>
            </div>
        )}/>

        <Match exactly pattern={props.pathname} component={ProductTable}/>
    </div>
);

export default ProductsView;
