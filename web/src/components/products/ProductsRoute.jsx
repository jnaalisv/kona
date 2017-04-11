import React from 'react'
import { Route, Link, Switch } from 'react-router-dom'

import ProductTable from './ProductTable'
import EditProduct from './EditProduct'

const ProductsRoute = ({match}) => (
    <div>
        <Link to={`${match.url}/new`}><button>create a new product</button></Link>

        <Switch>
            <Route path={`${match.url}/:productId`} component={EditProduct}/>
            <Route exact path={match.url} component={ProductTable}/>
        </Switch>
    </div>
);

export default ProductsRoute;
