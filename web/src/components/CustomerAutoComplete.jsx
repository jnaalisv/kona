import React from 'react'
import PropTypes from 'prop-types'
import { getCustomers, getCustomer } from '../customersHttp'
import AutoComplete from './AutoComplete'

class CustomerAutoComplete extends React.Component {
    constructor(props) {
        super(props);
        this.state = {query: undefined};
        this.selectCallback = this.selectCallback.bind(this);
        this.searchCallback = this.searchCallback.bind(this);
    }

    renderCustomerChoice = (customer) => <span>{customer.id} {customer.name}</span>;

    componentDidMount() {
        if (this.props.selectedId) {
            getCustomer(this.props.selectedId)
                .then(customer => {
                    this.setState({query: customer.name});
                });
        }
    }

    selectCallback(customer) {
        this.props.selectCallback(customer.id);
        this.setState({query: customer.name});
    }

    searchCallback(query) {
        this.setState({query});

        if (query && query.length > 1) {
            return getCustomers(query);
        }

        this.props.selectCallback(undefined);
        return Promise.resolve([]);
    }

    render() {
        return (
            <span>
                Orderer:
                <AutoComplete
                    renderResult={this.renderCustomerChoice}
                    searchCallback={this.searchCallback}
                    selectCallback={this.selectCallback}
                    query={this.state.query}/>
            </span>
        )
    }
}

CustomerAutoComplete.propTypes = {
    selectCallback: PropTypes.func.isRequired,
    selectedId: PropTypes.number
};

export default CustomerAutoComplete;
