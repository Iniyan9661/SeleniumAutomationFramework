package stepdefinitions;

import base.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.DashboardPage;
import pages.LoginPage;

public class LoginSteps {
	private LoginPage loginPage;
	private DashboardPage dashboardPage;

	@Given("the user is on the OrangeHRM login page")
	public void userIsOnLoginPage() {
		loginPage = new LoginPage(DriverFactory.getDriver());
		loginPage.open();
	}

	@When("the user enters username {string} and password {string}")
	

	public void userEntersCredentials(String username, String password) {
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
	}

	@When("the user clicks the login button")
	public void userClicksLogin() {
		loginPage.clickLogin();
	}

	@Then("the user should be redirected to the dashboard")
	public void userIsOnDashboard() {
		dashboardPage = new DashboardPage(DriverFactory.getDriver());
		Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard header not shown");
		Assert.assertTrue(dashboardPage.getCurrentUrl().contains("dashboard"), "URL does not contain 'dashboard'");
	}

	@Then("the error message {string} should be displayed")
	public void errorMessageShouldBeDisplayed(String expected) {
		Assert.assertEquals(loginPage.getErrorMessage().trim(), expected);
	}

	@Then("the required field validation should be displayed")
	public void requiredValidationDisplayed() {
		int count = loginPage.getRequiredMessages().size();
		Assert.assertEquals(count, 2, "Expected 'Required' for both fields");
		loginPage.getRequiredMessages().forEach(e -> Assert.assertEquals(e.getText().trim(), "Required"));
	}

	@When("the user clicks the forgot your password link")
	public void userClicksForgotPassword() {
		loginPage.clickForgotPassword();
	}

	@Then("the reset password page should be displayed")
	public void resetPasswordPageDisplayed() {
		Assert.assertTrue(loginPage.isResetPasswordPageDisplayed());
		Assert.assertTrue(DriverFactory.getDriver().getCurrentUrl().contains("requestPasswordResetCode"));
	}
}