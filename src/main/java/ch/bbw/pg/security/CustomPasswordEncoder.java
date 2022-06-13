package ch.bbw.pg.security;

import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

@Service
public class CustomPasswordEncoder implements PasswordEncoder {


    private String MD5HASH(String string) {

        try {
            StringBuilder hashed = new StringBuilder();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(string.getBytes());
            byte[] digest = messageDigest.digest();

            for (byte d : digest) {
                String nextByte = Integer.toHexString(d & 0xFF).toUpperCase();
                if (nextByte.length() < 2) {
                    nextByte = "0" + nextByte;
                }
                hashed.append(nextByte);
            }

            return hashed.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    public String hashPassword(String password) {
        String unsalted = MD5HASH(password);
        String salt = MD5HASH(unsalted);
        String salted = MD5HASH(unsalted + salt);

        /*
         * Blowfish Crypt
         */

        return salted;
    }


    @Override
    public String encode(CharSequence charSequence) {
        return hashPassword(String.valueOf(charSequence));
    }

    @Override
    public boolean matches(CharSequence password, String hashedDatabase) {
        String hashedPassword = encode(password);
        Logger.getAnonymousLogger().info(hashedPassword);
        Logger.getAnonymousLogger().info(hashedDatabase);
        return hashedDatabase.equals(hashedPassword);
    }
}
