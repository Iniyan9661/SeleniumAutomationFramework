package pages;

import base.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class LoginPage {
	private final WebDriver driver;
	private final WebDriverWait wait;
	private final By usernameInput = By.name("username");
	private final By passwordInput = By.name("password");
	private final By loginButton = By.cssSelector("button[type='submit']");
	private final By errorMessage = By.cssSelector(".oxd-alert-content-text");
	private final By requiredMessages = By.cssSelector(".oxd-input-field-error-message");
	private final By forgotPasswordLink = By.cssSelector(".orangehrm-login-forgot-header");
	private final By resetPasswordTitle = By.cssSelector(".orangehrm-forgot-password-title");

	// Testing GitHub pull operation
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(DriverFactory.get("timeout"))));
	}

	public void open() {
		driver.get(DriverFactory.get("url"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
	}

	public void enterUsername(String username) {
		WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
		el.clear();
		el.sendKeys(username);
	}

	public void enterPassword(String password) {
		WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
		el.clear();
		el.sendKeys(password);
	}

	public void clickLogin() {
		wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
	}

	public String getErrorMessage() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
	}

	public List<WebElement> getRequiredMessages() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(requiredMessages));
		return driver.findElements(requiredMessages);
	}

	public void clickForgotPassword() {
		wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordLink)).click();
	}

	public boolean isResetPasswordPageDisplayed() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(resetPasswordTitle)).isDisplayed();
	}
}
