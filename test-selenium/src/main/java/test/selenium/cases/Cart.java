package test.selenium.cases;

import java.io.IOException;
import java.util.List;
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

public class Cart {
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
	public void manipulateCart() throws InterruptedException {
		List<WebElement> addToCart = driver.findElements(By.className("btn_inventory"));
		for (WebElement add : addToCart) {
			System.out.println(add);
			add.click();
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("added to cart");
		WebElement cartBtn = driver.findElement(By.id("shopping_cart_container"));
		cartBtn.click();
		
		//System.out.println(driver.getCurrentUrl());
		String expectedURL = "https://www.saucedemo.com/cart.html";
		System.out.println("expected url: '" + expectedURL +"'");
		System.out.println("current url: '" + driver.getCurrentUrl() +"'");
		if (driver.getCurrentUrl().equalsIgnoreCase(expectedURL)) {
			System.out.println("page is displaying cart contents");
		} else {
			System.out.println("failed to display cart contents");
		}
		TimeUnit.SECONDS.sleep(pause);
		
		//**check cart content or remove from cart**//
		List<WebElement> shoppingCart = driver.findElements(By.className("cart_button"));
		int itemsCartCount = shoppingCart.size();
		String itemsCart = String.valueOf(itemsCartCount);
		System.out.println("items in cart: " + itemsCart);
		
		WebElement firstItem = shoppingCart.get(0);
		firstItem.click();
		
		List<WebElement> cartAfter = driver.findElements(By.className("cart_button"));
		int itemsCartCountAfter = cartAfter.size();
		System.out.println("items in cart: " + itemsCartCountAfter);
		
		if (itemsCartCount > itemsCartCountAfter) {
			System.out.println("remove cart working");
		} else {
			System.out.println("remove cart not working");
		}
	}
	
	@Test
	public void contShopping() throws InterruptedException {
		WebElement contShoppingBtn = driver.findElement(By.id("continue-shopping"));
		contShoppingBtn.click();
		TimeUnit.SECONDS.sleep(pause);
		String expectedURL = "https://www.saucedemo.com/inventory.html";
		System.out.println("expected url: '" + expectedURL +"'");
		System.out.println("current url: '" + driver.getCurrentUrl() +"'");
		if (driver.getCurrentUrl().equalsIgnoreCase(expectedURL)) {
			System.out.println("cont shopping button works from cart");
		} else {
			System.out.println("cont shopping button from cart does not work");
		}
		
		System.out.println("testing check out..");	
		WebElement cartBtn = driver.findElement(By.id("shopping_cart_container"));
		cartBtn.click();
		
		//**checkout**//
		WebElement checkout = driver.findElement(By.id("checkout"));
		checkout.click();
		WebElement firstNameField = driver.findElement(By.id("first-name"));
		WebElement lastNameField = driver.findElement(By.id("last-name"));
		WebElement postalCodeField = driver.findElement(By.id("postal-code"));
		firstNameField.sendKeys("john");
		lastNameField.sendKeys("doe");
		postalCodeField.sendKeys("123");
		WebElement continueBtn = driver.findElement(By.id("continue"));
		continueBtn.click();
		WebElement finishBtn = driver.findElement(By.id("finish"));
		finishBtn.click();
		
		
		expectedURL = "https://www.saucedemo.com/checkout-complete.html";
		System.out.println("expected url: '" + expectedURL +"'");
		System.out.println("current url: '" + driver.getCurrentUrl() +"'");
		if (driver.getCurrentUrl().equalsIgnoreCase(expectedURL)) {
			System.out.println("checkout complete");
		} else {
			System.out.println("checkout error");
		}
		TimeUnit.SECONDS.sleep(pause);
		
	}
	
	@After
	public void close() throws IOException {
		driver.quit();
	}
	
}
