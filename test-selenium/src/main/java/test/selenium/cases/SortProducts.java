package test.selenium.cases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;
import test.selenium.config.Config;
import test.selenium.utils.BrowserDriver;

public class SortProducts {
	private WebDriver driver;
	private static BrowserDriver browserDriver;
	private static String browser = Config.BROWSER.value();
	private static String url = Config.URL.value();
	private static String username = Config.USERNAME.value();
	private static String password = Config.PASSWORD.value();	
	private static long pause = Long.parseLong(Config.CLICK_INTERVAL.value());
	
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
	public void sort() {
		WebElement sort = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select"));
		System.out.println("dropdown clicked, selecting option hilo");
		
		Select dropdownSort = new Select(sort);
		dropdownSort.selectByValue("hilo");
		
		//get elements in list
		List<WebElement> inventory = driver.findElements(By.id("inventory_container"));
		List<String> actualValues = new ArrayList<>();
		for (WebElement element : inventory) {
			actualValues.add(element.getText());
		}
		List<String> sortedInventory = new ArrayList<>(actualValues);
		Collections.sort(sortedInventory);
		boolean isSortedCorrectly = actualValues.equals(sortedInventory);
		
		System.out.println("Correctly sorted? " + isSortedCorrectly);
		
		
	}
	
	@After
	public void close() throws IOException {
		driver.quit();
	}
}
