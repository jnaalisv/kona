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
            <AutoComplete renderResult={this.renderCustomerChoice} searchCallback={getCustomers} selectCallback={this.props.selectCallback} selectedValue={this.props.selectedValue}/>
        )
    }
}

CustomerAutoComplete.propTypes = {
    selectCallback: PropTypes.func.isRequired,
    selectedValue: PropTypes.string
};

export default CustomerAutoComplete;