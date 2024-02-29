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
import prashant_sauce_demo_automation_pages.CheckoutCompletePage;
import prashant_sauce_demo_automation_pages.CheckoutStepOnePage;
import prashant_sauce_demo_automation_pages.CheckoutStepTwoPage;
import prashant_sauce_demo_automation_pages.InventoryPage;
import prashant_sauce_demo_automation_pages.LoginPage;

/**
 * 
 */
public class CheckoutCompleteTest {
	
	private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutStepOnePage checkoutStepOne;
    private CheckoutStepTwoPage checkoutStepTwo;
    private CheckoutCompletePage checkoutCompletePage;
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
		checkoutCompletePage = new CheckoutCompletePage(driver);
		driver.get("https://www.saucedemo.com/");
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addProductsToCart(productsToAdd);
        inventoryPage.goToCartPage();
        cartPage.proceedToCheckout();
        checkoutStepOne.goToCheckoutPageTwo(lastName, firstName, zipCode);
        checkoutStepTwo.scrollToFinishButton();
        checkoutStepTwo.clickFinishButton();
    }

    @Test
    public void testOrderConfirmationMessage() {
        String expectedMessage = "Thank you for your order!";
        String actualMessage = checkoutCompletePage.getOrderConfirmationMessage();
        Assert.assertEquals(actualMessage, expectedMessage, "Order confirmation message is incorrect");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
