package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Web_Element_Exercise_II {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExcutor;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		jsExcutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Empty() {
		driver.get("http://live.techpanda.org/");
		// Step 1: Click My Account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		// Step 2: Để 2 text box blank
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.id("pass")).sendKeys("");
		// Step 3: Click login
		driver.findElement(By.id("send2")).click();
		// Step 4: Verify message
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(),
				"This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(),
				"This is a required field.");
	}

	@Test
	public void TC_02_Incorrect_Email_Pass() {
		driver.get("http://live.techpanda.org/");
		// Step 1: Click My Account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		// Step 2: Nhập mail/ pass invalid
		driver.findElement(By.id("email")).sendKeys("123@gfd.co");
		driver.findElement(By.id("pass")).sendKeys("123214");
		// Step 3: Click login
		driver.findElement(By.id("send2")).click();
		// Step 4: Verify message
		Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='messages']//span")).getText(),
				"Invalid login or password.");
	}

	@Test
	public void TC_03_Pass_Less_Than_6chars() {
		driver.get("http://live.techpanda.org/");
		// Step 1: Click My Account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		// Step 2: Nhập mail/ pass invalid
		driver.findElement(By.id("email")).sendKeys("123@gfd.co");
		driver.findElement(By.id("pass")).sendKeys("123");
		// Step 3: Click login
		driver.findElement(By.id("send2")).click();
		// Step 4: Verify message
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(),
				"Please enter 6 or more characters without leading or trailing spaces.");
	}

	private void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}