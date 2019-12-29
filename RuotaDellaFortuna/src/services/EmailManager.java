package services;

import javax.mail.MessagingException;



public class EmailManager {
    private static EmailManager emailManager = null;
    private String email;
    private String password;

    private EmailManager(String email, String password) {
        this.email = email;
        this.password = password;
    }


    /**
     * Questo metodo permette la creazione di un singleton EmailManager, se questo non è esistente
     * @param email mail account insubria
     * @param password password dell'account
     * @return singleton emailManager
     */
    public static EmailManager createEmailManager(String email, String password) {
        if (emailManager == null) {
            emailManager = new EmailManager(email, password);
            return emailManager;
        } else
            return emailManager;
    }


    /**
     * Questo metodo è utilizzato per l'invio delle mail
     * 
     * @param to destinatario 
     * @param sub oggetto della mail
     * @param txt testo della mail
     */
    public void sendEmail(String to, String sub, String txt) {
        try {
            EmailSender.sendUninsubriaEmail(email, password, to, sub, txt);
        } catch (MessagingException e) {
            throw new EmailAddressDoesNotExistException();
        }
    }


    /**
     * Questo metodo permette all'utente di eseguire il login
     * 
     * @param email mail dell'utente
     * @param password password dell'utente
     * @return <code>true</code> se l'utente ha inserito correttamente password e email, <code>false</code>
     */
    public static boolean logIntoAccount(String email, String password) {
        try{
            String sub = "Account connected";
            String txt = "Your account is ready to send email automatically";
            EmailSender.sendUninsubriaEmail(email,password,email,sub,txt);
            return true;
        }catch(MessagingException e){
            return false;
        }
    }
}