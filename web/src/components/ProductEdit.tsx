import React, {ChangeEvent} from 'react'
import {Product} from "./Product";
import {getProduct} from "../http/productsHttp";

export interface ProductEditProps {
    productId: number;
}

interface ProductEditState {
    product?: Product;
}

class ProductEdit extends React.Component<ProductEditProps, ProductEditState> {
    constructor(props: ProductEditProps) {
        super(props);
        this.state = {product: undefined};

        this.onChange = this.onChange.bind(this);
    };

    componentDidMount() {
        const { productId } = this.props;

        getProduct(productId)
                .then(
                    product => this.setState({ product }),
                        err => { console.log('err ', err); });
    }

    render () {
        const { product } = this.state;

        if (!product) {
            return <div>loading data</div>;
        }

        return (
            <div>
                <div>created: {product.created}</div>
                <div>name: <input name="name" value={product.name} onChange={this.onChange}/></div>
                <div>productCode: <input name="productCode" value={product.code} onChange={this.onChange}/></div>
                <div>price: {product.price}</div>
                <div>version: {product.version}</div>
                <div>product type:{product.productType}
                </div>
                <button onClick={(e) => this.saveProduct(e)}>Save</button>
            </div>
        )
    }

    private onChange(event: React.ChangeEvent<HTMLInputElement>) {
        //console.log('onChange ', event);
        const newValue = event.target.value;

        // TODO
        /*
        event.preventDefault();
        const product = { ...this.state.product };
        product[event.target.name] = event.target.value;
        this.setState({ product });
        */
    }


    private saveProduct(event: React.MouseEvent<HTMLButtonElement>) {
        console.log('saveProduct', event);
    }
}

export default ProductEdit;