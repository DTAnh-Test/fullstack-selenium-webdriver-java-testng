package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Default_Dropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Select select;
	String firstName, lastName, email, companyName, password, date, month, year;
	Random ran = new Random();

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
		
		firstName = "John";
		lastName = "Ken";
		email = "Auto" + ran.nextInt(9999) + "@gmail.com";
		companyName = "AutoFC";
		password = "123456Aa@";
		date = "19";
		month = "December";
		year = "1994";
	}

	@Test
	public void TC_01_() {
		driver.get("https://demo.nopcommerce.com/register");
		
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		sleepInSecond(2);
		
		// Nhập data
		driver.findElement(By.xpath("//label[text()='Male']/preceding-sibling::input")).click();
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Male']/preceding-sibling::input")).isSelected());
		
		new Select(driver.findElement(By.name("DateOfBirthDay"))).selectByVisibleText(date);
		
		new Select(driver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText(month);
		
		new Select(driver.findElement(By.name("DateOfBirthYear"))).selectByVisibleText(year);
		
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Company")).sendKeys(companyName);
		Assert.assertTrue(driver.findElement(By.id("Newsletter")).isSelected());
		
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		driver.findElement(By.id("register-button")).click();
		sleepInSecond(4);
		
		Assert.assertTrue(driver.findElement(By.className("result")).isDisplayed());
		
		// Click My Account
		driver.findElement(By.className("ico-account")).click();
		
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText(),date);
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(),month);
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthYear"))).getFirstSelectedOption().getText(),year);
		
	}

	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}