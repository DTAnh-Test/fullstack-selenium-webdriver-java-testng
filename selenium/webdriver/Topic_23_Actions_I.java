package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_23_Actions_I {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Actions action;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

//	@Test
	public void TC_01_Tooltip() {
		// Di chuột đến element rồi click
		// action.click(driver.findElement(By.id(""))).perform();
		// Không di chuột
		// driver.findElement(By.id("")).click();
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		action.moveToElement(driver.findElement(By.id("age"))).perform();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.cssSelector("div.ui-tooltip-content")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),
				"We ask for your age only for statistical purposes.");
		sleepInSecond(2);
		action.moveToElement(driver.findElement(By.xpath("//a[text()='Tooltips']"))).perform();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.className("ui-tooltip-content")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.className("ui-tooltip-content")).getText(),
				"That's what this widget is");

	}

//	@Test
	public void TC_02_Tooltip() {
		driver.get("https://viettel.vn/");
		action.moveToElement(driver.findElement(By.xpath("//div[@class='menu-pc']//a[text()='Dịch vụ di động']")))
				.perform();
		sleepInSecond(2);

		action.click(driver.findElement(By.xpath("//div[@class='menu-pc']//a[text()='Mua sim số']"))).perform();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//strong[text()='Mua sim số']")).getText(), "Mua sim số");
	}

//	@Test
	public void TC_03_Click_And_Hold_Block() {
		// Cách 1:
		// 1. Click chuột trái vào 1 số bắt đầu
		// 2. Vẫn giữ chuột trái => clickAndHold
		// 3. Kéo chuột/ move chuột tới số kết thúc => moveToElement
		// 4. Nhả chuột trái ra => release()
		// 5. Thực thi các hành động trên => perform()
		driver.get("https://automationfc.github.io/jquery-selectable/");
		action.clickAndHold(driver.findElement(By.xpath("//li[text()='1']")))
				.moveToElement(driver.findElement(By.xpath("//li[text()='6']"))).release().perform();
		sleepInSecond(3);

		Assert.assertTrue(
				driver.findElement(By.xpath("//li[text()='1' and contains(@class,'ui-selected')]")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//li[text()='2' and contains(@class,'ui-selected')]")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//li[text()='5' and contains(@class,'ui-selected')]")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//li[text()='6' and contains(@class,'ui-selected')]")).isDisplayed());
	}

//	@Test
	public void TC_04_Click_And_Hold_Block() {
		// Cách 2:
		// Nên lưu hết tất cả 12 số lại
		// Muốn thao tác số nào thì lôi số đó ra
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable>li"));

		action.clickAndHold(allNumber.get(0)).moveToElement(allNumber.get(3)).release().perform();
		sleepInSecond(2);
		List<WebElement> numberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(numberSelected.size(), 4);
	}

	@Test
	public void TC_05_Click_And_Hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		Keys key = null;
		if (osName.contains("Windows")) {
			key = Keys.CONTROL;
		} else {
			key = Keys.COMMAND;
		}
		// 1. Nhấn phím Ctrl
		action.keyDown(key);
		// action.keyDown(Keys.CONTROL).perform();
		// 2. Click chọn các số
		action.click(allNumber.get(0)).click(allNumber.get(2)).click(allNumber.get(5)).click(allNumber.get(7))
				.click(allNumber.get(9)).perform();
		// 3. Nhả phím Ctrl ra
		action.keyUp(Keys.CONTROL);
		sleepInSecond(3);

		List<WebElement> numberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(numberSelected.size(), 5);
	}

	@Test
	public void TC_06_() {

	}

	@Test
	public void TC_07_() {

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