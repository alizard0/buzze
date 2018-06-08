package auth;

import static util.AuthUtil.hash;

public class SimpleTokenAuth {
    private Datasource ds;

    public SimpleTokenAuth(final Datasource ds) {
        this.ds = ds;
    }

    public String login(final String email, final String password) {
        try {
            Salt salt = ds.getSalt(email);
            String hashedPassword = hash(password + salt.getSalt());
            if (ds.getHashedPassword(email).equalsIgnoreCase(hashedPassword)) {
                return ds.getTokenByEmail(email).getToken();
            } else {
                return null;
            }
        } catch (Throwable throwable) {
            return null;
        }
    }


    public boolean register(final String email, final String password) {
        try {
            Salt salt = ds.getSalt(email);
            String passwordSalted = password + salt.getSalt();
            Credentials credentials = new Credentials(email, passwordSalted);
            ds.persistCredentials(credentials);
            return true;
        } catch (Throwable throwable) {
            return false;
        }
    }

    public boolean isTokenValid(final String token) {
        try {
            return ds.getToken(token) != null;
        } catch (Throwable throwable) {
            return false;
        }
    }

}
