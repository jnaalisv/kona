import Header from './Header'
import LastError from './LastError'

const layoutStyle = {
    margin: 20,
    padding: 20,
    border: '1px solid #DDD'
};

const Layout = ({error, children}) => (
    <div style={layoutStyle}>
        <Header />
        {error && <LastError error={error}/>}
        {children}
    </div>
);

export default Layout