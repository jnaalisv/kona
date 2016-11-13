var _errorHandler = undefined;

function registerErrorHandler(errorHandler) {
    _errorHandler = errorHandler;
}

function handleError(error) {
    if(_errorHandler) {
        _errorHandler(error);
    } else {
        error.response.json().then((body) => {
            console.log('status ' + error.response.status);
            console.log('error msg' + body);
        });
    }
}

const api = {
    handleError: handleError,
    registerErrorHandler: registerErrorHandler
};

export default api;
