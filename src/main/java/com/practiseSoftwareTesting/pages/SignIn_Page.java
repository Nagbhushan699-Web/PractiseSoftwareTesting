package com.practiseSoftwareTesting.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class SignIn_Page {
WebDriver driver;
public SignIn_Page(WebDriver driver) {
	this.driver=driver;
}
By title=By.tagName("h3");
By email=By.id("email");
By password=By.id("password");
By login=By.className("btnSubmit");
By errorMsg=By.xpath("//div[text()='Invalid email or password']");
public String getTitle() {
	String txt=driver.findElement(title).getText();
	return txt;
}

public String doLogin(String mail,String pass) throws InterruptedException {
	JavascriptExecutor js=(JavascriptExecutor)driver;
	js.executeScript("window.scrollBy(0,200)");
	driver.findElement(email).clear();
	driver.findElement(email).sendKeys(mail);;
	driver.findElement(password).clear();
	driver.findElement(password).sendKeys(pass);;
	driver.findElement(login).click();
	Thread.sleep(5000);
	String txt=driver.findElement(errorMsg).getText();
	return txt;
}



}
