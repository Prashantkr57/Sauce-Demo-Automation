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
import org.testng.annotations.Test;

import prashant_sauce_demo_automation_pages.CartPage;
import prashant_sauce_demo_automation_pages.CheckoutStepOnePage;
import prashant_sauce_demo_automation_pages.CheckoutStepTwoPage;
import prashant_sauce_demo_automation_pages.InventoryPage;
import prashant_sauce_demo_automation_pages.LoginPage;

/**
 * 
 */
public class CheckoutStepTwoTest {
	
	private WebDriver driver;
	private LoginPage loginPage;
	private InventoryPage inventoryPage;
	private CartPage cartPage;
	private CheckoutStepOnePage checkoutStepOne;
	private CheckoutStepTwoPage checkoutStepTwo;
	
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
		checkoutStepTwo = new CheckoutStepTwoPage(driver);
		driver.get("https://www.saucedemo.com/");
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addProductsToCart(productsToAdd);
        inventoryPage.goToCartPage();
        cartPage.proceedToCheckout();
        checkoutStepOne.goToCheckoutPageTwo(lastName, firstName, zipCode);

	}
	
	
	@Test
    public void testItemTotalBeforeTaxIsSumOfIndividualItems() {
        double expectedTotal = 55.97;
        double actualTotal = checkoutStepTwo.getItemTotalBeforeTax();
        Assert.assertEquals(actualTotal, expectedTotal, "Item total before tax is not correct");
    }
	
	@Test
	public void testScrollToFinishButton() {
	    // Scroll to the finish button
	    checkoutStepTwo.scrollToFinishButton();

	    // Verify that the finish button is visible after scrolling
	    Assert.assertTrue(checkoutStepTwo.isFinishButtonVisible(), "Finish button is not visible after scrolling");
	}

	
	@AfterClass
    public void tearDown() {
        driver.quit();
    }
	
}
