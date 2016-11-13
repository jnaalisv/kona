import errors from './errors'

const api = {
    GET: httpGet,
    POST: httpPost,
    PUT: httpPut,
    DELETE: httpDelete,
};

const getConfig = {
    method: 'GET',
    mode: 'cors',
    headers: {
        'Accept': 'application/json'
    }
};

const postConfig = {
    method: 'POST',
    mode: 'cors',
    headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
    },
};

const putConfig = {
    method: 'PUT',
    mode: 'cors',
    headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
    },
};

const deleteConfig = {
    method: 'DELETE',
    mode: 'cors',
};

function parseJSON(response) {
    return response.json();
}

function checkStatus(response) {
    if (response.status >= 200 && response.status < 300) {
        return response;
    } else {
        var error = new Error(response.statusText);
        error.response = response;
        throw error;
    }
}

function httpGet(url) {
    return fetch(url, getConfig)
        .then(checkStatus)
        .then(parseJSON)
        .catch(errors.handleError)
}

function httpPost(url, body) {
    return fetch(url, Object.assign({}, postConfig, { body: JSON.stringify(body)}))
        .then(checkStatus)
        .then(parseJSON)
        .catch(errors.handleError)
}

function httpPut(url, body) {
    return fetch(url, Object.assign({}, putConfig, { body: JSON.stringify(body)}))
        .then(checkStatus)
        .then(parseJSON)
        .catch(errors.handleError)
}

function httpDelete(url) {
    return fetch(url, deleteConfig)
        .then(checkStatus)
        .then((response) => response.status)
        .catch(errors.handleError)
}

export default api;