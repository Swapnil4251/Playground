package com.selenium.practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumMacSupport {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new ChromeDriver();

		driver.get("file:///Users/swapnilsarwade/Downloads/inspinia-3-master/Static_Full_Version/index.html");
		
		driver.wait(10000);
		
	}

}
