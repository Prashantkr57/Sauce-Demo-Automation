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
import prashant_sauce_demo_automation_pages.InventoryPage;
import prashant_sauce_demo_automation_pages.LoginPage;

/**
 * 
 */
public class CartTest {
	
	private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    List<String> productsToAdd = Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt");
    
    @BeforeClass
    public void setUp() {
        //System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        driver.get("https://www.saucedemo.com/");
        loginPage.login("standard_user", "secret_sauce");
    }
    
    @Test
    public void verifyItemsAddedToCart() {
        inventoryPage.addProductsToCart(productsToAdd);
        inventoryPage.goToCartPage();
        List<String> cartItemNames = cartPage.getCartItemsNames();
        System.out.println(cartItemNames);
        for (String product : productsToAdd) {
            Assert.assertTrue(cartItemNames.contains(product), product + " is not found in the cart");
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
