package crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by rupalph on 10/27/19.
 */
public class Hashing {

    public static byte[] createHash(String input){
        MessageDigest m = null;
        byte hash[] = new byte[0];
        try {
            m = MessageDigest.getInstance("SHA-512");
            m.update(input.getBytes());
            hash = m.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return hash;
    }
}
