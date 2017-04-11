import React from 'react'
import productService from '../../ProductService'
import productTypeService from '../../ProductTypeService'
import Notifications from '../Notifications'
import HttpError from '../../HttpError'

class Product extends React.Component {
    constructor() {
        super();
        this.state = {product: {name:'', productCode:''},notifications: [], productTypes: []};

        this.addError = this.addError.bind(this);
        this.onChange = this.onChange.bind(this);
        this.onPriceChange = this.onPriceChange.bind(this);
    }

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

    componentDidMount() {
        productTypeService
            .getProductTypes()
            .then(productTypes => {
                this.setState({productTypes}, this.addError);
            });

        const productId = this.props.match.params.productId;

        if (productId !== 'new') {
            productService
                .getProduct(productId)
                .then(product => this.setState({product}), this.addError);
        }
    }

    saveProduct(e) {
        this.setState({ notifications: [] });

        productService
            .save(this.state.product)
            .then(product => {
                this.setState({product: product});
                const notifications = [...this.state.notifications];
                notifications.push({type:'info', message:'Product saved'});
                this.setState({ notifications });
            }, this.addError);
    }

    onChange(event) {
        event.preventDefault();
        const product = {...this.state.product};
        product[event.target.name] = event.target.value;
        this.setState({product});
    }

    onPriceChange(event) {
        event.preventDefault();
        const product = {...this.state.product};
        product.price.amount = event.target.value;
        this.setState({product});
    }

    render () {
        const product = this.state.product;
        const productTypes = this.state.productTypes;

        return (
            <div>
                <div>createTime: {product.createTime}</div>
                <div>name: <input name="name" value={product.name} onChange={this.onChange}/></div>
                <div>productCode: <input name="productCode" value={product.productCode} onChange={this.onChange}/></div>
                <div>
                    price: {product.price && <input type="number" name="price" value={product.price.amount} onChange={this.onPriceChange}/>}
                    {product.price && product.price.currency}
                </div>
                <div>version: {product.version}</div>

                <div>product type:
                    <select onChange={this.onChange} name="productType">
                        {Object.keys(productTypes).map(key => {
                            const productType = productTypes[key];
                            return <option key={key} value={productType} selected={productType === product.productType}>{productType}</option>;
                        })}
                    </select>
                </div>
                <button onClick={(e) => this.saveProduct(e)}>Save</button>
                <Notifications notifications={this.state.notifications} />
            </div>
        )
    }
}

export default Product;
