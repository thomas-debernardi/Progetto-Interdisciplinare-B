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

	public static void sendUninsubriaEmail(String usr, String pwd, String to, String subject, String body)
			throws AddressException, MessagingException {
		String password = pwd;
		String username = usr;
		String from = username;

		String host = "smtp.office365.com";
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
	}
}
