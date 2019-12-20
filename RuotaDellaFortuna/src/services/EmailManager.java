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


    public static EmailManager createEmailManager(String email, String password) {
        if (emailManager == null) {
            emailManager = new EmailManager(email, password);
            return emailManager;
        } else
            return emailManager;
    }


    public void sendEmail(String to, String sub, String txt) {
        try {
            EmailSender.sendUninsubriaEmail(email, password, to, sub, txt);
        } catch (MessagingException e) {
            throw new EmailAddressDoesNotExistException();
        }
    }


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
