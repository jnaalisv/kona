import React from 'react'
import {Match, Miss, Link} from 'react-router'

import CustomerTable from './CustomerTable'
import EditCustomer from './EditCustomer'

const CustomersView = (props) => (
    <div>
        <Link to={`${props.pathname}/new`}><button>create a new customer</button></Link>

        <Match pattern={`${props.pathname}/:customerId`} render={(matchProps) => (
            <div>
                <Match exactly pattern={`${props.pathname}/new`} render={() => <EditCustomer {...matchProps}/>}/>
                <Miss render={() => <EditCustomer {...matchProps}/> }/>
            </div>
        )}/>

        <Match exactly pattern={props.pathname} component={CustomerTable}/>
    </div>
);

export default CustomersView;
