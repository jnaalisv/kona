import { httpPOST } from './http'

const authenticationUrl = 'http://localhost:9999/kona/authenticate';

const authenticationStore = {
    authentication: undefined
};

export const authenticate = (username, password) => {
    return httpPOST(authenticationUrl, {username, password}, false)
        .then(response => {
            authenticationStore.authentication = {
                username: username,
                token: response.token
            };
            return authenticationStore.authentication;
        });
};

export const isAuthenticated = () => authenticationStore.authentication !== undefined;

export const getAuthentication = () => authenticationStore.authentication;

export const clearAuthentication = () => {
    authenticationStore.authentication = undefined
};