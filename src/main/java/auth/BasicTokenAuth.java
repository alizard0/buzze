package auth;

import java.security.NoSuchAlgorithmException;

import static util.AuthUtil.hash;

public class BasicTokenAuth {
    private Datasource ds;

    public BasicTokenAuth(final Datasource ds) {
        this.ds = ds;
    }

    public boolean login(final String email, final String password) throws NoSuchAlgorithmException {
        try {
            Salt salt = ds.getSalt(email);
            String hashedPassword = hash(password + salt.getSalt());
            return ds.getHashedPassword(email).equalsIgnoreCase(hashedPassword);
        } catch (Exception e) {
            return false;
        }
    }


    public boolean register(final String email, final String password) throws NoSuchAlgorithmException {
        try {
            Salt salt = ds.getSalt(email);
            String passwordSalted = password + salt.getSalt();
            Credentials credentials = new Credentials(email, passwordSalted);
            ds.persistCredentials(credentials);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isTokenValid(final String token) {
        try {
            return ds.getToken(token) != null;
        } catch (Exception e) {
            return false;
        }
    }

}
