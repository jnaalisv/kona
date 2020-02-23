import HttpError from './HttpError'

const handleResponse = (response: Response): Promise<any> => {
    if (response.status < 300) {

        const contentTypeHeader = response.headers.get('content-type');

        if (contentTypeHeader && contentTypeHeader.toLowerCase().startsWith('application/json')) {
            const responseJson: Promise<any> = response.json();
            return responseJson;
        }
        //return response.text();
    }
    return Promise.reject(new HttpError(response));
};

const doHttp = <T> (method: string, url: string, body: object): Promise<T> => {

    const requestInit: RequestInit = {
        method,
        mode: "cors",
        headers: {
            'Access-Control-Allow-Origin': '*',
            'Content-Type': 'application/json;charset=UTF-8',
            'Accept': 'application/json;charset=UTF-8',
        }
    };

    /*
    if (body) {
        requestInit.body = JSON.stringify(body);
    }*/

    return fetch(url, requestInit).then(handleResponse)
};

export const httpGET = <T>(url: string):Promise<T> => doHttp('GET', url, {});

export const httpPOST = (url: string, body: object) => doHttp('POST', url, body);
export const httpPUT = (url: string, body: object) => doHttp('PUT', url, body);
export const httpDELETE = (url: string) => doHttp('DELETE', url, {});
