package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Topic_36_Wait_Explicit_Exercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	String sea = "Sea.jpg";
	String mountain = "Mountain.jpg";
	String water = "Thac nuoc.jpg";

	//	String seaPath = projectPath + "\\uploadFiles\\" + sea; // Window là \\, MAC là //
	String seaPath = projectPath + File.separator + "uploadFiles" + File.separator + sea; // Áp dụng đc cho tất cả các hệ điều hành
	String mountainPath = projectPath + File.separator + "uploadFiles" + File.separator + mountain;
	String waterPath = projectPath + File.separator + "uploadFiles" + File.separator + water;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
		} else {
			System.setProperty("webdriver.edge.driver", projectPath + "/browserDrivers/msedgedriver");
		}

		driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver,30);
		driver.manage().window().maximize();
	}
	// Implicit/ Explicit/ Fluent
		// Nếu thoả mãn điều kiện thì sẽ không cần chờ hết timeout
		// Nếu chưa thoả mãn điều kiện (chưa tìm thấy) thì sẽ có cơ chế tìm lại trong khoảng timeout
		// Nếu như hết timeout vẫn chưa tìm thấy thì đánh fail
//	@Test
	public void TC_01_Visible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		// Click Start button
		driver.findElement(By.cssSelector("div#start>button")).click();
		// Wait cho Hello World text visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
	}

//	@Test
	public void TC_02_Invisible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		// Click Start button
		driver.findElement(By.cssSelector("div#start>button")).click();
		// Wait cho loading icon invisible
			// Khi 1 cái này biến mất thì cái kia mới xuất hiện => invisible và ngược lại
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");

	}

//	@Test
	public void TC_03_Text() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		// Click Start button
		driver.findElement(By.cssSelector("div#start>button")).click();
		// Wait cho Hello World text visible
		explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div#finish>h4"),"Hello World!"));
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
	}

//	@Test
	public void TC_04_Telerik() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		// Chờ cho form Calendar hiển thị (visible)
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceholder1_Panel1")));
		// Wait  và verify cho locator chứa text visible
		Assert.assertEquals(explicitWait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceholder1_Label1"))).getText(),
				"No Selected Dates to display.");

		// Wait cho ngay duoc click va click
		explicitWait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//a[text()='14']/parent::td"))).click();

		// Wait cho loading icon bien mat
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id$='RadCalendar1']>div.raDiv")));
		// Wait  và verify cho locator chứa text visible
		Assert.assertEquals(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceholder1_Label1"))).getText(),
				"Thursday, March 14, 2024");

	}

	@Test
	public void TC_05_Upload(){
		driver.get("https://gofile.io/welcome");
		// Wait cho icon loading bien mat
		Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.className("spinner-border")))));
		// Click Upload files button
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Upload Files']"))).click();
		// Wait cho icon loading bien mat
		Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.id("spinner-border")))));
		System.out.println("End wait icon hide: " + getDateTimeNow());
		// Upload 3 files
		driver.findElement(By.cssSelector("input#filesUploadInput")).sendKeys(seaPath + "\n" + mountainPath + "\n" + waterPath);
//		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input#filesUploadInput"))).sendKeys(seaPath + "\n" + mountainPath + "\n" + waterPath);
		System.out.println("End wait upload: " + getDateTimeNow());
		// Wait cho tat ca upload loading bien mat
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress-bar"))));
		System.out.println("End wait icon hide: " + getDateTimeNow());
		// Wait cho text thanh cong hien thi
		Assert.assertTrue(explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div.mainUploadSuccess div.border-success.text-white"),"Your files have been successfully uploaded")));
		System.out.println("End wait text visible: " + getDateTimeNow());
		// Mo duong link upload thanh cong
		driver.get(driver.findElement(By.cssSelector("div.mainUploadSuccessLink a")).getAttribute("href"));
		Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
		// Wait cho tat ca upload loading bien mat
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress-bar"))));

//		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='"+ sea +"']")).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@class='contentName' and text()='"+ sea +"']")))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@class='contentName' and text()='"+ mountain +"']")))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@class='contentName' and text()='"+ water +"']")))).isDisplayed());
//		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='"+ mountain +"']")).isDisplayed());
//		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='"+ water +"']")).isDisplayed());

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