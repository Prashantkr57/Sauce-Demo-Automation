/**
 * 
 */
package prashant_sauce_demo_automation_tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import prashant_sauce_demo_automation_pages.InventoryPage;
import prashant_sauce_demo_automation_pages.LoginPage;

/**
 * 
 */
public class InventoryTest {
	
	private WebDriver driver;
	private LoginPage loginPage;
	private InventoryPage inventoryPage;
	List<String> productsToAdd = Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt");
	
	@BeforeClass
	public void setUp() {
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\91850\\Downloads\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        driver.get("https://www.saucedemo.com/");
        loginPage.login("standard_user", "secret_sauce");
    }
	
	@Test (priority = 0)
	public void verifyInventoryCount() {
		int expectedItemCount = 6;
		int actualItemCount = inventoryPage.getInventoryItemCount();
		Assert.assertEquals(actualItemCount, expectedItemCount, "Incorrect inventory item count");
	}
	
	@Test (priority = 1)
	public void sortByPrice() {
		inventoryPage.SortInventoryByProceLowtoHigh();
	}
	
	@Test (priority = 2) 
	public void verifyInventorySortingByPrice() {
		List<Float> prices = inventoryPage.getInventoryItemPrices();
		//System.out.println(prices);
		List<Float> sortedPrices = new ArrayList<>(prices);
		Collections.sort(sortedPrices);
		Assert.assertEquals(prices, sortedPrices, "Inventory items are not sorted by price from low to high");
	}
	
	@Test (priority = 0)
	public void addToCart() {
		inventoryPage.addProductsToCart(productsToAdd);
	}
	
	@AfterClass
    public void tearDown() {
        driver.quit();
    }

}
