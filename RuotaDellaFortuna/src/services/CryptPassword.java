package services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptPassword {

    private static MessageDigest md;

    public static String crypt(String pwd) {
        try {
            md = MessageDigest.getInstance("SHA-512");
            byte[] passBytes = pwd.getBytes();
            md.reset();
            byte[] d = md.digest(passBytes);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < d.length; i++) {
                sb.append(Integer.toHexString(0xff & d[i]));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
