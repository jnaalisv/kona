import React from 'react'
import {Match, Miss, Link} from 'react-router'
import OrderTable from './OrderTable'
import EditOrder from './EditOrder'

const OrdersRoute = (props) => (
    <div>
        <Link to={`${props.pathname}/new`}><button>create a new order</button></Link>

        <Match pattern={`${props.pathname}/:orderId`} render={(matchProps) => (
            <div>
                <Match exactly pattern={`${props.pathname}/new`} render={() => <EditOrder {...matchProps}/>}/>
                <Miss render={() => <EditOrder {...matchProps}/> }/>
            </div>
        )}/>

        <Match exactly pattern={props.pathname} component={OrderTable}/>
    </div>
);

export default OrdersRoute;
