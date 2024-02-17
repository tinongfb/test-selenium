package test.selenium.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public enum Config {
	BROWSER("BROWSER"), 
	USERNAME("USERNAME"),
	PASSWORD("PASSWORD"),
	SLEEP_INTERVAL_LONG("SLEEP_INTERVAL_LONG"), 
	SLEEP_INTERVAL_SHORT("SLEEP_INTERVAL_SHORT"), 
	CLICK_INTERVAL("CLICK_INTERVAL"),
	URL("URL");
	private String value = "";
	private static Properties props;
	private long sleep;
	private String configLocation = "C:\\Users\\cong\\eclipse-workspace\\test-selenium\\target\\config.properties";
		//	C:\Users\cong\eclipse-workspace\webdriver\target\config.properties
		//	C:\Users\cong\Documents\Selenium_Automation\config.properties
			
	private Config(String value) {
		this.value = value;
	}

	private Config(long sleep) {
		this.sleep = sleep;
	}
	

	public String value() {
		try {
			initializeConfig(new FileInputStream(configLocation));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return props.getProperty(value).trim();
	}
	public long sleep() {
		try {
			initializeConfig(new FileInputStream(configLocation));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return sleep;
	}
	

	public static void initializeConfig(InputStream inputStream) {
		try {
			if (props == null) {
				props = new Properties();
				props.load(inputStream);

				inputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
