package com.practiseSoftwareTesting.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.practiseSoftwareTesting.base.BaseClass;
import com.practiseSoftwareTesting.pages.LandingPage;

public class LandingPage_Test extends BaseClass {
	LandingPage landing;

	@BeforeClass
	public void setUpTest() {
		setup(); // Initialize WebDriver
		landing = new LandingPage(driver); // Initialize Page Object AFTER setup
	}

	@Test(priority = 0)
	public void verifyTitle() {
		String expectedTitle = "Practice Software Testing - Toolshop - v5.0";
		Assert.assertEquals(landing.getTitle(), expectedTitle, "Page title does not match!");
	}
	@Test(priority = 1)
	public void verifyHomePageLoad() {
		String expectedHomeLink="https://practicesoftwaretesting.com/";
		Assert.assertEquals(landing.getHomeLink(), expectedHomeLink,"Home Link is not present");
		String expectedContactLink="https://practicesoftwaretesting.com/contact";
		Assert.assertEquals(landing.getContactLink(), expectedContactLink,"Contact Link is not present");
		String expectedSignInLink="https://practicesoftwaretesting.com/auth/login";
		Assert.assertEquals(landing.getSignInLink(), expectedSignInLink,"SignIn Link is not present");
	}
	
	@Test(priority = 2)
	public void verifySearchFunctionality() throws InterruptedException {
		List<String> expectedList = new ArrayList<>();
	    expectedList.add("Combination Pliers");
	    expectedList.add("Long Nose Pliers");

	    String searchQuery = "Pliers";
	    List<String> actualResults = landing.searchAndGetTheResults(searchQuery);

	    // Filter the actual results to check only the required items
	    List<String> filteredResults = actualResults.stream()
	            .filter(expectedList::contains)
	            .collect(Collectors.toList());

	    Assert.assertEquals(filteredResults, expectedList);
	}


	@Test(priority = 3)
	public void verifyFilterByCategory() throws InterruptedException {
	    List<Double> actualResult = landing.sortByPriceHighToLow();
	    
	    double arr[] = {14.24,14.15,12.01,9.17};
	    
	    // Convert primitive array to a mutable List<Double>
	    List<Double> expectedList = new ArrayList<>(Arrays.stream(arr).boxed().toList());

	    // Sort the list in descending order
	    Collections.sort(expectedList, Collections.reverseOrder());

	    System.out.println("Sorted list: " + expectedList);
	    
	    Assert.assertEquals(actualResult, expectedList);
	}


	@AfterClass
	public void tearDownTest() {
		//tearDown(); // Close the WebDriver after test execution
	}
}
