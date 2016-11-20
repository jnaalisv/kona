import React from 'react'

import ProductRow from './ProductRow'
import productService from './ProductService'
import ErrorBox from '../ErrorBox'

class ProductTable extends React.Component {
    constructor() {
        super();
        this.state = {products: [], errors:[]};

        this.showError = this.showError.bind(this);
        this.search = this.search.bind(this);
    };

    showError(error) {
        const errors = [...this.state.errors];
        errors.push(error.message);
        this.setState({errors});
    }

    componentDidMount() {
        productService.getProducts()
            .then(
                products => this.setState({products}),
                this.showError
            );
    }

    search(event) {
        productService
            .getProducts(event.target.value)
            .then(
                products => this.setState({products}),
                this.showError
            );
    }

    render() {
        return (
            <div>
                <table>
                    <thead>
                    <tr>
                        <th>code</th>
                        <th>name</th>
                        <th>version</th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                            </td>
                            <td><input onChange={this.search}/></td>
                            <td></td>
                        </tr>

                        {Object
                            .keys(this.state.products)
                            .map(index => {
                                return <ProductRow key={index} product={this.state.products[index]}/>
                            })
                        }
                    </tbody>
                </table>
                <ErrorBox errors={this.state.errors} />
            </div>

        )
    }
}

export default ProductTable;