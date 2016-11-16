
class HttpError {
    constructor(response) {
        this.response = response;
        this.message = `${response.status} ${response.message}`
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