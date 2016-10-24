import React from 'react';
import { render } from 'react-dom';
import { BrowserRouter, Match, Miss, Link } from 'react-router'
import LocationHeader from './components/LocationHeader'

import Products from './components/Products'

const Home = () => (
    <div>
        <h2>Home</h2>
    </div>
);

const About = () => (
    <div>
        <h2>About</h2>
    </div>
);

const NoMatch = () => (
    <div>
        <h2>Whoops</h2>
        <p>Sorry but {location.pathname} didn’t match any pages</p>
    </div>
);

const Topic = (props) => (
    <div>
        <h3>{props.params.topicId}</h3>
        <p>location.pathname: {location.pathname}</p>
        <p>params.topicId: {props.params.topicId}</p>
        <p>pathname: {props.pathname}</p>
        <p>pattern: {props.pattern}</p>
    </div>
);

const Topics = ({ pathname, pattern }) => (
    // 5. Components rendered by a `Match` get some routing-specific
    //    props, like the portion of the parent `pattern` that was
    //    matched against the current `location.pathname`, in this case
    //    `/topics`
    <div>
        <h2>Topics</h2>
        <ul>
            {/* 6. Use the parent's matched pathname to link relatively */}
            <li><Link to={`${pathname}/rendering`}>Rendering with React</Link></li>
            <li><Link to={`${pathname}/components`}>Components</Link></li>
            <li><Link to={`${pathname}/props-v-state`}>Props v. State</Link></li>
        </ul>

        {/* 7. Render more `Match` components to get nesting naturally
         within the render lifecycle. Use the parent's matched
         pathname to nest the url.
         */}
        <Match pattern={`${pathname}/:topicId`} component={Topic}/>

        {/* 8. use the `render` prop for convenient inline rendering */}
        <Match pattern={pathname} exactly render={() => (
            <h3>Please select a topic</h3>
        )}/>
    </div>
);

const App = () => (
    // 2. render a `Router`, it will listen to the url changes
    //    and make the location available to other components
    //    automatically
    <BrowserRouter>
        <div>
            <ul>
                {/* 3. Link to some paths with `Link` */}
                <li><Link to="/">Home</Link></li>
                <li><Link to="/about">About</Link></li>
                <li><Link to="/topics">Topics</Link></li>
                <li><Link to="/products">Products</Link></li>
            </ul>

            <LocationHeader />

            <hr/>

            <Match exactly pattern="/" component={Home} />
            <Match pattern="/about" component={About} />
            <Match pattern="/topics" component={Topics} />
            <Match pattern="/products" component={Products} />
            <Miss component={NoMatch}/>
        </div>
    </BrowserRouter>
);

render(<App/>, document.querySelector('#main'));