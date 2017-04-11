import React from 'react'
import {
    Route,
    Link,
    Switch
} from 'react-router-dom'
import OrderTable from './OrderTable'
import EditOrder from './EditOrder'

const OrdersRoute = ({match}) => (
    <div>
        <Link to={`${match.url}/new`}><button>create a new order</button></Link>

        <Switch>
            <Route path={`${match.url}/:orderId`} component={EditOrder}/>
            <Route exact path={match.url} component={OrderTable}/>
        </Switch>
    </div>
);

export default OrdersRoute;
