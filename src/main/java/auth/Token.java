package auth;

import java.util.UUID;

public class Token {
    private String email;
    private String token;

    public Token(String email) {
        this.email = email;
        this.token = UUID.randomUUID().toString();
    }

    public Token(String email, String token) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
