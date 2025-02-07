package com.practice.javamail;

public class SendEmail {

	public static void main(String[] args) {
		EmailService service = new EmailService();
		service.send("Test mail", "This is test mail from Swapnil\nDo not reply to this mail.", "Offersforyour@yahoo.com"); //meshram667@gmail.com
	}
}
