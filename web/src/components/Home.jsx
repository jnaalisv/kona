import React from 'react';
import ProductAutoComplete from './ProductAutoComplete'
import CustomerAutoComplete from './CustomerAutoComplete'

class Home extends React.Component {

    selectCustomer(customer) {
        console.log('customer selected ', customer);
    }

    selectProduct(product) {
        console.log('select product ', product);
    }

    render() {
        return (
            <div>
                <h2>Home</h2>
                <CustomerAutoComplete selectCallback={this.selectCustomer}/>
                <br />
                <ProductAutoComplete selectCallback={this.selectProduct}/>
            </div>
        );
    }
}

export default Home;