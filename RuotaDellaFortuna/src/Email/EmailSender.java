package Email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {
  /**
   * Invia delle mail attraverso l'account Insubria del mittente
   *
   * @param from     la mail del mittente
   * @param password la password del mittente
   * @param to       la mail del destinatario
   * @param subject  l'oggetto della mail
   * @param body     il corpo della mail
   * @throws SendFailedException
   * @throws MessagingException In caso non sia stato possibile inviare la mail
   */
  public static void sendUninsubriaEmail(String from, String password, String to, String subject, String body) throws SendFailedException, MessagingException {
    // host per la mail dell'universita'
    String host = "smtp.office365.com";
    // setto le proprieta' smtp
    Properties props = System.getProperties();
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.starttls.enable", "true");
    // porta per la mail di outlook 587
    props.put("mail.smtp.port", 587);
    props.put("mail.smtp.ssl.trust", host);
    // creo la sessione con le proprieta'
    Session session = Session.getInstance(props);
    // creo il messaggio
    Message msg = new MimeMessage(session);
    msg.setFrom(new InternetAddress(from));
    msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
    msg.setSubject(subject);
    msg.setText(body);
    // invio la mail
    Transport.send(msg, from, password);
  }
}
