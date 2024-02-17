package test.selenium.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserDriver {
	private WebDriver driver;

	public WebDriver getBrowserDriver(String browser) {
		switch (browser.trim().toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		default:
			System.out.println("Sorry, " + browser + "cannot be used.");
			System.exit(0);
			
		
		//driverFirefox.get("https://www.taylorswift.com/");
		
		
//// MANUAL USE - NO MANAGER - start //
//
//		// CHROME DRIVER //
//		System.setProperty("webdriver.chrome.driverChrome", "C:\\Users\\cong\\Documents\\Selenium_Automation\\drivers\\chromedriver_win32\\chromedriver.exe"); 
//		WebDriver driverChrome = new ChromeDriver();
//		driverChrome.get("https://www.google.com/");
//		
//		// Firefox DRIVER //
//		System.setProperty("webdriver.firefox.driverFirefox", "C:\\Users\\cong\\Documents\\Selenium_Automation\\drivers\\geckodriver-v0.33.0-win-aarch64\\geckodriver.exe");
//		WebDriver driverFirefox = new FirefoxDriver();
//		driverFirefox.get("https://www.taylorswift.com/");
//		
//		// Edge DRIVER //
//		System.setProperty("webdriver.edge.driverEdge", "C:\\Users\\cong\\Documents\\Selenium_Automation\\drivers\\edgedriver_win64\\msedgedriver.exe");
//		WebDriver driverEdge = new EdgeDriver();
//		driverEdge.get("https://kodaline.com/");
//
//// MANUAL USE - NO MANAGER - end //
		
		}
		return driver;
	}

}
