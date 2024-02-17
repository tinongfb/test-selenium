package test.selenium.cases;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import test.selenium.config.Config;
import test.selenium.utils.BrowserDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class OpenBrowser {
	private WebDriver driver;
	private static BrowserDriver browserDriver;
	private static String browser = Config.BROWSER.value();
	private static String url = Config.URL.value();
	private static String username = Config.USERNAME.value();
	private static String password = Config.PASSWORD.value();	
	
	@Before
	public void setUp() {
		browserDriver = new BrowserDriver();
		driver = browserDriver.getBrowserDriver(browser);
		driver.get(url);
		driver.manage().window().maximize();

		String currentURL = driver.getCurrentUrl();
		String pageTitle = driver.getTitle();
		System.out.println("Current URL: " + currentURL + "\nPage Title: " + pageTitle);
		WebDriverManager.chromedriver().clearDriverCache();
		WebDriverManager.chromedriver().clearResolutionCache();
		
		
	}
	
	@After
	public void close() throws IOException {
		System.out.println("open browser pass");
	}
}
