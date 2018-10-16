import React from 'react'

const CurrencyAmount = ({currencyAmount}) => (<p>price: {currencyAmount.amount} {currencyAmount.currency}</p>);

class ProductDetails extends React.Component {

    constructor(props) {
        super(props);
        this.state = { product: {} };
        this.onChange = this.onChange.bind(this);
    }

    onChange(event) {
        event.preventDefault();
        const product = {...this.state.product};
        product[event.target.name] = event.target.value;
        this.setState({ product });
    }
    
    componentDidMount() {
        const product = this.props.product;
        this.setState({ product });
    }

    render() {
        const { product } = this.state;
        return (
            <div>
                <h4>Product</h4>

                <p>name: <input name='name' value={product.name} onChange={this.onChange}/></p>
                <p>productCode: <input name='productCode' value={product.productCode} onChange={this.onChange}/></p>
                <p>productType: {product.productType}</p>
                {product.price && <CurrencyAmount currencyAmount={product.price} />}

                <p>id: {product.id}</p>
                <p>version: {product.version}</p>
                <p>createTime: {product.createTime}</p>
                <button>Save</button>
            </div>
        )
    }
}

export default ProductDetails;