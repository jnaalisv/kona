import React from 'react'
import {getCustomers} from '../../customers'

class CustomersAutoComplete extends React.Component {
    constructor() {
        super();

        this.state = {query: '', results:[], selected: undefined};

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

    select(index) {
        const newSelection = {...this.state.results[index]};
        this.setState({query: newSelection.name, results: [], selected: newSelection});
    }

    render() {
        return (
            <span>
                <input value={this.state.query} onChange={this.onChange}/>

                {
                    Object
                        .keys(this.state.results)
                        .map(index =>
                            <div
                                className={index === 0 ? 'autocomplete selected' : 'autocomplete'}
                                key={index}
                                onClick={() => this.select(index)}>
                                {this.state.results[index].id}: {this.state.results[index].name}
                            </div>
                        )
                }
            </span>
        )
    }
}

export default CustomersAutoComplete;
