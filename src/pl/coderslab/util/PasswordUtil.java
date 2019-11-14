package pl.coderslab.util;

import pl.coderslab.util.BCrypt;

public final class PasswordUtil {
    private PasswordUtil(){}

    public static String createHash(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}