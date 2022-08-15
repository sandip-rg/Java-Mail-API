package com.email;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "preaparing to sending email...." );
        String message = "Hello Dear, This is message for security check. ";
        String subject = "CodersArea: Confirmation";
        String to = "xyz@gmail.com";
        String from = "abc@gmail.com";
        
        sendEmail(message, subject, to, from);
    }

    //this is responsible to send email..
	private static void sendEmail(String message, String subject, String to, String from) {
		//variable for gmail
		String host = "smtp.gmail.com";
		
		//get the system properties
		Properties properties = System.getProperties();
		System.out.println("properties: " + properties);
		
		//setting important information to properties object
		
		//host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		//step 1: to get the session object
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				//your email and password
				return new PasswordAuthentication("abc@gmail.com", "abc");
			}
			
		});
		
		session.setDebug(true);
		
		//step 2: compose the message [text, multi media]
		MimeMessage m = new MimeMessage(session);
		try {

			//from email
			m.setFrom(from);
			
			//adding recipient to message
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			//adding subject to message
			m.setSubject(subject);
			
			//adding text to message
			m.setText(message);
			
			//step 3: send the message using Transport class
			Transport.send(m);
			System.out.println("sent successfully....");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
