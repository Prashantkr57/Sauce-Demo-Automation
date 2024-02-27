package prashant_sauce_demo_automation_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	private WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "user-name")
	private WebElement usernameInput;
	
	@FindBy(id = "password")
	private WebElement passwordInput;
	
	@FindBy(xpath = "//input[@id='login-button']")
	private WebElement loginButton;
	
	@FindBy(xpath = "//h3[contains(text(),'Epic sadface: Username and password do not match a')]")
	private WebElement invalidUserText;
	
	@FindBy(css = "h3[data-test='error']")
	private WebElement lockedUserText;
	
	@FindBy(className = "app_logo")
	private WebElement afterLogin;
	
	public void enterUsername(String username) {
		usernameInput.sendKeys(username);
	}
	
	public void enterPassword(String password) {
		passwordInput.sendKeys(password);
	}
	
	public void clickLogin() {
		loginButton.click();
	}
	
	public String invalidLoginText() {
		return invalidUserText.getText();
	}
	
	public String lockedUserText() {
		return lockedUserText.getText();
	}
	
	public String afterLoginText() {
		return afterLogin.getText();
	}
	
	public void login(String username, String password) {
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		loginButton.click();
	}
	

}
