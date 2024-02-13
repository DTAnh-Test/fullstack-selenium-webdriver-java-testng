package webdriver;

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

public class Topic_14_Web_Element_Exercise_I {
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

//	@Test
	public void TC_01_Displayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		if (driver.findElement(By.id("email")).isDisplayed()) {
			System.out.println("Email is displayed");
			driver.findElement(By.id("email")).sendKeys("Automation");
		} else {
			System.out.println("Email is not displayed");
		}
		
		if (driver.findElement(By.id("edu")).isDisplayed()) {
			System.out.println("Education is displayed");
			driver.findElement(By.id("edu")).sendKeys("Automation");
		} else {
			System.out.println("Education is not displayed");
		}
		
		if (driver.findElement(By.id("under_18")).isDisplayed()) {
			System.out.println("Under 18 is displayed");
			driver.findElement(By.id("under_18")).click();
		} else {
			System.out.println("Under 18 is not displayed");
		}
		
		if (driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed()) {
			System.out.println("Name user 5 is displayed");
		} else {
			System.out.println("Name user 5 is not displayed");
		}
	}

//	@Test
	public void TC_02_Enable() {
		if (driver.findElement(By.id("email")).isEnabled()) {
			System.out.println("Email textbox is enable");
		} else {
			System.out.println("Email textbox is disable");
		}
		
		if (driver.findElement(By.id("under_18")).isEnabled()) {
			System.out.println("Under 18 radio is enable");
		} else {
			System.out.println("Under 18 radio is disable");
		}
	}

//	@Test
	public void TC_03_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.id("under_18")).click();
		driver.findElement(By.id("java")).click();
		
		// Kiểm tra 1 element được chọn thành công
		Assert.assertTrue(driver.findElement(By.id("under_18")).isSelected());
		Assert.assertTrue(driver.findElement(By.id("java")).isSelected());
		
//		Assert.assertFalse(driver.findElement(By.id("under_18")).isSelected());
//		Assert.assertTrue(driver.findElement(By.id("java")).isSelected());
		
		driver.findElement(By.id("java")).click();
		Assert.assertFalse(driver.findElement(By.id("java")).isSelected());
	}

	@Test
	public void MailChimp() {
		driver.get("https://login.mailchimp.com/signup/");
//		sleepInSecond(5);
//		driver.findElement(By.id("email")).sendKeys("1234abc@gmail.com");
		
//		Assert.assertTrue(driver.findElement(By.id("create-account-enabled")).isEnabled());
		// Scroll tới element
		jsExcutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("create-account-enabled")));
		driver.findElement(By.id("create-account-enabled")).click();
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='email']/following-sibling::span")).getText(), "An email address must contain a single @.");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='new_username']/following-sibling::span")).getText(),"Please enter a value");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']/span")).getText(), "One lowercase character");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']/span")).getText(), "One uppercase character");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='number-char not-completed']/span")).getText(), "One number");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='special-char not-completed']/span")).getText(), "One special character");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='8-char not-completed']/span")).getText(), "8 characters minimum");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='username-check completed']/span")).getText(), "Must not contain username");
		
		// Case 1: Number
		driver.findElement(By.id("new_password")).sendKeys("123");
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']/span")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']/span")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']/span")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']/span")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']/span")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check completed']/span")).isDisplayed());
	
		// Case 2: Lower case
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("bvcgd");
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']/span")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']/span")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']/span")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']/span")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']/span")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check completed']/span")).isDisplayed());
		
		// Case 3: Upper case
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("BNVHFK");
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']/span")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']/span")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']/span")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']/span")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']/span")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check completed']/span")).isDisplayed());
		
		// Case 4: Special char
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("!@#$");
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']/span")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']/span")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']/span")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']/span")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']/span")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check completed']/span")).isDisplayed());
		
		// Case 5: Greater than 8 char
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("dd111111");
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']/span")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']/span")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']/span")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']/span")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']/span")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check completed']/span")).isDisplayed());
		
		// Case 6: Valid
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("dD@123456");
		
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='lowercase-char completed']/span")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='uppercase-char completed']/span")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='number-char completed']/span")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='special-char completed']/span")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']/span")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='username-check completed']/span")).isDisplayed());

		driver.findElement(By.id("marketing_newsletter")).click();
		Assert.assertTrue(driver.findElement(By.id("marketing_newsletter")).isSelected());
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