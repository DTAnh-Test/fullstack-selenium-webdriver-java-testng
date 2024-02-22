package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_21_Checkbox_Radio {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExcutor;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
		} else {
			System.setProperty("webdriver.edge.driver", projectPath + "/browserDrivers/msedgedriver");
		}

		driver = new EdgeDriver();
		jsExcutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

//	@Test
	public void TC_01_Default_Checkbox() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

		By dualZoneCheckbox = By.xpath("//label[text()='Luggage compartment cover']/preceding-sibling::input");
		// Click checkbox
		if (!driver.findElement(dualZoneCheckbox).isSelected()) {
			jsExcutor.executeScript("arguments[0].scrollIntoView(true);",
					driver.findElement(By.cssSelector("h1.kd-title")));
			sleepInSecond(1);
			driver.findElement(dualZoneCheckbox).click();
			sleepInSecond(1);
		}
		// Verify check box đã đc chọn
		Assert.assertTrue(driver.findElement(dualZoneCheckbox).isSelected());
		// Bỏ chọn checkbox
		if (driver.findElement(dualZoneCheckbox).isSelected()) {
			jsExcutor.executeScript("arguments[0].scrollIntoView(true);",
					driver.findElement(By.cssSelector("h1.kd-title")));
			sleepInSecond(1);
			driver.findElement(dualZoneCheckbox).click();
			sleepInSecond(1);
		}
		// Verify check box đã bỏ chọn
		Assert.assertFalse(driver.findElement(dualZoneCheckbox).isSelected());
	}

//	@Test
	public void TC_02_Default_Radio() {
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

		By petrolTwoDot = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
		By dieselTwoDot = By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::input");

		// Click radio
		if (!driver.findElement(petrolTwoDot).isSelected()) {
			jsExcutor.executeScript("arguments[0].scrollIntoView(true);",
					driver.findElement(By.cssSelector("h1.kd-title")));
			sleepInSecond(1);
			driver.findElement(petrolTwoDot).click();
			sleepInSecond(1);
		}
		// Verify radio đã đc chọn
		Assert.assertTrue(driver.findElement(petrolTwoDot).isSelected());
		// Bỏ chọn radio
		if (driver.findElement(petrolTwoDot).isSelected()) {
			jsExcutor.executeScript("arguments[0].scrollIntoView(true);",
					driver.findElement(By.cssSelector("h1.kd-title")));
			sleepInSecond(1);
			driver.findElement(dieselTwoDot).click();
			sleepInSecond(1);
		}
		// Verify radio đã bỏ chọn
		Assert.assertFalse(driver.findElement(petrolTwoDot).isSelected());
		Assert.assertTrue(driver.findElement(dieselTwoDot).isSelected());

	}

//	@Test
	public void TC_03_Select_All_Checkbox() {
		driver.get("https://automationfc.github.io/multiple-fields/");

		// Dùng 1 list element chứa tất cả check box
		List<WebElement> listCheckbox = driver
				.findElements(By.cssSelector("div.form-single-column  input.form-checkbox"));
		// Click toàn bộ check box
		for (WebElement checkbox : listCheckbox) {
			if (!checkbox.isSelected()) {
				checkbox.click();
			}
		}
		// Verify đã click toàn bộ check box
		for (WebElement checkbox : listCheckbox) {
			Assert.assertTrue(checkbox.isSelected());
		}

	}

//	@Test
	public void TC_04_Select_Checkbox_Radio_By_Condition() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		// Dùng 1 list element chứa tất cả check box
		List<WebElement> listCheckbox = driver
				.findElements(By.cssSelector("div.form-single-column  input.form-checkbox"));
		// Checkbox tên là Gallstones thì mới click
		for (WebElement checkbox : listCheckbox) {
			if (!checkbox.isSelected() && checkbox.getAttribute("value").equals("Gallstones")) {
				checkbox.click();
			}
		}
		// Verify chí có check box Gallstones được chọn
		for (WebElement checkbox : listCheckbox) {
			if (checkbox.getAttribute("value").equals("Gallstones")) {
				Assert.assertTrue(checkbox.isSelected());
			}
		}

		// Lưu hết radio của Exercise
		List<WebElement> excersiceRadio = driver.findElements(
				By.xpath("//label[contains(text(),'Exercise')]//following-sibling::div//input[@type='radio']"));

		for (WebElement radio : excersiceRadio) {
			if (!radio.isSelected() && radio.getAttribute("value").equals("3-4 days")) {
				radio.click();
			}
		}

		// Verify chỉ có radio 3-4 days được chọn
		for (WebElement radio : excersiceRadio) {
			if (radio.isSelected() && radio.getAttribute("value").equals("3-4 days")) {
				Assert.assertTrue(radio.isSelected());
			}
		}
	}

//	@Test
	public void TC_05_Custom() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		By registerRadioVerify = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input");
		By registerRadioClick = By.xpath(
				"//div[text()='Đăng ký cho người thân']/preceding-sibling::div/div[@class='mat-radio-outer-circle']");

		// Thực tế không dùng 3 case 1-2-3
		// Case 1: Nếu như dùng thẻ input thì không click được nhưng lại verify được
//		driver.findElement(registerRadioVerify).click();
//		Assert.assertTrue(driver.findElement(registerRadioVerify).isSelected());

		// Case 2: Dùng thẻ khác hiển thị để click nhưng không verify được
//		driver.findElement(registerRadioClick).click();
//		Assert.assertTrue(driver.findElement(registerRadioClick).isSelected()); 
			// Vì trạng thái isSelected chỉ áp dụng cho thẻ input

		// Case 3: Dùng thẻ khác input để click và dùng thẻ input để verify
//		driver.findElement(registerRadioClick).click();
//		sleepInSecond(2);
//		Assert.assertTrue(driver.findElement(registerRadioVerify).isSelected()); 

		// Case 4: Vẫn dùng input để click và verify -> dùng JS Click
		// JS không quan tâm element có bị che khônng
		jsExcutor.executeScript("arguments[0].click();", driver.findElement(registerRadioVerify));
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(registerRadioVerify).isSelected()); 
			// => Không lạm dụng JS để click vì
				// không đúng với hành vi user, chỉ
				// dùng trong case đặc biệt

		// Lưu ý: các app của google/ facebook/.. thì không auto vì có chống spam =>
		// không auto ở tầng UI
	}

	@Test
	public void TC_06_Radio_Checkbox_No_InputElement() {
		driver.get(
				"https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		By hcmRadio = By.xpath("//div[@aria-label='Hồ Chí Minh' and @aria-checked]");
		By quangnamCheckbox = By.xpath("//div[@aria-label='Quảng Nam' and @aria-checked]");

		// verify HCM radio/ quảng nam check box chưa được chọn
//		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Hồ Chí Minh' and @aria-checked]")).isDisplayed());
		Assert.assertEquals(driver.findElement(hcmRadio).getAttribute("aria-checked"), "false");
		Assert.assertEquals(driver.findElement(quangnamCheckbox).getAttribute("aria-checked"), "false");

		// CLick vào HCM radio
		driver.findElement(hcmRadio).click();
		driver.findElement(quangnamCheckbox).click();

		// Verify HCM radio/ quảng nam check box được chọn thành công
//		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Hồ Chí Minh' and @aria-checked]")).isDisplayed());
		Assert.assertEquals(driver.findElement(hcmRadio).getAttribute("aria-checked"), "true");
		Assert.assertEquals(driver.findElement(quangnamCheckbox).getAttribute("aria-checked"), "true");

		// List element chứa hết tất cả checkbox
		List<WebElement> allCheckboxes = driver.findElements(By.xpath("//div[@role='checkbox' and @aria-label]"));
		for (WebElement checkbox : allCheckboxes) {
			if (checkbox.getAttribute("aria-checked").equals("false")) {
				checkbox.click();
				sleepInSecond(1);
			}
		}
		for (WebElement checkbox : allCheckboxes) {
			Assert.assertEquals(checkbox.getAttribute("aria-checked"), "true");
		}
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