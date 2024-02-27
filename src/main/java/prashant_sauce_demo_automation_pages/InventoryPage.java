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
import org.openqa.selenium.support.ui.Select;

/**
 * 
 */
public class InventoryPage {
	
	private WebDriver driver;
	
	public InventoryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".inventory_item")
	private List<WebElement> inventoryItems;
	
	@FindBy(xpath = "//select[@class='product_sort_container']")
	private WebElement dropDowns;
	
	@FindBy(css = ".btn_inventory")
    private List<WebElement> addToCartButtons;
	
	@FindBy(css = ".shopping_cart_link")
    private WebElement cartIcon;
	
	public int getInventoryItemCount() {
		return inventoryItems.size();
	}
	
	public void SortInventoryByProceLowtoHigh() {
		Select dropdown = new Select(dropDowns);  
		dropdown.selectByVisibleText("Price (low to high)");  
	}
	
	public List<Float> getInventoryItemPrices() {
	    return inventoryItems.stream()
	                         .map(item -> {
	                             String priceText = item.findElement(By.cssSelector(".inventory_item_price")).getText();
	                             // Assuming the price text is in the format "$X.XX"
	                             return Float.parseFloat(priceText.replace("$", ""));
	                         })
	                         .collect(Collectors.toList());
	}
	
	public void addProductsToCart(List<String> productNames) {
        for (int i = 0; i < inventoryItems.size(); i++) {
            String productName = inventoryItems.get(i).findElement(By.cssSelector(".inventory_item_name")).getText();
            if (productNames.contains(productName)) {
                addToCartButtons.get(i).click();
            }
        }
    }
	
	public void goToCartPage() {
        cartIcon.click();
    }

}
