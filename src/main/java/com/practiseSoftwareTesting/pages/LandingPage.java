package com.practiseSoftwareTesting.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LandingPage {
    private WebDriver driver;

    // Constructor to initialize WebDriver
    public LandingPage(WebDriver driver) {
        this.driver = driver;
    }
 private By home=By.linkText("Home");
 private By categories=By.linkText(" Categories ");
 private By contact=By.linkText("Contact");
 private By signIn=By.linkText("Sign in");
 private By searchInput=By.id("search-query");
 private By searchResults=By.cssSelector("[data-test='product-name']");
 private By HandTools=By.xpath("//input[@data-test='category-01JK0P3T3EN8XGSP30A5VNV4C1']");
 private By sortByPrice=By.xpath("//select[@data-test=\"sort\"]");
 private By prices=By.xpath("//span[@data-test=\"product-price\"]");
 public void scrollToElement(WebElement ele,int pixel) {
	 JavascriptExecutor js=(JavascriptExecutor)driver;
	 js.executeScript("arguments[0].scrollIntoView();", ele);
	 js.executeScript("window.scrollBy(0,"+pixel+")");
 }
 public String getHomeLink() {
	 WebElement homelink=driver.findElement(home);
	 String href=homelink.getAttribute("href");
	 return href;
 }
 public String getContactLink() {
	 WebElement link=driver.findElement(contact);
	 String href=link.getAttribute("href");
	 return href;
 }
 public String getSignInLink() {
	 WebElement link=driver.findElement(signIn);
	 String href=link.getAttribute("href");
	 return href;
 }
 
    // Get Page Title
    public String getTitle() {
        return driver.getTitle();
    }
    
    public List<String> searchAndGetTheResults(String query) throws InterruptedException {
    	WebElement el=driver.findElement(searchInput);
    	scrollToElement(el,0);
    	el.sendKeys(query,Keys.ENTER);
    	Thread.sleep(Duration.ofSeconds(5));
    	List<WebElement>list=driver.findElements(searchResults);
    	List<String>resultSet=new ArrayList<String>();
    	for(WebElement elements:list) {
    		resultSet.add(elements.getText());
    	}
    	return resultSet;
    }
    
    public List<String> filterByCategory() {
    	
    	WebElement el=driver.findElement(HandTools);
    	scrollToElement(el,200);
    	el.click();
    	List<String> allResults = new ArrayList<>();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        while (true) {
            // Wait until product names are visible
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("[data-test='product-name']")));

            // Get all product elements on the current page
            List<WebElement> productElements = driver.findElements(By.cssSelector("[data-test='product-name']"));

            // Store product names in the list
            for (WebElement product : productElements) {
                allResults.add(product.getText().trim());
            }

            // Locate the "Next" button
            WebElement nextButton = null;
            try {
                nextButton = driver.findElement(By.cssSelector("[aria-label='Next']"));
            } catch (NoSuchElementException e) {
                break; // No "Next" button means we're on the last page
            }

            // Check if the "Next" button is inside a disabled <li> element
            WebElement parentLi = nextButton.findElement(By.xpath("./parent::li"));
            if (parentLi.getAttribute("class").contains("disabled")) {
                break; // Exit if the button is disabled
            }

            // Scroll into view and wait for the button to be clickable
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextButton);
            wait.until(ExpectedConditions.elementToBeClickable(nextButton));

            // Click the "Next" button
            nextButton.click();

            // Wait for new products to load
            wait.until(ExpectedConditions.refreshed(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("[data-test='product-name']"))
            ));
        }

        return allResults;
    }

public List<Double> sortByPriceHighToLow() throws InterruptedException {
	WebElement sort=driver.findElement(sortByPrice);
	Select sel=new Select(sort);
	sel.selectByVisibleText("Price (High - Low)");
	Thread.sleep(5000);
	List<WebElement>priceList=driver.findElements(prices);
	List<Double>list=new ArrayList<Double>();
	for (WebElement el : priceList) {
		double txt=Double.parseDouble(el.getText().replace("$", ""));
		System.out.println("actual price : "+txt);
		list.add(txt);
	}
return list;
		
}
public SignIn_Page goToSignInPage() {
	driver.findElement(signIn).click();
	return new SignIn_Page(driver);
	
}
}
