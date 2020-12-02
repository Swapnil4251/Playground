package com.selenium.practice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.app.util.AppUtil;

public class WhatsAppSeleniumUtils extends SeleniumBase {
	
	private static final Logger LOG = Logger.getLogger(WhatsAppSeleniumUtils.class.getName());
	
	public void open() throws Exception {
		this.getWebDriver().get("https://web.whatsapp.com");
		waitUntilApplicationIsLoaded();
	}
	
	public void waitUntilApplicationIsLoaded() throws InterruptedException {
		findElementSafely(By.className("_3xpD_"));
	}
	
	public void sendMessage(String nameOrNumber, String messageText) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("myName", "Swapnil");
		sendMessage(nameOrNumber, messageText, params);
	}
	
	public Map sendMessageToContactsMatchingPattern(String pattern, String messageText) {
		try {
			WebElement paneSide = findElementSafely(By.id("pane-side"));
			
			List<WebElement> chats = paneSide.findElements(By.className("_210SC"));
			for (WebElement chat : chats) {
				WebElement nameElement = findElementSafely(chat, By.className("_3CneP"));
				LOG.info(nameElement.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return AppUtil.emptyMap();
	}
	
	public void sendMessage(String nameOrNumber, String messageText, Map<String, String> params) {
		try {
			WebElement searchElement = findElementSafely(By.className("_3xpD_"));
			
			findElementSafely(searchElement, By.className("_3FRCZ")).sendKeys(nameOrNumber);
			
			synchronized (getWebDriver()) {
				getWebDriver().wait(7000);
			}
			
			List<WebElement> searchResults = getWebDriver().findElements(By.className("_3ko75"));
			Predicate<? super WebElement> condition = ele -> ele.getAttribute("title") != null && ele.getAttribute("title").equals(nameOrNumber);
			
			if (searchResults.stream().anyMatch(condition)) {
				WebElement chatFound = searchResults.stream().filter(condition).findFirst().get();
				String contactName = assertAndSetValidName(nameOrNumber, chatFound.getAttribute("title"));
				chatFound.click();
				
				WebElement enterText = findElementSafely(By.className("_3uMse"));
				
				findElementSafely(enterText, By.className("_3FRCZ")).sendKeys(AppUtil.formatMessage(messageText, params));
				
				//findElementSafely(By.className("_1U1xa")).click();
				
				LOG.log(Level.INFO, "Message sent to (" + nameOrNumber + ") " + contactName);
			} else {
				LOG.log(Level.INFO, "No chats found for contact name: " + nameOrNumber);
			}
		} catch (Exception e) {
			LOG.log(Level.WARNING, "Error while sending message to " + nameOrNumber, e);
		}
	}
	
	private String assertAndSetValidName(String filterText, String nameFromPhone) {
		if (filterText.startsWith("+") || filterText.replace("+", "").matches("^[0-9]+$")) {
			return nameFromPhone;
		}
		return filterText;
	}
}
