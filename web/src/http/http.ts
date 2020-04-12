import HttpError from './HttpError'

const baseUrl = 'http://localhost:8080/';

const handleResponse = (response: Response): Promise<any> => {
    if (response.status < 300) {

        const contentTypeHeader = response.headers.get('content-type');

        if (contentTypeHeader && contentTypeHeader.toLowerCase().startsWith('application/json')) {
            const responseJson: Promise<any> = response.json();
            return responseJson;
        }
        return response.text();
    }
    return Promise.reject(new HttpError(response));
};

const doHttp = <T> (method: string, path: string, body?: object): Promise<T> => {

    const requestInit: RequestInit = {
        method,
        mode: "cors",
        headers: {
            'Access-Control-Allow-Origin': '*',
            'Content-Type': 'application/json;charset=UTF-8',
            'Accept': 'application/json;charset=UTF-8',
        }
    };


    if (body) {
        requestInit.body = JSON.stringify(body);
    }

    return fetch(baseUrl + path, requestInit).then(handleResponse)
};

export const httpGET = <T>(path: string): Promise<T> => doHttp('GET', path, undefined);
export const httpPOST = <T>(path: string, body: object): Promise<T> => doHttp('POST', path, body);
export const httpPUT = <T>(path: string, body: object): Promise<T> => doHttp('PUT', path, body);
export const httpDELETE = (path: string) => doHttp('DELETE', path, {});
