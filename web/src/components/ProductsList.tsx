import React from 'react'
import {Product} from "./Product";
import {Link, Route, Switch, useParams, useRouteMatch} from "react-router-dom";
import ProductEdit from "./ProductEdit";

export interface ProductsListProps {
    products: Product[]
}

const ProductsList = ({ products }: ProductsListProps) => {
    let match = useRouteMatch();
    return (
        <div>

            <Switch>
                <Route path={`${match.path}/:productId`}>
                    <ProductView />
                </Route>
                <Route path={match.path}>
                    <h3>Please select a product.</h3>
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
                                .keys(products)
                                .map((value, index, array) => {
                                    return <ProductRow key={index} product={products[index]} />;
                                })
                        }
                        </tbody>
                    </table>
                </Route>
            </Switch>


        </div>
    )
};

export default ProductsList;

const ProductView = () => {
    let { productId } = useParams();
    const productIdNumber: number = (productId || 0) as number;

    return (<ProductEdit productId={productIdNumber} />);
};

export interface ProductRowProps {
    product: Product
}

const ProductRow = ({ product}: ProductRowProps) => {
    let match = useRouteMatch();
    return (
        <tr>
            <td>{product.id}</td>
            <td>
                <Link to={`${match.url}/${product.id}`}>
                    {product.name}
                </Link>
            </td>
            <td>{product.code}</td>
        </tr>
    )
};
