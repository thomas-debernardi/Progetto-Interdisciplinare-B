package Services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptPassword {

    private static MessageDigest md;

    public static String encrypt(String pwd) {
        try {
            md = MessageDigest.getInstance("SHA-512");
            byte[] passBytes = pwd.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < digested.length; i++) {
                sb.append(Integer.toHexString(0xff & digested[i]));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
