package Services;

/**
 * Classe di supporto per indicare che si sta utilizzando la piattaforma tramite PlayerRdF o AdminRdF
 */
public class AdminChecker {
    private static boolean isAdmin;

    public static boolean isIsAdmin() {
        return isAdmin;
    }

    public static void setIsAdmin(boolean isA) {
        isAdmin = isA;
    }
}
