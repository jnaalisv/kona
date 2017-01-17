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
            console.log('response ', response);
        }).catch(error => {
            console.log('error ', error);
        });
}

export default authenticate;