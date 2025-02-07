package com.selenium.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumBase {

	private WebDriver webDriver;
	
	public SeleniumBase() {
		
	}
	
	public WebDriver getWebDriver() {
		if (webDriver == null) {
			ChromeOptions options = new ChromeOptions();
	        options.addArguments("user-data-dir=/Users/swapnilsarwade/Library/Application Support/Google/Chrome/Default/");
	        options.addArguments("--start-maximized");
			webDriver = new ChromeDriver(options);//new SafariDriver();
		}
		return webDriver;
	}
	
	public void waitForVisibility(By by) {
		WebElement element = null;
		while (element == null) {
			try {
				element = this.getWebDriver().findElement(by);
			} catch (NoSuchElementException e) {
				System.out.println("retry... waiting for : " + by);
			}
		}
	}
	
	public WebElement findElementSafely(By by) throws InterruptedException {
		return findElementSafely(getWebDriver(), by);
	}
	
	public WebElement findElementSafely(SearchContext ctx, By by) throws InterruptedException {
		WebElement element = null;
		synchronized (ctx) {
			while (true) {
				try {
					element = ctx.findElement(by);
					break;
				} catch (Exception e) {
					System.out.println("retry... waiting for 5000ms to lookup by " + by);
					ctx.wait(5000);
				}
			}
		}
		return element;
	}
}
