/**
 * 
 */
package prashant_sauce_demo_automation_pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * 
 */
public class CartPage {
	
	private WebDriver driver;
	
	public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	@FindBy(css = ".cart_item")
    private List<WebElement> cartItems;
	
	public List<String> getCartItemsNames() {
	    return cartItems.stream()
	                    .map(item -> item.findElement(By.cssSelector(".inventory_item_name")).getText())
	                    .collect(Collectors.toList());
	}


}
