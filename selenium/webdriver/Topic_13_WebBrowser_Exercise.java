package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_WebBrowser_Exercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

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
	public void TC_01_Url() {
		driver.get("http://live.guru99.com/");
		
		// Click MY ACCOUNT
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInsecond(3);
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");
		
		// Click Create an Account
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		sleepInsecond(3);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
	}

//	@Test
	public void TC_02_Title() {
		driver.get("http://live.guru99.com/");
		
		// Click MY ACCOUNT
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInsecond(3);
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		
		// Click Create an Account
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		sleepInsecond(3);
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}

//	@Test
	public void TC_03_Navigate() {
		driver.get("http://live.guru99.com/");
		
		// Click MY ACCOUNT
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInsecond(3);
		// Click Create an Account
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
		
		driver.navigate().back();
		
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.navigate().forward();
		
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		
	}
	
	@Test
	public void TC_04_Source() {
		driver.get("http://live.guru99.com/");
		
		// Click MY ACCOUNT
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInsecond(3);
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		
		// Click Create an Account
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		sleepInsecond(2);
		
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
		
	}
	
	private void sleepInsecond(long timeout) {
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