package com.w2a.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.w2a.utilities.ExcelReader;
import com.w2a.utilities.ExtentManager;
import com.w2a.utilities.Utilities;

public class Page {
	
	public static WebDriver driver;
	
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browser;
	
	public static TopMenu menu;
	
	public Page(){
		
		if(driver==null){
			
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Config file loaded !!!");
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("OR file loaded !!!");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//For Jenkins:
			//----------------------------
			if(System.getenv("browser")!=null && !System.getenv("browser").isEmpty())
				browser = System.getenv("browser");
			else
				browser = config.getProperty("browser");
			
			config.setProperty("browser", browser);
			//----------------------------			

			if (config.getProperty("browser").equals("firefox")) {

				System.setProperty("webdriver.gecko.driver", 
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\geckodriver.exe");
				driver = new FirefoxDriver();
				log.debug("Firefox Launched !!!");

			} else if (config.getProperty("browser").equals("chrome")) {

				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//src//test//resources//executables//chromedriver.exe");
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--disable-extensions");
				options.addArguments("--disable-infobars");
				driver = new ChromeDriver(options);
				log.debug("Chrome Launched !!!");
				
			} else if (config.getProperty("browser").equals("ie")) {

				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				log.debug("IE Launched !!!");
			}

			driver.manage().window().maximize();
			driver.get(config.getProperty("testsiteurl"));
			log.debug("Navigated to: "+config.getProperty("testsiteurl"));
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 15);
			
			menu = new TopMenu(driver);
		}
		
	}
	
	
	public static void quit(){
		
		if(driver!=null){
			driver.quit();
		}
		log.debug("Test execution completed !!!");
		
	}
	

	public Boolean isElementPresent(String locator){
		
		try{
			
			if(locator.endsWith("_CSS")){
				driver.findElement(By.cssSelector(OR.getProperty(locator)));
			}else if(locator.endsWith("_XPATH")){
				driver.findElement(By.xpath(OR.getProperty(locator)));
			}else if(locator.endsWith("_ID")){
				driver.findElement(By.id(OR.getProperty(locator)));
			}		
			
			log.debug("The Element with locator : "+locator+" is present");
			test.log(LogStatus.INFO, "The Element with locator : "+locator+" is present");
			
			return true;
			
		}catch(NoSuchElementException e){
			
			return false;
			
		}
			
	}
	
	
	public static void click(String locator){
		
		if(locator.endsWith("_CSS")){
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		}else if(locator.endsWith("_XPATH")){
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		}else if(locator.endsWith("_ID")){
			driver.findElement(By.id(OR.getProperty(locator))).click();
		}		
		
		log.debug("Clicking on: "+locator);
		test.log(LogStatus.INFO, "Clicking on: "+locator);
		
	}
	
	
	public static void type(String locator, String value){
		
		if(locator.endsWith("_CSS")){
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		}else if(locator.endsWith("_XPATH")){
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		}else if(locator.endsWith("_ID")){
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		}
		
		log.debug("Typing in: "+locator+", entered value as: "+value);
		test.log(LogStatus.INFO, "Typing in: "+locator+", entered value as: "+value);
		
	}
	
	
	public void verifyEquals(String expected, String actual) throws IOException{
		
		try{
		
			Assert.assertEquals(actual, expected);
			
		}catch(Throwable t){
			
			Utilities.captureScreenshot();
			
			//ReportNG:
			Reporter.log("<br>"+"Verification Failure: "+t.getMessage()+"<br>");
			Reporter.log("<a target=\"_blank\" href="+Utilities.screenshotName+"><img scr="+Utilities.screenshotName+ " height=200 width=400></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			
			//Extent Report:
			test.log(LogStatus.FAIL, "Verification Failed with exception: "+ t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(Utilities.screenshotName));
			
		}	
	}
		
	static WebElement dropdown;

	public void Select(String locator, String value){
		
		if(locator.endsWith("_CSS")){
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		}else if(locator.endsWith("_XPATH")){
			dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
		}else if(locator.endsWith("_ID")){
			dropdown = driver.findElement(By.id(OR.getProperty(locator)));
		}		
		
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
		
		log.debug("Clicking dropdown: "+locator+", with value: "+value);
		test.log(LogStatus.INFO, "Clicking dropdown: "+locator+", with value: "+value);
		
		
	}

}
