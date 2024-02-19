package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_25_Fixed_Popup {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

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
	}

//	@Test
	public void TC_01_Popup_In_Dom() {
		driver.get("https://ngoaingu24h.vn/");
		sleepInSecond(2);
		driver.findElement(By.cssSelector("button[class='login_ icon-before']")).click();
		sleepInSecond(1);

		By popupLogin = By.cssSelector("div[id='modal-login-v1'][style]");

		Assert.assertTrue(driver.findElement(popupLogin).isDisplayed());

		driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] input#account-input"))
				.sendKeys("Automation");
		driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] input#password-input"))
				.sendKeys("Automation");
		driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] button.btn-login-v1")).click();
		sleepInSecond(1);

		Assert.assertTrue(driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] div.error-login-panel"))
				.isDisplayed());
		Assert.assertEquals(
				driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] div.error-login-panel")).getText(),
				"Tài khoản không tồn tại!");

		driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] button.close")).click();
		sleepInSecond(1);

		Assert.assertFalse(driver.findElement(popupLogin).isDisplayed());
	}

//	@Test
	public void TC_02_Popup_In_Dom() {
		driver.get("https://skills.kynaenglish.vn/");

		driver.findElement(By.className("login-btn")).click();
		sleepInSecond(1);

		By popupLoginKyna = By.cssSelector("div.k-popup-account-mb div.modal-content");

		Assert.assertTrue(driver.findElement(popupLoginKyna).isDisplayed());

		driver.findElement(By.cssSelector("div.k-popup-account-mb input#user-login")).sendKeys("automation@gmail.com");
		driver.findElement(By.cssSelector("div.k-popup-account-mb input#user-password")).sendKeys("123456");
		driver.findElement(By.cssSelector("div.k-popup-account-mb button#btn-submit-login")).click();
		sleepInSecond(5);

		Assert.assertEquals(
				driver.findElement(By.cssSelector("div#password-form-login-message")).getText(),
				"Sai tên đăng nhập hoặc mật khẩu");
		Assert.assertTrue(
				driver.findElement(By.cssSelector("div.k-popup-account-mb div#password-form-login-message")).isDisplayed());
		
		driver.findElement(By.cssSelector("div.k-popup-account-mb button.k-popup-account-close")).click();
		sleepInSecond(1);
		
		Assert.assertFalse(driver.findElement(popupLoginKyna).isDisplayed());
	}

//	@Test
	public void TC_03_Popup_Not_In_HTML() {
		driver.get("https://tiki.vn/");
		driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();
		sleepInSecond(1);
		
		By loginPopup = By.cssSelector("div.ReactModal__Content");
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		
		driver.findElement(By.className("login-with-email")).click();
		sleepInSecond(1);
		
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Email không được để trống']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Mật khẩu không được để trống']")).isDisplayed());
		
		driver.findElement(By.className("btn-close")).click();
		sleepInSecond(1);
		
		Assert.assertEquals(driver.findElements(By.cssSelector("div.ReactModal__Content")).size(), 0);
	}
	
//	@Test
	public void TC_04_Popup_Not_In_HTML() {
		driver.get("https://www.facebook.com/");
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		sleepInSecond(1);
		
		By registerPopup = By.xpath("//div[text()='Sign Up']/parent::div/parent::div");
		
		Assert.assertTrue(driver.findElement(registerPopup).isDisplayed());
		
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/parent::div/img")).click();
		sleepInSecond(1);
		
		Assert.assertEquals(driver.findElements(registerPopup).size(), 0);
		
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