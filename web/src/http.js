import HttpError from './HttpError'
import { getAuthentication, isAuthenticated } from './authentication'

const defaultOptions = {
    mode: 'cors',
    headers: {
        'Content-Type': 'application/json;charset=UTF-8',
        'Accept': 'application/json;charset=UTF-8',
    }
};

function handleResponse(response) {
    if (response.status < 300) {

        const contentTypeHeader = response.headers.get('content-type');

        if (contentTypeHeader && contentTypeHeader.toLowerCase().startsWith('application/json')) {
            return response.json();
        }
        return response.text();
    }
    throw new HttpError(response);
}

function joinQueryParamsToUrl(url, queryParams) {
    if(queryParams) {
        url += (url.indexOf('?') === -1 ? '?' : '&') + joinQueryParams(queryParams);
    }
    return url;
}

function joinQueryParams(params) {
    return Object.keys(params)
        .map(key => encodeURIComponent(key) + '=' + encodeURIComponent(params[key]))
        .join('&');
}

function doHttp(method, url, body, authRequired = true) {

    const options = {...defaultOptions};
    options.method = method;

    if (authRequired) {
        if (isAuthenticated()) {
            options.headers.Authorization = getAuthentication().token;
        } else {
            // TODO: global errror handling
            throw new Error(`authentication required to access ${url}`);
        }
    }

    if (body) {
        options.body = JSON.stringify(body);
    }

    return fetch(url, options).then(handleResponse)
}

const api = {
    GET: (url, queryParams) => doHttp('GET', joinQueryParamsToUrl(url, queryParams)),
    POST: (url, body, authRequired) => doHttp('POST', url, body, authRequired),
    PUT: (url, body) => doHttp('PUT', url, body),
    DELETE: (url) => doHttp('DELETE', url),
};

export default api;
