package kona.http.authentication;

public class TokenDTO {
    public String token;

    private TokenDTO(String token) {
        this.token = token;
    }

    public TokenDTO() { /* for jackson */}

    static TokenDTO from(String token) {
        return new TokenDTO(token);
    }
}
