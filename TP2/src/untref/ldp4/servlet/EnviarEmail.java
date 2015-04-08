package untref.ldp4.servlet;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnviarEmail {

	private static String USER_NAME = "jeeeckson@outlook.com";
	private static String PASSWORD = "";
	private static String RECIPIENT = "ssethson@baufest.com";

	public static void main(String[] args) {
		String usuario = USER_NAME;
		String contrasenia = PASSWORD;
		String[] para = { RECIPIENT };
		String asunto = "Java send mail example";
		String body = "Welcome to JavaMail!";
		String host = "smtp-mail.outlook.com";

		enviarEmailGmail(usuario, contrasenia, para, asunto, body, host);
		System.out.println("sent");
	}

	private static void enviarEmailGmail(final String usuario,
			final String contrasenia, String[] to, String asunto, String body,
			String host) {
		// Todas las configuraciones las definimos mediante la clase Properties
		// sumadas las que vienen del sistema
		Properties props = obtenerPropiedades(host, usuario, contrasenia);
		// Establecemos la sesi�n con el servidor del correo
		Session session = obtenerSession(props, usuario, contrasenia);
		// Creamos el mail
		MimeMessage message = obtenerMensaje(session, usuario, to, asunto, body);

		try {
			// Enviamos a traves de servidor Smtp de la session creada
			Transport transport = session.getTransport("smtp");
			// Conectamos al servidor encargado de enviar
			transport.connect(host, usuario, contrasenia);
			// Enviamos el mensaje a los destinatarios
			transport.sendMessage(message, message.getAllRecipients());
			// Cerramos la conexion
			transport.close();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public static Properties obtenerPropiedades(String host, String usuario,
			String contrasenia) {
		Properties props = System.getProperties();

		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", usuario);
		props.put("mail.smtp.password", contrasenia);
		props.put("mail.smtp.port", "25");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		props.put("mail.smtp.socketFactory.port", "25");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		return props;
	}

	public static Session obtenerSession(Properties props,
			final String usuario, final String contrasenia) {
		return Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(usuario, contrasenia);
					}
				});
	}

	public static MimeMessage obtenerMensaje(Session session, String usuario,
			String[] to, String asunto, String body) {
		MimeMessage message = new MimeMessage(session);

		try {
			// Agregamos Emisor
			message.setFrom(new InternetAddress(usuario));
			// Creamos una lista de Receptores
			for (int i = 0; i < to.length; i++) {
				// Agregamos solo To, sin CC o CCO
				message.addRecipient(Message.RecipientType.TO,
						new InternetAddress(to[i]));
			}

			message.setSubject(asunto);
			message.setText(body);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}

}