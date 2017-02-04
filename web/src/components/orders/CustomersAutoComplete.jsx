import React from 'react'
import {getCustomers} from '../../customers'

class CustomersAutoComplete extends React.Component {
    constructor() {
        super();

        this.state = {query: '', results:[]};

        this.onChange = this.onChange.bind(this);
    }

    onChange(event) {
        event.preventDefault();
        const newQuery = event.target.value;
        this.setState({query: newQuery, results: []});

        if (newQuery && newQuery.length > 1) {
            getCustomers(newQuery).then(response => this.setState({results: response}));
        }
    }

    render() {
        return (
            <span>
                <input value={this.state.query} onChange={this.onChange}/>

                <ul class="autocomplete">
                    {
                        Object
                            .keys(this.state.results)
                            .map(index => <li key={index}>{this.state.results[index].id}: {this.state.results[index].name}</li>)
                    }
                </ul>
            </span>
        )
    }
}

export default CustomersAutoComplete;
