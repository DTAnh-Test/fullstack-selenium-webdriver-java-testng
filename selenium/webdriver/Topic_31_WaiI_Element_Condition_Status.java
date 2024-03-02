package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_31_WaiI_Element_Condition_Status {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver,30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	// Status: Visibe/ Invisibe/ Presence/ Staleness
	// Điều kiện 1: Element có trên giao diện và HTML => Element được xem là hiển thị (Visibe/ Displayed/ Visibility)
		// Tiêu chí bắt buộc: Phải có trên UI
		// 1 Element được gọi là present/ presence (in DOM): Thoả mãn điều kiên 1/2: Có trong HTML
	// Điều kiện 2: Element không có trên giao diện nhưng có trong HTML => Element không hiển thị (Invisibe/ Undisplayed/ Invisibility)
		// Tiêu chí bắt buộc: Không có trên UI
		// 1 Element được gọi là present/ presence (in DOM): Thoả mãn điều kiên 1/2: Có trong HTML
	// Điều kiện 3: Element không có trên giao diện và không có trong HTML => Element không hiển thị
		// Tiêu chí bắt buộc: Không có trên UI
		// Staleness: Điều kiện bắt buộc: Không có trong HTML

	// implicitlyWait: Apply cho việc tìm element (findElement/ findElements)
	// explicitWait: Apply các trạng thái cho element: Visibe/ Invisibe/ Presence/ Staleness,...
//	@Test
	public void TC_01_Displayed() {
	// Điêu kện 1: Element có trên giao diện và HTML
		// Điều kiện bắt buộc của Visibe: Element phải có trên UI
		driver.get("https://web.facebook.com/?_rdc=1&_rdr");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("login")));
	}


//	@Test
	public void TC_02_Undisplayed_In_HTML() {
	//	Điều kiện 2: Element không có trên giao diện nhưng có trong HTML
		// Điều kiện bắt buộc của Invisibe: Element không có trên UI
		driver.get("https://web.facebook.com/?_rdc=1&_rdr");
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
	}

//	@Test
	public void TC_03_Undisplayed_Not_In_HTML() {
	// Điều kiện 3: Element không có trên giao diện và không có trong HTML
		driver.get("https://web.facebook.com/?_rdc=1&_rdr");
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
	}

//	@Test
	public void TC_04_Presence(){
		driver.get("https://web.facebook.com/?_rdc=1&_rdr");
		// Điều kiện bắt buộc của Presence: Phải có trong HTML
		// Điêu kện 1: Element có trên giao diện và HTML
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.name("login")));
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		//	Điều kiện 2: Element không có trên giao diện nhưng có trong HTML
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.name("reg_email_confirmation__")));
	}

	@Test
	public void Staleness(){
		driver.get("https://web.facebook.com/?_rdc=1&_rdr");
		// Staless: 1 element đang có ở 1 thời điểm trước đó nhưng sau đó không còn trong HTML nữa
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		// Tại thời điểm này nó đang có trong HTML
		WebElement confirmEmailTextbox = driver.findElement(By.name("reg_email_confirmation__"));
		driver.findElement(By.className("_8idr img")).click();

		// Wait cho email confirm textbox không còn trong HTML nữa
		explicitWait.until(ExpectedConditions.invisibilityOf(confirmEmailTextbox));
		explicitWait.until(ExpectedConditions.stalenessOf(confirmEmailTextbox));
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