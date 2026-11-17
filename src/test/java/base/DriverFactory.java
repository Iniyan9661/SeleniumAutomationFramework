package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public class DriverFactory {
	private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
	private static final Properties CONFIG = new Properties();
	static {
		try (InputStream is = DriverFactory.class.getClassLoader().getResourceAsStream("config.properties")) {
			CONFIG.load(is);
		} catch (Exception e) {
			throw new RuntimeException("Unable to load config.properties", e);
		}
	}

	public static String get(String key) {
		return CONFIG.getProperty(key);
	}

	public static void initDriver() {
		String browser = get("browser").toLowerCase();
		boolean headless = Boolean.parseBoolean(get("headless"));
		WebDriver driver;
		switch (browser) {
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		default:
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized", "--remote-allow-origins=*");
			if (headless) {
				options.addArguments("--headless=new", "--window-size=1920,1080");
			}
			driver = new ChromeDriver(options);
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		DRIVER.set(driver);
	}

	public static WebDriver getDriver() {
	    if (DRIVER.get() == null) {
	        initDriver();
	    }
	    return DRIVER.get();
	}

	public static void quitDriver() {
		if (DRIVER.get() != null) {
			DRIVER.get().quit();
			DRIVER.remove();
		}
	}
}