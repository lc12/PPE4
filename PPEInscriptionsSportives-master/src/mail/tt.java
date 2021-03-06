package mail;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.util.Properties;
public class tt {


	        private static final String SMTP_HOST_NAME = "smtp-mail.outlook.com";
	        private static final String SMTP_AUTH_USER = "m2l.java@hotmail.com";
	        private static final String SMTP_AUTH_PWD  = "IticParis";

	        public static void main(String[] args) throws Exception{
	           new tt().test();
	        }

	        public void test() throws Exception{
	            Properties props = new Properties();
	            props.put("mail.transport.protocol", "smtp");
	            props.put("mail.smtp.host", SMTP_HOST_NAME);
	            props.put("mail.smtp.port", 587);
	    	    props.setProperty("mail.smtp.starttls.enable", "true");
	            props.put("mail.smtp.auth", "true");

	            Authenticator auth = new SMTPAuthenticator();
	            Session mailSession = Session.getDefaultInstance(props, auth);
	            // uncomment for debugging infos to stdout
	            // mailSession.setDebug(true);
	            Transport transport = mailSession.getTransport();

	            MimeMessage message = new MimeMessage(mailSession);

	            Multipart multipart = new MimeMultipart("alternative");

	            BodyPart part1 = new MimeBodyPart();
	            part1.setText("Checking to see what box this mail goes in ?");

	            BodyPart part2 = new MimeBodyPart();
	            part2.setContent("Checking to see what box this mail goes in ?", "text/html");

	            multipart.addBodyPart(part1);
	            multipart.addBodyPart(part2);

	            message.setContent(multipart);
	            message.setFrom(new InternetAddress("m2l.java@hotmail.com"));
	            message.setSubject("Can you see this mail ?");
	            message.addRecipient(Message.RecipientType.TO,
	                 new InternetAddress("aurelienlazaro@gmail.com"));

	            transport.connect();
	            transport.sendMessage(message,
	                message.getRecipients(Message.RecipientType.TO));
	            transport.close();
	        }

	        private class SMTPAuthenticator extends javax.mail.Authenticator {
	            public PasswordAuthentication getPasswordAuthentication() {
	               String username = SMTP_AUTH_USER;
	               String password = SMTP_AUTH_PWD;
	               return new PasswordAuthentication(username, password);
	            }
	        }
}
