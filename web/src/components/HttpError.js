
class HttpError {
    constructor(response) {
        this.response = response;
    }

    getStatusText() {
        return this.response.status;
    }

    getMessage() {
        return this.response.message;
    }

    getDetailedMessage() {
        return this.response.json();
    }

}

export default HttpError;