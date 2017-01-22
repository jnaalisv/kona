import React from 'react'
import {getCustomers} from '../../customers'

class CustomersAutoComplete extends React.Component {
    constructor() {
        super();

        this.state = {query: ''};

        this.onChange = this.onChange.bind(this);
    }

    onChange(event) {
        event.preventDefault();
        const newQuery = event.target.value;
        this.setState({query: newQuery});

        getCustomers(newQuery)
            .then(response => {
                console.log(' found customers: ', response.length);
            });

    }

    render() {
        return (
            <span>
                <input value={this.state.query} onChange={this.onChange}/>
            </span>
        )
    }
}

export default CustomersAutoComplete;