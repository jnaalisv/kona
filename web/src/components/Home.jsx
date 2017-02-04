import React from 'react';
import CustomersAutoComplete from './orders/CustomersAutoComplete'

class Home extends React.Component {

    render() {
        return (
            <div>
                <h2>Home</h2>

                <CustomersAutoComplete />
            </div>
        );
    }
}

export default Home;