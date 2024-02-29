/**
 * 
 */
package prashant_sauce_demo_automation_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * 
 */
public class CheckoutStepOnePage {
	
	private WebDriver driver;
	
	public CheckoutStepOnePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@id='first-name']")
	private WebElement firstName;
	
	@FindBy(xpath = "//input[@id='last-name']")
	private WebElement lastName;
	
	@FindBy(xpath = "//input[@id='postal-code']")
	private WebElement postalCode;
	
	@FindBy(xpath = "//input[@id='continue']")
	private WebElement continueButton;
	
	@FindBy(xpath = "//h3[@data-test='error']")
	private WebElement error;
	
	public void enterFirstName(String fName) {
		firstName.sendKeys(fName);
	}
	
	public void enterLastName(String lName) {
		lastName.sendKeys(lName);
	}
	
	public void enterZipCode(String zipCode) {
		postalCode.sendKeys(zipCode);
	}
	
	public void clickContinue() {
		continueButton.click();
	}
	
	public String getErrorText() {
		return error.getText();
	}
	
	public void goToCheckoutPageTwo(String fName, String lName, String zipCode) {
		firstName.sendKeys(fName);
		lastName.sendKeys(lName);
		postalCode.sendKeys(zipCode);
		continueButton.click();
	}

}
