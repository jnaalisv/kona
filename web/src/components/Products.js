import React from 'react'

class Products extends React.Component {

    getProducts() {

        const url = 'http://localhost:9999/kona/products';
        
        fetch(url, {
            method: 'GET',
            mode: 'cors',
            headers: {
                'ContentType': 'application/json'
            }
        }).then(function (response) {
            console.log('response ' + response);
            // set auth credentials,
            // redirect to somewhere
        }).catch(function(error) {
            console.log('There has been a problem with your fetch operation: ' + error.message);
            // show error message
        });
    }

    render() {

        this.getProducts();

        return (
            <p>Product list should go here</p>
        )
    }
}

export default Products;