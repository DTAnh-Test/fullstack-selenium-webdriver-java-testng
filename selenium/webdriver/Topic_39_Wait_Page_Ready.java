package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_39_Wait_Page_Ready {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver,30);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_() {

		driver.get("https://admin-demo.nopcommerce.com");
		driver.findElement(By.id("Email")).clear();
		driver.findElement(By.id("Email")).sendKeys("admin@yourstore.com");
		driver.findElement(By.id("Password")).clear();
		driver.findElement(By.id("Password")).sendKeys("admin");

		driver.findElement(By.cssSelector("button.login-button")).click();
		// Cho ajaxLoading bien mat
//		Assert.assertTrue(loadingStatus(By.xpath("//div[@id='ajaxBusy']")));
		Assert.assertTrue(isPageLoadedSuccess());
//		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='ajaxBusy']")));

		driver.findElement(By.xpath("//ul[@role='menu']/li/a/p[contains(text(),'Customers')]")).click();


		driver.findElement(By.xpath("//li/a/p[text()=' Customers']")).click();
//		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='ajaxBusy']")));
//		Assert.assertTrue(loadingStatus(By.xpath("//div[@id='ajaxBusy']")));
		Assert.assertTrue(isPageLoadedSuccess());

		driver.findElement(By.cssSelector("input[id='SearchFirstName']")).sendKeys("abc@gmail.com");
//
//		Assert.assertTrue(loadingStatus(By.xpath("//div[@id='ajaxBusy']")));
		Assert.assertTrue(isPageLoadedSuccess());
//		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='ajaxBusy']")));

		driver.findElement(By.xpath("//a[text()='Logout']")).click();

	}

	@Test
	public void TC_02_() {

	}

	// Cach 1:
	public boolean loadingStatus(By locator){
		return explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	// Cach 2:
	public boolean isPageLoadedSuccess() {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}