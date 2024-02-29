/**
 * 
 */
package prashant_sauce_demo_automation_pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * 
 */
public class CheckoutStepTwoPage {
	
	private WebDriver driver;
	
	public CheckoutStepTwoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class='cart_item_label']//div[@class='inventory_item_price']")
    private List<WebElement> itemPrices;

    @FindBy(className = "summary_subtotal_label")
    private WebElement subtotalLabel;

    @FindBy(xpath = "//button[text()='Finish']")
    private WebElement finishButton;

    public double getItemTotalBeforeTax() {
        double total = 0.0;
        for (WebElement itemPrice : itemPrices) {
            String priceText = itemPrice.getText().replace("$", "");
            total += Double.parseDouble(priceText);
        }
        return total;
    }

    public void scrollToFinishButton() {
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", finishButton);
    }

    public void clickFinishButton() {
        finishButton.click();
    }
    
    public boolean isFinishButtonVisible() {
        return finishButton.isDisplayed();
    }

}
