package Services;



/**
 * Classe che si occupa dell'invio delle mail automatiche.
 */
public class EmailManager {
    private static EmailManager emailManager = null;
    private String email;
    private String password;

    private EmailManager(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Ritorna, eventualmente inizializzando, un oggetto di tipo {@link EmailManager}. Se non e' stato ancora inizializzato, costruira' il singleton
     * utilizzando le credenziali di un account insubria che permettranno l'invio di mail automatiche
     *
     * @param email    l'indirizzo email dell'account insubria
     * @param password la password dell'account insubria
     * @return il singleton di tipo {@link EmailManager}
     */
    public static EmailManager createEmailManager(String email, String password) {
        if (emailManager == null) {
            emailManager = new EmailManager(email, password);
            return emailManager;
        } else
            return emailManager;
    }

    /**
     * Invia una mail
     *
     * @param to  il destinatario
     * @param sub l'oggetto della mail
     * @param txt il corpo del messaggio
     */
    public void sendEmail(String to, String sub, String txt) {
        try {
            EmailSender.sendUninsubriaEmail(email, password, to, sub, txt);
        } catch (MessagingException e) {
            throw new EmailAddressDoesNotExistException();
        }
    }

    /**
     * Questo metdodo permette all'utente di loggarsi
     *
     * @param email    la mail dell'utente
     * @param password la password dell'utente
     * @return true se l'accesso è avvenuto con successo, false altrimenti
     */
    public static boolean logIntoAccount(String email, String password) {
        try{
            String sub = "RdF: collegamento dell'account riuscito";
            String txt = "Ora e' possibile inviare email in automatico dalla piattaforma attraverso questo account.";
            EmailSender.sendUninsubriaEmail(email,password,email,sub,txt);
            return true;
        }catch(MessagingException e){
            return false;
        }
    }
}
