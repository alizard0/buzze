package auth;

public interface Datasource {
    Token getTokenByEmail(String email);
    Token getToken(String token);
    String getHashedPassword(String email);
    Salt getSalt(String email);

    void persistCredentials(Credentials credentials);
    void persistSalt(Salt salt);
    void persistToken(Token token);
}
