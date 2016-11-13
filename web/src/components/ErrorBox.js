import React from 'react'
import errors from './errors'

class ErrorBox extends React.Component {
    constructor() {
        super();
        this.state = {errorMessage: undefined};

        this.showError = this.showError.bind(this);

        errors.registerErrorHandler(this.showError);
    }

    showError(httpError) {
        httpError.response.json().then((message) => {
            const newErrorMessage = `${httpError.response.status} ${httpError.message} ${message}`;
            this.setState({errorMessage: newErrorMessage})
        });
    }

    render() {
        return (
            <div id="errorbox">{this.state.errorMessage}</div>
        )
    }
}

export default ErrorBox;