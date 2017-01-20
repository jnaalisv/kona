import HttpError from './HttpError'
import { getToken } from '../auth/tokenStore'

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

function doHttp(method, url, body) {

    const options = {...defaultOptions};
    options.method = method;
    options.headers.Authorization = getToken();

    if (body) {
        options.body = JSON.stringify(body);
    }

    return fetch(url, options).then(handleResponse)
}

const api = {
    GET: (url, queryParams) => doHttp('GET', joinQueryParamsToUrl(url, queryParams)),
    POST: (url, body) => doHttp('POST', url, body),
    PUT: (url, body) => doHttp('PUT', url, body),
    DELETE: (url) => doHttp('DELETE', url),
};

export default api;
