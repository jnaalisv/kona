import React from 'react'
import PropTypes from 'prop-types'
import { getCustomers, getCustomer } from '../customersHttp'
import AutoComplete from './AutoComplete'

class CustomerAutoComplete extends React.Component {
    constructor(props) {
        super(props);
        this.state = {initialQuery: undefined};
    }

    renderCustomerChoice = (customer) => <span>{customer.id} {customer.name}</span>;

    componentDidMount() {
        if (this.props.selectedId) {
            getCustomer(this.props.selectedId)
                .then(customer => {
                    this.setState({initialQuery: customer.name});
                });
        }
    }

    render() {
        if (this.state.initialQuery) {
            return (
                <span>
                    Orderer:
                    <AutoComplete
                        renderResult={this.renderCustomerChoice}
                        searchCallback={getCustomers}
                        selectCallback={this.props.selectCallback}
                        initialQuery={this.state.initialQuery}/>
                </span>
            )
        }
        return (<span>loading</span>)
    }
}

CustomerAutoComplete.propTypes = {
    selectCallback: PropTypes.func.isRequired,
    selectedId: PropTypes.number
};

export default CustomerAutoComplete;
