import React from 'react'
import {
    Link,
    Route,
    Switch
} from 'react-router-dom'

import CustomerTable from './CustomerTable'
import EditCustomer from './EditCustomer'

const CustomersRoute = ({match}) => (
    <div>
        <Link to={`${match.url}/new`}>
            <button>create a new customer</button>
        </Link>

        <Switch>
            <Route path={`${match.url}/:customerId`} component={EditCustomer}/>
            <Route path={match.url} component={CustomerTable}/>
        </Switch>
    </div>
);

export default CustomersRoute;
