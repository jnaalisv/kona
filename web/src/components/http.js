import HttpError from './HttpError'
import {getToken} from '../auth/tokenStore'

const api = {
    GET: httpGet,
    POST: httpPost,
    PUT: httpPut,
    DELETE: httpDelete,
};

const GEToptions = {
    method: 'GET',
    mode: 'cors',
    headers: {
        'Accept': 'application/json'
    }
};

const POSToptions = {
    method: 'POST',
    mode: 'cors',
    headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
    },
};

const PUToptions = {
    method: 'PUT',
    mode: 'cors',
    headers: {
        'Content-Type': 'application/json;charset=UTF-8',
        'Accept': 'application/json;charset=UTF-8',
    },
};

const DELETEoptions = {
    method: 'DELETE',
    mode: 'cors',
};

const parseJSON = response => response.json();

function checkStatus(response) {
    if (response.status >= 200 && response.status < 300) {
        return response;
    } else {
        throw new HttpError(response);
    }
}

function _fetch(url, options) {
    if(options.queryParams) {
        url += (url.indexOf('?') === -1 ? '?' : '&') + joinQueryParams(options.queryParams);
        delete options.queryParams;
    }

    return fetch(url, options);
}

function joinQueryParams(params) {
    return Object.keys(params)
        .map(key => encodeURIComponent(key) + '=' + encodeURIComponent(params[key]))
        .join('&');
}

function httpGet(url, options={}) {

    options = {...GEToptions, ...options};
    options.headers.Authorization = getToken();

    return _fetch(url, options)
        .then(checkStatus)
        .then(parseJSON);
}

function httpPost(url, body) {

    const options = {...POSToptions};

    options.headers.Authorization = getToken();
    options.body = JSON.stringify(body);

    return fetch(url, options)
        .then(checkStatus)
        .then(parseJSON);
}

function httpPut(url, body) {

    const options = {...PUToptions};

    options.headers.Authorization = getToken();
    options.body = JSON.stringify(body);

    return fetch(url, options)
        .then(checkStatus)
        .then(parseJSON);
}

function httpDelete(url) {
    const options = {
        ...DELETEoptions,
        headers: {
            Authorization: getToken()
        }
    };

    return fetch(url, options)
        .then(checkStatus)
        .then(response => response.status);
}

export default api;