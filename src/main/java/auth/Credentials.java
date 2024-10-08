package auth;

import java.security.NoSuchAlgorithmException;

import static util.AuthUtil.hash;

public class Credentials {
    private String email;
    private String hashedPassword;

    public Credentials(String email) {
        this.email = email;
    }

    public Credentials(final String email, final String password) throws NoSuchAlgorithmException {
        this.email = email;
        this.hashedPassword = hash(password);
    }

    public String getEmail() {
        return email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
}
