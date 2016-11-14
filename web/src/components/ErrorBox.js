import React from 'react'
import errorHandling from './errorHandling'
import HttpError from './HttpError'

class ErrorBox extends React.Component {
    constructor() {
        super();
        this.state = {errorMessage: undefined};

        this.showError = this.showError.bind(this);

        errorHandling.registerErrorHandler(this.showError);
    }

    showError(error) {

        if (error instanceof HttpError) {
            error.getDetailedMessage()
                .then((message) => {
                    this.setState({errorMessage: `${error.getStatusText()} ${error.getMessage()} ${message}`})
                });
        } else {
            this.setState({errorMessage: error.message})
        }
    }

    render() {
        return (
            <div id="errorbox">{this.state.errorMessage}</div>
        )
    }
}

export default ErrorBox;