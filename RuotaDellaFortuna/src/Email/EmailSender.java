package Email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
  /**
   * Invia delle mail attraverso l'account Insubria del mittente
   *
   * @param from     la mail del mittente
   * @param password la password del mittente
   * @param to       la mail del destinatario
   * @param subject  l'oggetto della mail
   * @param body     il corpo della mail
 * @throws AddressException 
   * @throws SendFailedException
   * @throws MessagingException In caso non sia stato possibile inviare la mail
   */
  public static void sendUninsubriaEmail(String usr, String pwd, String to, String subject, String body) throws AddressException, MessagingException {
	String password = pwd;
	String username = usr;
    String host = "smtp.office365.com";
    String from = username;

    Properties props = System.getProperties();
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.port", 587);
    
    props.put("mail.smtp.ssl.trust", host);
 
    Session session = Session.getInstance(props);
    
    Message msg = new MimeMessage(session);
    msg.setFrom(new InternetAddress(from));
    msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
    msg.setSubject(subject);
    msg.setText(body);

    Transport.send(msg, from, password);
    System.out.println("\nMail was sent successfully.");   
  }
}
