package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_32_Wait_FindElement {
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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

//	@Test
	public void TC_01_FindElement() {
		driver.get("https://web.facebook.com/?_rdc=1&_rdr");
		System.out.println("Start time: " + getDateTimeNow());
		// 2 hàm findElement/ findElements bị ảnh hưởng thời gian cua implicitWait
		// findElement: Tìm 1 element
		// findElements" Tìm nhiều element
		// Bản chất findElement/ findElements: Tìm element trước khi thao tác lên nó
		// 3 trường hợp xảy ra:
			// 1. Tìm thấy đúng duy nhất 1 element
				// Nó không cần chờ hết thời gian implicit
		driver.findElement(By.id("email"));
		System.out.println("End time: " + getDateTimeNow());
			// 2. Không tìm thấy element nào
				// Nó có cơ chế tìm lại, mỗi 0.5s tìm lại 1 lần
				// TÌm lại mà thấy element thì trả về element đó => Không tìm tiếp nữa
				// Tìm lại vẫn không thấy và hết timeout => Sẽ đánh fail testcase tại step đó và trả về thow: NoSuchElementException
		driver.findElement(By.name("reg_email_confirmation__"));
			// 3. Tìm thấy nhiều hơn 1 element
				// Lấy ra element đầu tiên nếu như có nhiều hơn 1 element
		driver.findElement(By.cssSelector("input[id='email' or id='pass']")).sendKeys("ABC");
				// Khi thao tác với 1 element th nên tối ưu locator
	}

	@Test
	public void TC_02_FindElements() {
		driver.get("https://web.facebook.com/?_rdc=1&_rdr");
		List<WebElement> elements;
		// 1. Tìm thấy đúng duy nhất 1 element
			// Nó không cần chờ hết thời gian implicit
		elements = driver.findElements(By.id("email"));
		System.out.println("Duy nhất 1 element: " + elements.size());
		// 2. Không tìm thấy element nào
			// Nó có cơ chế tìm lại, mỗi 0.5s tìm lại 1 lần
			// TÌm lại mà thấy element thì trả về element đó => Không tìm tiếp nữa
			// Tìm lại vẫn không thấy và hết timeout => Sẽ không đánh fail testcase, trả về 1 list rỗng
		elements = driver.findElements(By.name("reg_email_confirmation__"));
		// 3. Tìm thấy nhiều hơn 1 element
			// Nó không cần chờ hết thời gian implicit
			// Trả về 1 list chứa tất cả element được tìm thấy
		elements = driver.findElements(By.cssSelector("input[id='email' or id='pass']"));
		System.out.println("Nhiều element: " + elements.size());
	}

	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}