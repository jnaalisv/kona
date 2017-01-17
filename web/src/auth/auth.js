import HttpError from '../components/HttpError'
import { storeToken } from './tokenStore'

const authenticationUrl = 'http://localhost:9999/kona/authenticate';

const authRequestOptions = {
    method: 'POST',
    mode: 'cors',
    headers: {
        'Content-Type': 'application/json',
        'Accept': 'text/plain'
    }
};

function authenticate(username, password) {

    const options = {
        ...authRequestOptions,
        body: JSON.stringify({username:username, password: password})
    };

    fetch(authenticationUrl, options)
        .then(response => {
            if (response.status === 200) {
                return response.text();
            }
            throw new HttpError(response);
        })
        .then(authToken => {

            storeToken(authToken);

            return authToken;
        })
        .catch(error => {
            console.error('error ', error);
        });
}

export default authenticate;