package stepdefinitions;

import base.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {	
	@Before
	public void setUp(Scenario scenario) {
		System.out.println("Starting scenario: " + scenario.getName());
		DriverFactory.initDriver();
	}

	@After
	public void tearDown(Scenario scenario) {
	    try {
	        if (scenario.isFailed() && DriverFactory.getDriver() != null) {
	            byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver())
	                    .getScreenshotAs(OutputType.BYTES);
	            scenario.attach(screenshot, "image/png", "Failure screenshot");
	        }
	    } catch (Exception e) {
	        System.out.println("Could not capture screenshot: " + e.getMessage());
	    } finally {
	        DriverFactory.quitDriver();
	    }
	}
}