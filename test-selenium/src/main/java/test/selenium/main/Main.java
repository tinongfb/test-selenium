package test.selenium.main;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

import test.selenium.cases.Cart;
import test.selenium.cases.Login;
import test.selenium.cases.OpenBrowser;
import test.selenium.cases.SideBar;
import test.selenium.cases.SortProducts;
import test.selenium.config.Config;
import test.selenium.utils.BrowserDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Main {
	private static WebDriver driver;
	private static BrowserDriver browserDriver;
	private static String browser = Config.BROWSER.value();
	private static String url = Config.URL.value();
	private static String username = Config.USERNAME.value();
	private static String password = Config.PASSWORD.value();	

	public static void main(String[] args) throws InterruptedException, IOException {
		System.out.println("Test start");
				
		OpenBrowser openBrowser = new OpenBrowser();
		openBrowser.setUp();
		openBrowser.close();
		
		Login login = new Login();
		login.setUp();
		login.login();
		login.close();
		
		SortProducts sort = new SortProducts();
		sort.setUp();
		sort.sort();
		sort.close();
		
		SideBar sideBar	= new SideBar();
		sideBar.setUp();
		sideBar.allItems();
		sideBar.about();
		sideBar.resetApp();
		sideBar.logout();
		sideBar.close();
		
		Cart cart = new Cart();
		cart.setUp();
		cart.manipulateCart();
		cart.contShopping();
		cart.close();
		
		
	}

}
