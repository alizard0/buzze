package auth;

import java.util.UUID;

public class Salt {
    private String salt;
    private String email;

    public Salt(String email) {
        this.email = email;
        this.salt = UUID.randomUUID().toString();
    }

    public Salt(String salt, String email) {
        this.salt = salt;
        this.email = email;
    }

    public String getSalt() {
        return salt;
    }

    public String getEmail() {
        return email;
    }
}
