
const store = {
    token: undefined
};

function getToken() {
    return store.token;
}

function storeToken(token) {
    store.token = token;
}

export {getToken, storeToken};
