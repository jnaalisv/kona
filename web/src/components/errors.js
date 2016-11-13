var _errorHandler = undefined;

function registerErrorHandler(errorHandler) {
    _errorHandler = errorHandler;
}

function handleHttpError(httpError) {
    if(_errorHandler) {
        _errorHandler(httpError);
    } else {
        httpError.response.json().then((message) => {
            console.log('-- DEFAULT ERROR HANDLER --');
            console.log(`${httpError.response.status} ${httpError.message}`);
            console.log(`${message}`);
        });
    }
}

const api = {
    handleHttpError: handleHttpError,
    registerErrorHandler: registerErrorHandler
};

export default api;
