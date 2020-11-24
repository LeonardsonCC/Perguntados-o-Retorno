package utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.spec.KeySpec;
import java.util.Base64;

public class PasswordHasher {

    private static byte[] salt = "UM PREFIXO INDECIFRAVEL: ".getBytes();

    public static String hash(String password) {
        try {
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            Base64.Encoder enc = Base64.getEncoder();
            password = enc.encodeToString(hash);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }

}
