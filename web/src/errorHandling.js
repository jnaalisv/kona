import HttpError from './HttpError'

let _errorHandler = undefined;

function registerErrorHandler(errorHandler) {
    _errorHandler = errorHandler;
}

function handleError(error) {
    if(_errorHandler) {
        _errorHandler(error);
    } else {

        if (error instanceof HttpError) {
            error.response.json().then((message) => {
                console.log('-- DEFAULT HTTP ERROR HANDLER --');
                console.log(`${error.response.status} ${error.response.statusText}`);
                console.log(`${message}`);
            });
        } else {
            console.log('-- DEFAULT ERROR HANDLER --');
            console.log(`${error}`);
        }
    }
}

const api = {
    handleError: handleError,
    registerErrorHandler: registerErrorHandler
};

export default api;
