package com.sayan.code.onlinevotingsystem.Security;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class SecurityTools {

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12)); // 12 = strength
    }

    public static boolean verifyPassword(String password, String storedHash) {
        return BCrypt.checkpw(password, storedHash);
    }

}
