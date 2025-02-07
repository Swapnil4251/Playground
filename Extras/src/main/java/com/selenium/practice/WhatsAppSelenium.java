package com.selenium.practice;

public class WhatsAppSelenium {

	public static void main(String[] args) throws Exception {
		WhatsAppSeleniumUtils whatsapp = new WhatsAppSeleniumUtils();
		whatsapp.open();
		whatsapp.sendMessage("*", "Wish you and your family a very happy and prosperous Diwali {contactName}! ✨🪔🎊");
	}	
}
