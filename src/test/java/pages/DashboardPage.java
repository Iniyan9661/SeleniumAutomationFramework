package pages;

import base.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DashboardPage {
	private final WebDriver driver;
	private final WebDriverWait wait;

	private final By dashboardHeader = By.cssSelector(".oxd-topbar-header-breadcrumb h6");

	public DashboardPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(DriverFactory.get("timeout"))));
	}

	public boolean isDashboardDisplayed() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader)).getText()
				.equalsIgnoreCase("Dashboard");
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
}
