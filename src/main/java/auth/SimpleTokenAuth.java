package auth;

import static util.AuthUtil.hash;

public class SimpleTokenAuth {
    private Datasource ds;

    public SimpleTokenAuth(final Datasource ds) {
        this.ds = ds;
    }

    public boolean login(final String email, final String password) {
        try {
            Salt salt = ds.getSalt(email);
            String hashedPassword = hash(password + salt.getSalt());
            return ds.getHashedPassword(email).equalsIgnoreCase(hashedPassword);
        } catch (Throwable throwable) {
            return false;
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
