package webdriver;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_28_Window_Tab {
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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

//	@Test
	public void TC_01_SwitchWindowById() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// Chỉ chạy cho 2 tab/ 2 window
		String gitHubPageID = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(3);
		switchWindowByID(gitHubPageID);
		
		String googlePageID = driver.getWindowHandle();
		
		driver.findElement(By.name("q")).sendKeys("Auto");
		sleepInSecond(1);
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		sleepInSecond(3);
		switchWindowByID(googlePageID);
		
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Selenium WebDriver API']")).isDisplayed());
	}

//	@Test
	public void TC_02_SwitchWindowByTitle() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// Chạy cho nhiều tab/ window
		String gitHubPageID = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(3);
		
		switchWindowByTitle("Google");
		driver.findElement(By.name("q")).sendKeys("Auto");
		sleepInSecond(1);
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		sleepInSecond(3);
		
		switchWindowByTitle("Selenium WebDriver");
		sleepInSecond(1);
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(3);
		switchWindowByTitle("Facebook – log in or sign up");
		driver.findElement(By.id("email")).sendKeys("Auto@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("Automation");
		sleepInSecond(1);
		
		switchWindowByTitle("Selenium WebDriver");
		sleepInSecond(1);
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleepInSecond(3);
		switchWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		driver.findElement(By.cssSelector("input[data-view-id='main_search_form_input']")).sendKeys("Laptop Lenovo");
		sleepInSecond(2);
		driver.findElement(By.cssSelector("button[data-view-id='main_search_form_button']")).click();
		sleepInSecond(3);
		switchWindowByTitle("Selenium WebDriver");
		sleepInSecond(1);
		
		closeAllWindowWithoutExpected(gitHubPageID);
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Selenium WebDriver API']")).isDisplayed());
	}

//	@Test
	public void TC_03_KynaEnglish() {
		driver.get("https://skills.kynaenglish.vn/");
		jsExcutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//a[text()='Điều khoản dịch vụ']")));
		sleepInSecond(2);
		
		String kynaId = driver.getWindowHandle();
		driver.findElement(By.xpath("//first[text()=' Hotline']/ancestor::ul/following-sibling::div//img[@alt='facebook']")).click();
		sleepInSecond(2);
		switchWindowByTitle("Kyna.vn | Ho Chi Minh City | Facebook");
		
		driver.findElement(By.xpath("//span[text()='See more on Facebook']/parent::div/following-sibling::div//input[@name='email']")).sendKeys("auto@gmail.com");
		sleepInSecond(1);
		
		switchWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		sleepInSecond(2);
		driver.findElement(By.xpath("//first[text()=' Hotline']/ancestor::ul/following-sibling::div//img[@alt='youtube']")).click();
		sleepInSecond(2);
		switchWindowByTitle("Kyna.vn - YouTube");
		driver.findElement(By.cssSelector("form#search-form  input")).sendKeys("Auto");
		driver.findElement(By.cssSelector("form#search-form  input")).sendKeys(Keys.ENTER);
		sleepInSecond(2);
		
		switchWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		sleepInSecond(2);
		WebElement kynaIframe = driver.findElement(By.cssSelector("div.face-content>iframe"));
		driver.switchTo().frame(kynaIframe);
		driver.findElement(By.xpath("//a[text()='Kyna.vn']")).click();
		sleepInSecond(2);
		driver.switchTo().defaultContent();
		switchWindowByTitle("Kyna.vn | Ho Chi Minh City | Facebook");
		driver.findElement(By.xpath("//span[text()='See more on Facebook']/parent::div/following-sibling::div//input[@name='email']")).sendKeys("auto@gmail.com");
		sleepInSecond(1);
		switchWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		driver.findElement(By.xpath("//div[@id='k-footer-copyright']//a[contains(@href,'CustomWebsiteDisplay')]")).click();
		sleepInSecond(2);
		switchWindowByTitle("Thông tin website thương mại điện tử - Online.Gov.VN");
		Assert.assertEquals(driver.getCurrentUrl(), "http://online.gov.vn/Home/WebDetails/61473");
		sleepInSecond(1);
		
		switchWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		sleepInSecond(1);
		driver.findElement(By.xpath("//div[@id='k-footer-copyright']//a[contains(@href,'Home/WebDetails')]")).click();
		sleepInSecond(2);
		switchWindowByTitle("Thông tin website thương mại điện tử - Online.Gov.VN");
		Assert.assertEquals(driver.getCurrentUrl(), "http://online.gov.vn/Home/WebDetails/60140");
		sleepInSecond(1);
		switchWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		jsExcutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("live-search-bar")));
		
		driver.findElement(By.id("live-search-bar")).sendKeys("Excel");
		sleepInSecond(2);
		closeAllWindowWithoutExpected(kynaId);
	}

//	@Test
	public void TC_04_Live_Techpanda() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Samsung Galaxy has been added to comparison list.']")).isDisplayed());
		driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Sony Xperia has been added to comparison list.']")).isDisplayed());
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		sleepInSecond(2);
		
		switchWindowByTitle("Products Comparison List - Magento Commerce");
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Compare Products']")).isDisplayed());
		driver.findElement(By.xpath("//button[@title='Close Window']")).click();
		
		switchWindowByTitle("Mobile");
		driver.findElement(By.id("search")).sendKeys("SamSung");
		sleepInSecond(3);
	}

	@Test
	public void TC_05_Cambridge() {
		driver.get("https://dictionary.cambridge.org/vi/");
		driver.findElement(By.cssSelector("div.hdn.hdib-s>div[amp-access='NOT loggedIn']")).click();
		switchWindowByTitle("Login");
		
		driver.findElement(By.cssSelector("input[value='Log in']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//input[@placeholder='Email *']/following-sibling::span")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@placeholder='Password *']/following-sibling::span")).isDisplayed());
		driver.close();
		switchWindowByTitle("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa");
		driver.findElement(By.id("searchword")).sendKeys("Automation");
		sleepInSecond(3);
		
	}

	// Chỉ chạy cho 2 tab/ 2 window
	public void switchWindowByID(String expectedId) {
		Set<String> allIds = driver.getWindowHandles();
		for (String id : allIds) {
			if (!id.equals(expectedId)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}
	// Chạy cho nhiều tab/ window
	public void switchWindowByTitle(String expectedTitle) {
		Set<String> allIds = driver.getWindowHandles();
		for (String id : allIds) {
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(expectedTitle)) {
				break;
			}
		}
	}
	
	public void closeAllWindowWithoutExpected(String expectedId) {
		Set<String> allIds = driver.getWindowHandles();
		for (String id : allIds) {
			
			if (!id.equals(expectedId)) {
				driver.switchTo().window(id);
				driver.close();
			}
		driver.switchTo().window(expectedId);
		}
		
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