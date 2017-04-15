import React from 'react'

import ProductRow from './ProductRow'
import { getProducts, deleteProduct } from '../../productsHttp'
import Notifications from '../Notifications'
import HttpError from '../../HttpError'

class ProductTable extends React.Component {
    constructor() {
        super();
        this.state = {products: [], notifications:[]};

        this.addError = this.addError.bind(this);
        this.addInfo = this.addInfo.bind(this);
        this.search = this.search.bind(this);
    };

    addError(error) {
        let errorMessage;

        if (error instanceof TypeError) {
            errorMessage = error.message;
        } else if (error instanceof HttpError) {
            const response = error.response;
            errorMessage = `${response.status} ${response.statusText}`;
        }

        const notifications = [...this.state.notifications];
        notifications.push({type:'error', message: errorMessage});
        this.setState({ notifications });
    }

    addInfo(infoMessage) {
        const notifications = [...this.state.notifications];
        notifications.push({type:'info', message:infoMessage});
        this.setState({ notifications });
    }

    componentDidMount() {
        getProducts()
            .then(
                products => this.setState({products}),
                this.addError
            );
    }

    search(event) {
        getProducts(event.target.value)
            .then(
                products => this.setState({products}),
                this.addError
            );
    }

    deleteProduct(index) {
        deleteProduct(this.state.products[index].id)
            .then(
                () => {
                    const products = [...this.state.products];
                    products.splice(index, 1);
                    this.setState({products});
                    this.addInfo('Deleted');
                },
                this.addError
            );
    }

    render() {
        return (
            <div>
                <table className="productTable">
                    <thead>
                    <tr>
                        <th id="code">code</th>
                        <th id="name">name</th>
                        <th>version</th>
                        <th></th>
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
                                return <ProductRow key={index} product={this.state.products[index]} deleteProduct={() => this.deleteProduct(index)}/>
                            })
                        }
                    </tbody>
                </table>
                <Notifications notifications={this.state.notifications} />
            </div>

        )
    }
}

export default ProductTable;
