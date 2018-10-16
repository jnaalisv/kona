const errorStyle = {
    backgroundColor: 'red',
    color: 'white'
};

const LastError = ({error}) => (
    <p style={errorStyle}>{error}</p>
);

export default LastError;