package Services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Questa classe si occupa di criptare la password degli utenti registrati
 */
public class CryptPassword {

    private static MessageDigest md;

    /**
     * Questo metodo crypta la password con l'algoritmo SHA-512
     *
     * @param pwd la password da cryptare
     * @return la password cryptata, <code>null</code> se viene sollevata l'eccezione <code>NoSuchAlgorithmException</code>
     */
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
