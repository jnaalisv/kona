import React from 'react'

const ErrorBox = ({errors}) => (
    <div className="error-container">
        {Object.keys(errors).map(key => <div key={key} id="errorbox">{errors[key]}</div>)}
    </div>
);

export default ErrorBox;

// TODO: propTypes errors [] required