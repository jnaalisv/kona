
class HttpError {
    constructor(response) {
        this.response = response;
        this.statusText = `${response.status} ${response.statusText}`
    }

    getStatusText() {
        return this.response.statusText;
    }

    getMessage() {
        return this.response.message;
    }

    getDetailedMessage() {
        return this.response.json();
    }
}

export default HttpError;