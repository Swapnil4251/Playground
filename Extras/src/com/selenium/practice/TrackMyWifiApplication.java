package com.selenium.practice;

import java.util.List;
import java.util.function.Predicate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TrackMyWifiApplication extends SeleniumBase {

	public static void main(String[] args) throws InterruptedException {
		TrackMyWifiApplication app = new TrackMyWifiApplication();
		WebDriver driver = app.getWebDriver();
		driver.get("https://web.whatsapp.com");
		
		System.out.println("after get");
		String contactName = "Bhushan Sawarkar";
		String messageText = "Hi " + contactName + ", this message is sent from a 'ChatBot for WhatsApp' developed by Swapnil.";
		
		WebElement searchElement = app.findElementSafely(By.className("_3xpD_"));
		
		app.findElementSafely(searchElement, By.className("_3FRCZ")).sendKeys(contactName);
		
		synchronized (driver) {
			driver.wait(7000);
		}
		
		List<WebElement> searchResults = driver.findElements(By.className("_3ko75"));
		Predicate<? super WebElement> condition = ele -> ele.getAttribute("title") != null && ele.getAttribute("title").equals(contactName);
		
		if (searchResults.stream().anyMatch(condition)) {
			WebElement chatFound = searchResults.stream().filter(condition).findFirst().get();
			chatFound.click();
			
			WebElement enterText = app.findElementSafely(By.className("_3uMse"));
			
			app.findElementSafely(enterText, By.className("_3FRCZ")).sendKeys(messageText);
			
			app.findElementSafely(By.className("_1U1xa")).click();
			
			System.out.println("Message sent to " + contactName);
		} else {
			System.out.println("No chats found for contact name: " + contactName);
		}
	}

}
