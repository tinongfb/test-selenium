package test.selenium.cases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;
import test.selenium.config.Config;
import test.selenium.utils.BrowserDriver;

public class SideBar {
	private WebDriver driver;
	private static BrowserDriver browserDriver;
	private static String browser = Config.BROWSER.value();
	private static String url = Config.URL.value();
	private static String username = Config.USERNAME.value();
	private static String password = Config.PASSWORD.value();	
	private static long pause = Long.parseLong(Config.CLICK_INTERVAL.value());
	private static long sleepLong = Long.parseLong(Config.SLEEP_INTERVAL_LONG.value());
	private static long sleepShort = Long.parseLong(Config.SLEEP_INTERVAL_SHORT.value());
	
	@Before
	public void setUp() throws InterruptedException {
		browserDriver = new BrowserDriver();
		driver = browserDriver.getBrowserDriver(browser);
		driver.get(url);
		driver.manage().window().maximize();

		String currentURL = driver.getCurrentUrl();
		String pageTitle = driver.getTitle();
		System.out.println("Current URL: " + currentURL + "\nPage Title: " + pageTitle);
		WebDriverManager.chromedriver().clearDriverCache();
		WebDriverManager.chromedriver().clearResolutionCache();
		
		WebElement userField = driver.findElement(By.id("user-name"));
		WebElement pwField = driver.findElement(By.id("password"));
		WebElement loginBtn = driver.findElement(By.id("login-button"));
		
		userField.sendKeys(username);
		pwField.sendKeys(password);
		loginBtn.click();
		TimeUnit.SECONDS.sleep(pause);	
	}
	
	@Test
	public void allItems() throws InterruptedException {
		WebElement sideBtn = driver.findElement(By.id("react-burger-menu-btn"));
		sideBtn.click();
		WebElement allItemsBtn = driver.findElement(By.id("inventory_sidebar_link"));
		allItemsBtn.click();
		TimeUnit.SECONDS.sleep(pause);
		String currentURL = driver.getCurrentUrl();
		String expectedURL = "https://www.saucedemo.com/inventory.html";
		System.out.println("expected url: '" + expectedURL +"'");
		System.out.println("current url: '" + currentURL +"'");
		if (currentURL.equalsIgnoreCase(expectedURL)) {
			System.out.println("inventory sidelink pass");
		} else {
			System.out.println("inventory sidelink fail");
		}
		driver.quit();
	}
	
	@Test
	public void about() throws InterruptedException {
		driver = browserDriver.getBrowserDriver(browser);
		driver.get(url);
		driver.manage().window().maximize();
		WebElement userField = driver.findElement(By.id("user-name"));
		WebElement pwField = driver.findElement(By.id("password"));
		WebElement loginBtn = driver.findElement(By.id("login-button"));
		userField.sendKeys(username);
		pwField.sendKeys(password);
		loginBtn.click();
		TimeUnit.SECONDS.sleep(pause);	
		
			WebElement sideBtn = driver.findElement(By.id("react-burger-menu-btn"));
			sideBtn.click();
			WebElement aboutBtn = driver.findElement(By.id("about_sidebar_link"));
			aboutBtn.click();
		
		TimeUnit.SECONDS.sleep(pause);
		String currentURL = driver.getCurrentUrl();
		String expectedURL = "https://saucelabs.com/";
		System.out.println("expected url: '" + expectedURL +"'");
		System.out.println("current url: '" + currentURL +"'");
		if (currentURL.equalsIgnoreCase(expectedURL)) {
			System.out.println("about sidelink pass");
		} else {
			System.out.println("about sidelink fail");
		}
		driver.quit();
	}
	
	@Test
	public void resetApp() throws InterruptedException {
		driver = browserDriver.getBrowserDriver(browser);
		driver.get(url);
		driver.manage().window().maximize();
		WebElement userField = driver.findElement(By.id("user-name"));
		WebElement pwField = driver.findElement(By.id("password"));
		WebElement loginBtn = driver.findElement(By.id("login-button"));
		userField.sendKeys(username);
		pwField.sendKeys(password);
		loginBtn.click();
		TimeUnit.SECONDS.sleep(pause);	
	
			WebElement sideBtn = driver.findElement(By.id("react-burger-menu-btn"));
			sideBtn.click();
			WebElement resetBtn = driver.findElement(By.id("reset_sidebar_link"));
			resetBtn.click();
			
		TimeUnit.SECONDS.sleep(pause);
		driver.quit();
	}
	
	@Test
	public void logout() throws InterruptedException {
		driver = browserDriver.getBrowserDriver(browser);
		driver.get(url);
		driver.manage().window().maximize();
		WebElement userField = driver.findElement(By.id("user-name"));
		WebElement pwField = driver.findElement(By.id("password"));
		WebElement loginBtn = driver.findElement(By.id("login-button"));
		userField.sendKeys(username);
		pwField.sendKeys(password);
		loginBtn.click();
		TimeUnit.SECONDS.sleep(pause);	
		WebElement sideBtn = driver.findElement(By.id("react-burger-menu-btn"));
		sideBtn.click();
		WebElement logoutBtn = driver.findElement(By.id("logout_sidebar_link"));
		logoutBtn.click();
		TimeUnit.SECONDS.sleep(pause);
		String currentURL = driver.getCurrentUrl();
		String expectedURL = "https://www.saucedemo.com/";
		System.out.println("expected url: '" + expectedURL +"'");
		System.out.println("current url: '" + currentURL +"'");
		if (currentURL.equalsIgnoreCase(expectedURL)) {
			System.out.println("logout sidelink pass");
		} else {
			System.out.println("logout sidelink fail");
		}
		
	}
	

	@After
	public void close() throws IOException {
		driver.quit();
	}
}
