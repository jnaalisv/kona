import React from 'react'

import { getProducts } from '../http/productsHttp'
import {Product} from "./Product";
import ProductsList from "./ProductsList";

interface ProductsComponentState {
    products: Product[]
}

class ProductsComponent extends React.Component<{}, ProductsComponentState> {
    constructor(props:{}) {
        super(props);
        this.state = {products: []};
    };

    componentDidMount() {
        getProducts().then(products => this.setState({products}));
    }

    render() {
        return (
            <ProductsList products={this.state.products}/>
        )
    }
}

export default ProductsComponent;
