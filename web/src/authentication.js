import http from './http'

const authenticationUrl = 'http://localhost:9999/kona/authenticate';

const authenticationStore = {
    authentication: undefined
};

const authenticate = (username, password) => {
    return http
        .POST(authenticationUrl, {username, password}, false)
        .then(response => {
            authenticationStore.authentication = {
                username: username,
                token: response.token
            };
            return authenticationStore.authentication;
        });
};

const isAuthenticated = () => authenticationStore.authentication !== undefined;

const getAuthentication = () => authenticationStore.authentication;

const clearAuthentication = () => {
    authenticationStore.authentication = undefined
};

export { authenticate, isAuthenticated, getAuthentication, clearAuthentication }