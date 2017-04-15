import React from 'react';
import AutoComplete from './AutoComplete'
import {getCustomers} from '../customers'

class Home extends React.Component {

    selectCustomer(customer) {
        console.log('customer selected ', customer);
    }

    renderCustomerResult(customer) {
        return (
            <span>
                {customer.id}: {customer.name}
            </span>
        )
    }

    render() {
        return (
            <div>
                <h2>Home</h2>
                <AutoComplete searchCallback={getCustomers} selectCallback={this.selectCustomer} renderResult={this.renderCustomerResult}/>
            </div>
        );
    }
}

export default Home;