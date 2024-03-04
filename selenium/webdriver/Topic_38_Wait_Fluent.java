package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class Topic_38_Wait_Fluent {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	FluentWait<WebDriver> fluentWait;
	long durationTime = 30;
	long pollingTime = 1;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

//	@Test
	public void TC_01_() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		// Click Start button
		driver.findElement(By.cssSelector("div#start>button")).click();
		fluentWait = new FluentWait<WebDriver>(driver);
		// Viet lien
//		boolean textStatus = fluentWait.withTimeout(Duration.ofSeconds(durationTime)).pollingEvery(Duration.ofMillis(pollingTime))
//		.ignoring(NoSuchElementException.class).until(new Function<WebDriver, Boolean>() {
//			@Override
//			public Boolean apply(WebDriver driver) {
//				return driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed();
//			}
//		});
//		Assert.assertTrue(textStatus);
		// Viet roi
		// Set timeout tong thoi gian la bao nhieu
		fluentWait.withTimeout(Duration.ofSeconds(durationTime));
				// Polling/ Interval time: Thoi gian tim lap lai
		fluentWait.pollingEvery(Duration.ofMillis(pollingTime));
				// Ignore exception neu khong tim thay
		fluentWait.ignoring(NoSuchElementException.class);
				// Dieu kien de Wait: Wait cho element co locator: //div[@id='finish']/h4[text()='Hello World!'] isDisplayed
					// new Function<T, V>:
						// T: tham so cua ham apply - type cua fluent wait khi khoi tao
						// V: Kieu tra ve cua ham apply, phu thuoc dieu kien muon. Ex: isDisplay thi khai bao Boolean, getText() -> String
						// V: khong khai bao kieu nguyen thuy (boolean,byte, float,int, long,short,...)
							// ma khai bao dang wrapper class (Boolen,Byte, Float,Interger, Long,Short,...)
		fluentWait.until(new Function<WebDriver, Boolean>() {
					@Override
					public Boolean apply(WebDriver driver) {
						return driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed();
					}
				});

		// Wait cho element co text: //div[@id='finish']/h4 => getText()
		String helloText = fluentWait.until(new Function<WebDriver, String>() {
			@Override
			public String apply(WebDriver driver) {
				return driver.findElement(By.xpath("//div[@id='finish']/h4")).getText();
			}
		});
		Assert.assertEquals(helloText,"Hello World!");
	}

	@Test
	public void TC_02_() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.withTimeout(Duration.ofSeconds(durationTime)).pollingEvery(Duration.ofMillis(pollingTime))
				.ignoring(NoSuchElementException.class);
		boolean timeExpectedStatus = fluentWait.until(new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return driver.findElement(By.xpath("//div[@id='javascript_countdown_time' and text()='01:01:00']")).isDisplayed();
			}
		});
		Assert.assertTrue(timeExpectedStatus);

		boolean timeExpected = fluentWait.until(new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return driver.findElement(By.xpath("//div[@id='javascript_countdown_time']")).getText().equals("01:01:00");
			}
		});
		Assert.assertTrue(timeExpected);
	}

	@Test
	public void TC_03_() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.withTimeout(Duration.ofSeconds(durationTime)).pollingEvery(Duration.ofMillis(pollingTime))
				.ignoring(NoSuchElementException.class);
		boolean timeExpected = fluentWait.until(new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return driver.findElement(By.xpath("//div[@id='javascript_countdown_time']")).getText().equals("01:01:00");
			}
		});
		Assert.assertTrue(timeExpected);
	}

	@Test
	public void TC_04_Define_FindElement(){
		driver.get("https://automationfc.github.io/dynamic-loading/");
		// Click Start button
		findWebElement(By.cssSelector("div#start>button")).click();
	}

	// Define lai ham findElement
	public WebElement findWebElement(By locator) {
		fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.withTimeout(Duration.ofSeconds(durationTime)).pollingEvery(Duration.ofMillis(pollingTime))
				.ignoring(NoSuchElementException.class);
		return fluentWait.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver webDriver) {
				return driver.findElement(locator);
			}
		});
	}

	// Define lai ham getText()
	public String getElementText(By locator) {
		fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.withTimeout(Duration.ofSeconds(durationTime)).pollingEvery(Duration.ofMillis(pollingTime))
				.ignoring(NoSuchElementException.class);
		return fluentWait.until(new Function<WebDriver, String>() {
			@Override
			public String apply(WebDriver webDriver) {
				return driver.findElement(locator).getText();
			}
		});
	}

	// Define lai ham isDisplayed()
	public boolean isElementDisplayed(By locator) {
		fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.withTimeout(Duration.ofSeconds(durationTime)).pollingEvery(Duration.ofMillis(pollingTime))
				.ignoring(NoSuchElementException.class);
		return fluentWait.until(new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver webDriver) {
				return driver.findElement(locator).isDisplayed();
			}
		});
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}