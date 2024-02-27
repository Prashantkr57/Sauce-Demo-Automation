package prashant_sauce_demo_automation_tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import junit.framework.Assert;
import prashant_sauce_demo_automation_pages.LoginPage;

public class LoginTest {
	
	private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\91850\\Downloads\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void validLoginTest() {
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        // Assertions to verify successful login
        String loginText=loginPage.afterLoginText();
        Assert.assertEquals(loginText, "Swag Labs");
    }

    @Test
    public void lockedUserLoginTest() {
        loginPage.enterUsername("locked_out_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        // Assertions to verify unsuccessful login
        String lockedText = loginPage.lockedUserText();
        Assert.assertEquals(lockedText, "Epic sadface: Sorry, this user has been locked out.");
    }
    
    @Test
    public void invalidLoginTest() {
    	loginPage.enterUsername("invalid_user");
    	loginPage.enterPassword("invalid_pass");
    	loginPage.clickLogin();   	
    	// Assertions to verify the invalid text
    	String invalidText = loginPage.invalidLoginText();
    	Assert.assertEquals(invalidText, "Epic sadface: Username and password do not match any user in this service");
    }
    
    @AfterMethod
    public void restDriver() {
    	driver.quit();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
