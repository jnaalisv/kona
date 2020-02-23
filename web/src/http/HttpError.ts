
class HttpError {
    private readonly response: Response;
    private readonly statusText: string;

    constructor(response: Response) {
        this.response = response;
        this.statusText = `${response.status} ${response.statusText}`
    }

    getStatusText() {
        return this.response.statusText;
    }

    getMessage() {
        return this.response.body;
    }

    getDetailedMessage() {
        return this.response.json();
    }
}

export default HttpError;