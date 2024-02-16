package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Textbox_Textarea_II {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstname, lastname, employeeID, username, password, passportID, issueDate, expiryDate, comment;
	Random rand = new Random();

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
		} else {
			System.setProperty("webdriver.edge.driver", projectPath + "/browserDrivers/msedgedriver");
		}

		driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		firstname = "John";
		lastname = "Wick";
		username = "Auto" + rand.nextInt(9999);
		password = "admin@123";
		passportID = "135055685";
		issueDate = "2018-04-17";
		expiryDate = "2028-04-17";
		comment = "Employee Passport \nIndentification Number";
	}

	@Test
	public void TC_01_OrangeHrm() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

		// Login
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		sleepInSecond(4);

		// Click 'PIM'
		driver.findElement(By.xpath("//span[text()='PIM']/parent::a")).click();
		sleepInSecond(2);

		// Click 'Add Employee
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		sleepInSecond(2);

		// Nhập thông tin:
		driver.findElement(By.name("firstName")).sendKeys(firstname);
		driver.findElement(By.name("lastName")).sendKeys(lastname);

		// Get thông tin EmployeeID
		employeeID = driver
				.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"))
				.getAttribute("value");

		// Click switch button
		driver.findElement(By.xpath("//p[text()='Create Login Details']/following-sibling::div")).click();

		// Login details
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Enabled']/input")).isSelected());
		driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input"))
				.sendKeys(username);
		driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input"))
				.sendKeys(password);
		driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input"))
				.sendKeys(password);

		driver.findElement(By.cssSelector("button[type='submit']")).click();
		sleepInSecond(9);

		// Verify data
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), firstname);
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), lastname);
		Assert.assertEquals(
				driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"))
						.getAttribute("value"),
				employeeID);

		// Click Immigration
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInSecond(2);
		// Click Add
		driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button")).click();
		sleepInSecond(2);
		// Nhập data và Save
		driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input"))
				.sendKeys(passportID);
		driver.findElement(By.xpath("//label[text()='Issued Date']/parent::div/following-sibling::div//input"))
				.sendKeys(issueDate);
		driver.findElement(By.xpath("//label[text()='Expiry Date']/parent::div/following-sibling::div//input"))
				.sendKeys(expiryDate);
		driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea"))
		.sendKeys(comment);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		sleepInSecond(3);

		// Click Edit
		driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
		sleepInSecond(5);
		// Verify data
		Assert.assertEquals(
				driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input"))
						.getAttribute("value"),
				passportID);
		Assert.assertEquals(
				driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea"))
						.getAttribute("value"),
				comment);

		// Logout
		driver.findElement(By.className("oxd-userdropdown-tab")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		sleepInSecond(3);

		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		sleepInSecond(4);

		driver.findElement(By.xpath("//span[text()='My Info']/parent::a")).click();
		sleepInSecond(4);

		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), firstname);
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), lastname);
		Assert.assertEquals(
				driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"))
						.getAttribute("value"),
				employeeID);
		
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInSecond(3);
		
	}

	public void sleepInSecond(long timeout) {
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