import fetch from 'isomorphic-unfetch'

export const httpGET = url => doHttp('GET', url);
export const httpPOST = (url, body) => doHttp('POST', url, body);
export const httpPUT = (url, body) => doHttp('PUT', url, body);

const doHttp = (method, url, body) => {
    const options = {...defaultOptions};
    options.method = method;

    if (body) {
        options.body = JSON.stringify(body);
    }

    return fetch(url, options).then(handleResponse)
};

const handleResponse = response => {
    if (response.status < 300) {

        const contentTypeHeader = response.headers.get('content-type');

        if (contentTypeHeader && contentTypeHeader.toLowerCase().startsWith('application/json')) {
            return response.json();
        }
        return response.text();
    }
    return Promise.reject(new Error(response));
};

const defaultOptions = {
    // mode: 'cors',
    headers: {
        'Content-Type': 'application/json;charset=UTF-8',
//        'Accept': 'application/json;charset=UTF-8',
    }
};