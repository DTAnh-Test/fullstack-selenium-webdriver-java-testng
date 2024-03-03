package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_35_Wait_Explicit {
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver,15);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_() {
		driver.get("https://web.facebook.com/?_rdc=1&_rdr");

		// Chờ cho 1 alert xuất hien trong HTML
		Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent()); //**
		alert.accept();

		// Cho cho 1 attribute co value
			// Dung truoc ham getAttribute()
		explicitWait.until(ExpectedConditions.attributeContains(
				By.id("login_username"),"placeholder","SDT hoac email"));

		explicitWait.until(ExpectedConditions.attributeToBe(
				By.id("login_username"),"placeholder","Nhap SDT hoac email"));//*

		// Dung de cho cho 1 element co the duoc click hay khong: button, checkbox, radio, link, image
			// Dung truoc ham click()
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".fhs-btn-login")));//**

		// Cho cho 1 element da duoc chon hay chua: checkbox/ radio
			// Dung truoc khi apply isSelected()
		explicitWait.until(ExpectedConditions.elementToBeSelected(By.cssSelector("input[name='sex']")));//*

		// Cho cho frame/ iframe xuat hien va switch vaof frame do
		explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("")));//*

		// Cho cho 1 element khong con visibale nua
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("")));//*

		// Cho cho nhieu element khong con visibale nua
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.id(""))));//*
		// Var Arguments
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElement(By.id(""))));//**
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElement(By.id("")),driver.findElement(By.id(""))));

		// Cho cho cac element cos tong so luong la bao nhieu
			// Bang
		explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.id(""),10));//**
			// It hon
		explicitWait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.id(""),10));
			// Nhieu hon
		explicitWait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.id(""),10));
			// Lay ra so luong bang bao nhieu
		int numberCheckbox = driver.findElements(By.cssSelector("input[type=checkbox]")).size();

		// Thao tac mo tab/ window
			// Cho cho bao nhieu tab/ window xuat hien
		explicitWait.until(ExpectedConditions.numberOfWindowsToBe(3));

		// Cho cho element co trong HTML (Khong quan tam visible hay khong
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.id("")));
		// Cho cho nhieu element co trong HTML (Khong quan tam visible hay khong
			// Dropdown
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("")));//*

		WebElement loginUsernameTextbox = explicitWait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(By.cssSelector("father"),By.cssSelector("child")));
		List<WebElement> usernameTextbox = explicitWait.until(ExpectedConditions.presenceOfNestedElementsLocatedBy(By.id(""),By.cssSelector("input[id='login_username']")));

		// Cho cho 1 element khong con trong HTML nua
		explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.id(""))));
			// Cho va verify
		Assert.assertTrue(explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.id("")))));

		// Dung tuoc ham getText()
		explicitWait.until(ExpectedConditions.textToBe(By.id(""),"Ubuntu One"));//*

		// Dung truoc ham getTitle()
		explicitWait.until(ExpectedConditions.titleContains("Log"));
		explicitWait.until(ExpectedConditions.titleIs("Login"));

		// Dung truoc ham getCurrentUrl()
		explicitWait.until(ExpectedConditions.urlContains("facebook.com"));
		explicitWait.until(ExpectedConditions.urlToBe("facebook.com/"));

		// Cho cho 1 element hien thi
		explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input[type=checkbox]"))));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type=checkbox]")));//**

		// Cho cho nhieu element hien thi
		explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.cssSelector("input[type=checkbox]"))));
		explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.cssSelector("input[type=checkbox]")),driver.findElement(By.cssSelector("input[type=checkbox]"))));
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("input[type=checkbox]")));//**

		explicitWait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(By.cssSelector("father"),By.cssSelector("child")));
		explicitWait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(driver.findElement(By.cssSelector("father")),By.cssSelector("child")));

	}

	@Test
	public void TC_02_() {
		
	}

	@Test
	public void TC_03_() {
		
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