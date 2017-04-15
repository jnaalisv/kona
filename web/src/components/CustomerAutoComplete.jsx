import React from 'react'
import PropTypes from 'prop-types'
import { getCustomers } from '../customersHttp'
import AutoComplete from './AutoComplete'

class CustomerAutoComplete extends React.Component {

    renderCustomerChoice(customer) {
        return (
            <span>{customer.id} {customer.name}</span>
        )
    }

    render() {
        return (
            <AutoComplete  renderResult={this.renderCustomerChoice} searchCallback={getCustomers} selectCallback={this.props.selectCallback}/>
        )
    }
}

CustomerAutoComplete.propTypes = {
    selectCallback: PropTypes.func.isRequired,
};

export default CustomerAutoComplete;