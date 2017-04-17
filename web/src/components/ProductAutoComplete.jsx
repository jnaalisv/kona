import React from 'react'
import PropTypes from 'prop-types'
import { getProducts, getProduct } from '../productsHttp'
import AutoComplete from './AutoComplete'

class ProductAutoComplete extends React.Component {
    constructor(props) {
        super(props);
        this.state = {query: undefined};
        this.selectCallback = this.selectCallback.bind(this);
        this.searchCallback = this.searchCallback.bind(this);
    }

    componentDidMount() {
        if (this.props.selectedId) {
            getProduct(this.props.selectedId)
                .then(product => {
                    this.setState({query: product.name});
                });
        }
    }

    selectCallback(product) {
        this.props.selectCallback(product.id);
        this.setState({query: product.name});
    }

    searchCallback(query) {
        this.setState({query});

        if (query && query.length > 1) {
            return getProducts(query);
        }

        this.props.selectCallback(undefined);
        return Promise.resolve([]);
    }

    render() {
        return (
            <span>
                Product:
                <AutoComplete
                    renderResult={(product) => <span>{product.productCode} {product.name}</span>}
                    searchCallback={this.searchCallback}
                    selectCallback={this.selectCallback}
                    query={this.state.query}/>
            </span>
        )
    }
}

ProductAutoComplete.propTypes = {
    selectCallback: PropTypes.func.isRequired,
    selectedId: PropTypes.number
};

export default ProductAutoComplete;
