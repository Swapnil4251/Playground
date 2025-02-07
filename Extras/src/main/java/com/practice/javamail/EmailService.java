/**
 * 
 */
package com.practice.javamail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author swapnilsarwade
 *
 */
public class EmailService {

	public void send(String sub, String body, String... recipients) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "192.254.75.222"); // server.datatechbox.com //192.254.75.218 smtp.gmail.com
		//props.put("mail.smtp.socketFactory.port", "2525"); //465
		//props.put("mail.smtp.socketFactory.class", SSLSocketFactory.class.getName());
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.port", "2525");
		
		//addHeaders();
		
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("datatechbox4ikmO9zu70J0", "dN18s85Kcx70753XxiwT");
			}
		});
		
		try {
			InternetAddress recps[] = new InternetAddress[recipients.length];
			
			for (int i = 0; i < recipients.length; i++) {
				recps[i] = new InternetAddress(recipients[i]);
			}
			
			MimeMessage msg = new MimeMessage(session);
			msg.addRecipients(Message.RecipientType.TO, recps);
			msg.addFrom(new InternetAddress[] { new InternetAddress("info@abc.com") } );
			msg.setSubject(sub);
			msg.setText(body);
			
			Transport.send(msg);
			System.out.println("sent");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		EmailService service = new EmailService();
		service.send("Test mail1", "This is test mail from Swapnil", "swapnil.sarwade8@gmail.com"); //meshram667@gmail.com
	}
}
