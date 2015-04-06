package untref.ldp4.serlvet;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author LDP4
 *
 */
public class EnviarEmail {

	    private static String USER_NAME = "avase.soluciones@gmail.com";
	    private static String PASSWORD = "password";
	    private static String RECIPIENT = "vasquez.mvv@gmail.com";

	    // TODO Maru. El main falla, y esto se debe a temas de seguridad. Investigando...
	    public static void main(String[] args) {
	        String usuario = USER_NAME;
	        String contrasenia = PASSWORD;
	        String[] para = { RECIPIENT };
	        String asunto = "Java send mail example";
	        String body = "Welcome to JavaMail!";

	        enviarEmailGmail(usuario, contrasenia, para, asunto, body);
	        System.out.println("sent");
	    }

	    private static void enviarEmailGmail(final String usuario, final String contrasenia, String[] to, String asunto, String body) {
	    	
	        Properties props = System.getProperties();
	        String host = "smtp.gmail.com";
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", host);
	        props.put("mail.smtp.user", usuario);
	        props.put("mail.smtp.password", contrasenia);
	        props.put("mail.smtp.port", "587");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable","true");
	        
	        props.put("mail.smtp.socketFactory.port", "587");
	        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

	        Session session = Session.getDefaultInstance(props,
	        	    new javax.mail.Authenticator() {
	        	        protected PasswordAuthentication getPasswordAuthentication() {
	        	            return new PasswordAuthentication(usuario, contrasenia);
	        	    }
	        	});
	        
	        MimeMessage message = new MimeMessage(session);

	        try {
	            message.setFrom(new InternetAddress(usuario));
	            InternetAddress[] toAddress = new InternetAddress[to.length];

	            for( int i = 0; i < to.length; i++ ) {
	                toAddress[i] = new InternetAddress(to[i]);
	            }

	            for( int i = 0; i < toAddress.length; i++) {
	                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
	            }

	            message.setSubject(asunto);
	            message.setText(body);
	            Transport transport = session.getTransport("smtp");
	            transport.connect(host, usuario, contrasenia);
	            transport.sendMessage(message, message.getAllRecipients());
	            transport.close();
	        }
	        catch (AddressException ae) {
	            ae.printStackTrace();
	        }
	        catch (MessagingException me) {
	            me.printStackTrace();
	        }
	    }

}
