import React from 'react'

import ProductRow from './ProductRow'
import { getProducts } from '../http/productsHttp'
import {Product} from "./Product";

interface ProductTableState {
    products: Product[]
}

class ProductTable extends React.Component<{}, ProductTableState> {
    constructor(props:{}) {
        super(props);
        this.state = {products: []};
    };

    componentDidMount() {
        getProducts().then(products => this.setState({products}));
    }

    render() {
        return (
            <div>
                <h3>Products</h3>
                <table className="productTable">
                    <thead>
                    <tr>
                        <th id="id">id</th>
                        <th id="name">name</th>
                        <th>code</th>
                    </tr>
                    </thead>
                    <tbody>
                        {
                            Object
                            .keys(this.state.products)
                            .map((value, index, array) => {
                                return <ProductRow key={index} product={this.state.products[index]} />;
                            })
                        }
                    </tbody>
                </table>
            </div>
        )
    }
}

export default ProductTable;
