package com.practiseSoftwareTesting.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.practiseSoftwareTesting.base.BaseClass;
import com.practiseSoftwareTesting.pages.LandingPage;
import com.practiseSoftwareTesting.pages.SignIn_Page;
import com.practiseSoftwareTesting.utils.ExcelReader;

public class SignInPage_Test extends BaseClass{
	SignIn_Page signIn;
	@BeforeClass()
	public void beforeClass() {
		setup();
		LandingPage landing=new LandingPage(driver);
		 signIn=landing.goToSignInPage();
	}
@Test()
public void getTitle() {
	String expectedTitle="Login";
	Assert.assertEquals(signIn.getTitle(), expectedTitle);
}
@Test(dataProvider = "ExcelData")
public void loginTest(String username, String password) throws InterruptedException {
    System.out.println("Testing with Username: " + username + ", Password: " + password );
    String actual=signIn.doLogin(username, password);
   

    // Verify successful login (Modify based on your application)
    String expectedErrorMsg = "Invalid email or password"; // Example URL
    Assert.assertEquals(actual,expectedErrorMsg);
}

@DataProvider(name = "ExcelData")
public Object[][] readExcelData() {
    String filePath = "F:\\Selenium Frameworks\\PractiseSoftwareTesting\\src\\main\\java\\com\\practiseSoftwareTesting\\testdata\\testdata.xlsx"; // Update with actual path
    ExcelReader excel = new ExcelReader(filePath);
    Object[][] data = excel.getSheetDataAsObject("Sheet1"); // Sheet Name
    excel.closeWorkbook();
    return data;
}

}
