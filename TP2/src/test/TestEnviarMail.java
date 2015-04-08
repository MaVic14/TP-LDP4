package test;

import java.util.Properties;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import junit.framework.Assert;

import org.junit.Test;

import untref.ldp4.servlet.EnviarEmail;

public class TestEnviarMail {

	private static String USER_NAME = "jeeeckson@outlook.com";
	private static String PASSWORD = "";
	private static String RECIPIENT = "ssethson@baufest.com";
	private static String usuario = USER_NAME;
	private String contrasenia = PASSWORD;
	private String[] to = { RECIPIENT };
	private String asunto = "Java send mail example";
	private String body = "Welcome to JavaMail!";
	private String host = "smtp-mail.outlook.com";
	private Properties props = EnviarEmail.obtenerPropiedades(host, usuario,
			contrasenia);
	private Session session = EnviarEmail.obtenerSession(props, usuario,
			contrasenia);
	private MimeMessage message = EnviarEmail.obtenerMensaje(session, usuario,
			to, asunto, body);

	@Test
	public void testStarTLS() {

		Assert.assertEquals(props.getProperty("mail.smtp.starttls.enable"),
				"true");

	}

	@Test
	public void testHost() {
		Assert.assertEquals(props.getProperty("mail.smtp.host"), host);
	}

	@Test
	public void testUser() {
		Assert.assertEquals(props.getProperty("mail.smtp.user"), usuario);
	}

	@Test
	public void testPassword() {
		Assert.assertEquals(props.getProperty("mail.smtp.password"),
				contrasenia);
	}

	@Test
	public void testPuerto() {
		Assert.assertEquals(props.getProperty("mail.smtp.port"), "25");
	}

	@Test
	public void testAuth() {
		Assert.assertEquals(props.getProperty("mail.smtp.auth"), "true");
	}

	@Test
	public void testPuertoSocket() {
		Assert.assertEquals(props.getProperty("mail.smtp.socketFactory.port"),
				"25");
	}

	@Test
	public void testSocketFactory() {
		Assert.assertEquals(props.getProperty("mail.smtp.socketFactory.class"),
				"javax.net.ssl.SSLSocketFactory");
	}

	@Test
	public void testPropiedadesIgualesALasDeLaSession() {
		Assert.assertEquals(props, session.getProperties());
	}

	
}
