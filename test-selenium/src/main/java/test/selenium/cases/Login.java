package test.selenium.cases;

import java.io.IOException;
import java.util.NoSuchElementException;
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

public class Login {
	private WebDriver driver;
	private static BrowserDriver browserDriver;
	private static String browser = Config.BROWSER.value();
	private static String url = Config.URL.value();
	private static String username = Config.USERNAME.value();
	private static String password = Config.PASSWORD.value();	
	private static long pause = Long.parseLong(Config.CLICK_INTERVAL.value());
	
	@Before
	public void setUp() {
		browserDriver = new BrowserDriver();
		driver = browserDriver.getBrowserDriver(browser);
		driver.get(url);
		driver.manage().window().maximize();

		String currentURL = driver.getCurrentUrl();
		String pageTitle = driver.getTitle();
		System.out.println("Current URL : " + currentURL + "\nPage Title: " + pageTitle);
		WebDriverManager.chromedriver().clearDriverCache();
		WebDriverManager.chromedriver().clearResolutionCache();
				
	}
	
	
	@Test
	public void login() throws IOException, InterruptedException {
		WebElement userField = driver.findElement(By.id("user-name"));
		WebElement pwField = driver.findElement(By.id("password"));
		WebElement loginBtn = driver.findElement(By.id("login-button"));
		
		//if error message is present
		userField.sendKeys(username);
		pwField.sendKeys("wrongpw");
		loginBtn.click();
		WebElement errorMsg = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3"));
		System.out.println("invalid login key pass");
		TimeUnit.SECONDS.sleep(pause);
		
		if (errorMsg != null) {
			userField.clear();
			pwField.clear();
			userField.sendKeys(username);
			pwField.sendKeys(password);
			loginBtn.click();			
		} else {
			//should proceed out
			System.out.println("login key invalid fail");
		}
		
		
		TimeUnit.SECONDS.sleep(pause);
		String currentTitle = driver.getTitle();
		System.out.println(currentTitle);
		String expectedTitle = "Swag Labs";
		if (currentTitle.equalsIgnoreCase(expectedTitle)) {
			System.out.println("login valid key pass");
		} else {
			System.out.println("login key valid fail");
		}
		

	}

	
	
	@After
	public void close() throws IOException {
		driver.quit();
	}
}
