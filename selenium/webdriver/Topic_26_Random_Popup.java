package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_26_Random_Popup {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random rand = new Random();
	String email;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		email = "test" + rand.nextInt(999) + "@gmail.com";
	}

//	@Test
	public void TC_01_Java_Code_Geeks_Not_In_HTML() {
		// 3 trường hợp:
		// Mới mở page: Có. không có trong HTML
		// Element popup được render ra: Chắc chắn có trong HTML
		// Đóng popup thì có thể có hoặc không trong HTML
		// Cách handle:
		// Nếu element có trong HTML thì dùng findElement để check isDisplayed()
		// Nếu element không có trong HTML thì dùng findElements và check size()= 0 ->
		// không hiển thị
		// Mở page thì popup chưa có trong HTML
		driver.get("https://www.javacodegeeks.com/");
		sleepInSecond(40);
		// Sau 1 tgian render thì popup có trong HTML
		// List findElements
		By firstPopup = By.xpath(
				"//div[text()='Need FREE Best Selling eBooks?' or text()='Grab our programming books for FREE!']/ancestor::div[@style='display: block;']");
		By secondPopup = By.xpath(
				"//div[text()='Need FREE Best Selling eBooks?' or text()='Grab our programming books for FREE!']/ancestor::div[@style='display: block;']/div[@data-page='confirmation']");
		List<WebElement> firstStepPopup = driver.findElements(firstPopup);
		List<WebElement> secondStepPopup = driver.findElements(secondPopup);

		// 1-Nếu có hiển thị thì nhập thông tin và click
		// Xử lý tiếp step đến khi popup đóng lại
		if (firstStepPopup.size() > 0 && firstStepPopup.get(0).isDisplayed()) {
			driver.findElement(By.xpath(
					"//div[@data-title='Newsletter Free eBooks' or @data-title ='Newsletter-Books Anime Brief - NEW']//input[@type='email']"))
					.sendKeys(email);
			sleepInSecond(2);
			driver.findElement(By.xpath("//a[@data-label='Get the Books' or @data-label='OK']")).click();
			sleepInSecond(10);

			Assert.assertTrue(secondStepPopup.get(0).isDisplayed());
			Assert.assertFalse(firstStepPopup.get(0).isDisplayed());
			sleepInSecond(11);
			Assert.assertFalse(secondStepPopup.get(0).isDisplayed());
		}
		// 2-Nếu không hiển thị thì qua step tiếp theo
		driver.findElement(By.id("search-input")).sendKeys("Java");
		sleepInSecond(2);
		driver.findElement(By.id("search-submit")).click();
		sleepInSecond(5);
		Assert.assertTrue(driver.findElement(By.cssSelector("h1.page-title>span")).isDisplayed());

	}

//	@Test
	public void TC_02_VNK_In_HTML() {
		driver.get("https://vnk.edu.vn/");
		sleepInSecond(30);
		
		By popup = By.cssSelector("div#tve_editor div[data-style='cb_style_7");
		
		if (driver.findElement(popup).isDisplayed()) {
			driver.findElement(By.cssSelector("div.tcb-icon-display")).click();
			sleepInSecond(2);
			Assert.assertFalse(driver.findElement(popup).isDisplayed());
		}
		
		driver.findElement(By.cssSelector("button.btn-danger")).click();
		sleepInSecond(5);
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://vnk.edu.vn/lich-khai-giang/");
	}

	@Test
	public void TC_03_Dehieu_Not_In_HTML() {
		driver.get("https://dehieu.vn/");
		sleepInSecond(5);
		List<WebElement> popup = driver.findElements(By.cssSelector("section#popup div.popup-content"));
		if (popup.get(0).isDisplayed()  && popup.size() > 0) {
			driver.findElement(By.id("close-popup")).click();
			sleepInSecond(1);
		}
		driver.findElement(By.xpath("//a[text()='Kích hoạt khóa học']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.getCurrentUrl(), "https://dehieu.vn/kich-hoat-khoa-hoc");
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