package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_27_Frame_Iframe {
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

	@Test
	public void TC_01_() {
		driver.get("https://skills.kynaenglish.vn/");
		WebElement facebookIframe = driver.findElement(By.cssSelector("div.face-content>iframe"));
		Assert.assertTrue(facebookIframe.isDisplayed());
		
		driver.switchTo().frame(facebookIframe);
		Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText(), "176K followers");
		
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame("cs_chat_iframe");
		driver.findElement(By.className("meshim_widget_Widget")).click();
		sleepInSecond(1);
		
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("ABCD");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0945856712");
		driver.findElement(By.cssSelector("textarea[name='message']")).sendKeys("Phản ánh dịch vụ \n Chất lượng dịch vụ");
		new Select(driver.findElement(By.id("serviceSelect"))).selectByVisibleText("HỖ TRỢ KỸ THUẬT");
		sleepInSecond(2);
		
		driver.findElement(By.className("icons")).click();
		sleepInSecond(1);
		
		driver.switchTo().defaultContent();
		driver.findElement(By.id("live-search-bar")).sendKeys("Excel");
		driver.findElement(By.className("search-button")).click();
		sleepInSecond(3);
		
		List<WebElement> courseName = driver.findElements(By.cssSelector("div.content>h4"));
		
		// Verify course number
		Assert.assertEquals(courseName.size(), 9);
		
		// Verify course name
		for (WebElement course : courseName) {
			Assert.assertTrue(course.getText().contains("Excel"));
		}
		
		
	}

	@Test
	public void TC_02_() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		
		driver.switchTo().frame("login_page");
		
		// Nhập vào userID
		driver.findElement(By.name("fldLoginUserId")).sendKeys("John");
		
		// Click CONTINUE
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(5);
		
		// Switch về page trước đó
		driver.switchTo().defaultContent();
		// Verify password hiển thị
		Assert.assertTrue(driver.findElement(By.id("keyboard")).isDisplayed());
		driver.findElement(By.id("keyboard")).sendKeys("AutoTest");
		
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