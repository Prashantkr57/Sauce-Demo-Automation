/**
 * 
 */
package prashant_sauce_demo_automation_tests;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import prashant_sauce_demo_automation_pages.CartPage;
import prashant_sauce_demo_automation_pages.CheckoutStepOnePage;
import prashant_sauce_demo_automation_pages.InventoryPage;
import prashant_sauce_demo_automation_pages.LoginPage;

/**
 * 
 */
public class CheckoutStepOneTest {
	
	private WebDriver driver;
	private LoginPage loginPage;
	private InventoryPage inventoryPage;
	private CartPage cartPage;
	private CheckoutStepOnePage checkoutStepOne;
	
	List<String> productsToAdd = Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt");
	String firstName = "Prashant";
	String lastName = "Kumar";
	String zipCode = "123123";
	
	@BeforeClass
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		loginPage = new LoginPage(driver);
		inventoryPage = new InventoryPage(driver);
		cartPage = new CartPage(driver);
		checkoutStepOne = new CheckoutStepOnePage(driver);

	}
	
	@BeforeMethod
	public void navigateToCheckoutPage() {
		driver.get("https://www.saucedemo.com/");
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addProductsToCart(productsToAdd);
        inventoryPage.goToCartPage();
        cartPage.proceedToCheckout();
	}
	
	@Test
	public void verifyErrorforFirstName() {
		checkoutStepOne.enterLastName(lastName);
		checkoutStepOne.enterZipCode(zipCode);
		checkoutStepOne.clickContinue();
		String errorText = checkoutStepOne.getErrorText();
		Assert.assertEquals(errorText, "Error: First Name is required");
	}
	
	@Test
	public void verifyErrorforLastName() {
		checkoutStepOne.enterFirstName(firstName);
		checkoutStepOne.enterZipCode(zipCode);
		checkoutStepOne.clickContinue();
		String errorText = checkoutStepOne.getErrorText();
		Assert.assertEquals(errorText, "Error: Last Name is required");
	}
	
	@Test
	public void verifyErrorforPostalCode() {
		checkoutStepOne.enterFirstName(firstName);
		checkoutStepOne.enterLastName(firstName);
		checkoutStepOne.clickContinue();
		String errorText = checkoutStepOne.getErrorText();
		Assert.assertEquals(errorText, "Error: Postal Code is required");
	}
	
	@Test (priority = 1)
	public void verifyNavigationtoCheckoutpageTwo() {
		checkoutStepOne.goToCheckoutPageTwo(lastName, firstName, zipCode);
		//System.out.println(driver.getCurrentUrl());
		Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-two"), "Navigation to Checkout Step Two failed");
	}
	
	@AfterClass
	public void tearDown() {
        driver.quit();
    }

}
