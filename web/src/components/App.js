import React from 'react';
import { BrowserRouter, Match, Miss, Link } from 'react-router'
import LocationHeader from './LocationHeader'
import ProductsView from './products/ProductsView'

const Home = () => (
    <div>
        <h2>Home</h2>
    </div>
);

const NoMatch = () => (
    <div>
        <h2>Whoops</h2>
        <p>Sorry but {location.pathname} didn’t match any pages</p>
    </div>
);

class App extends React.Component {

    render() {
        return (
            <BrowserRouter>
                <div>

                    <header>
                        <LocationHeader />
                    </header>

                    <nav>
                        <ul>
                            <li><Link to="/">Home</Link></li>
                            <li><Link to="/products">Products</Link></li>
                        </ul>
                    </nav>

                    <div id="content">
                        <Match exactly pattern="/" component={Home} />
                        <Match pattern="/products" component={ProductsView} />
                        <Miss component={NoMatch}/>
                    </div>
                    <footer></footer>
                </div>
            </BrowserRouter>
        )
    }
}



export default App;
