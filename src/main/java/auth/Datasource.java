package auth;

public interface Datasource {
    Token getTokenByEmail(String email) throws Throwable;
    Token getToken(String token) throws Throwable;
    String getHashedPassword(String email) throws Throwable;
    Salt getSalt(String email) throws Throwable;

    void persistCredentials(Credentials credentials) throws Throwable;
    void updatePassword(final String email, final String password) throws Throwable;
    void persistSalt(Salt salt) throws Throwable;
    void updateSalt(Salt salt) throws Throwable;
    void persistToken(Token token) throws Throwable;
    void updateToken(Token token) throws Throwable;
}
