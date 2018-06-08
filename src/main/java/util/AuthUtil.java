package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AuthUtil {

    public static String hash(final String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(password.getBytes());
        return new String(messageDigest.digest());
    }
}
