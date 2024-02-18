package webdriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_24_Actions_II {
	WebDriver driver;
	Actions action;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExecutor;
	String dragDropFile = projectPath + "\\dragAndDrop\\drag_and_drop_helper";

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

//	@Test
	public void TC_01_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// Firefox nếu các element không nằm trong viewport thì thường không click được
		if (driver.toString().contains("Firefox")) {
			// Scroll tới element (Firefox)
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);",
					driver.findElement(By.xpath("//button[text()='Double click me']")));
		}
		// Scroll tới element (Firefox)
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//button[text()='Double click me']")));
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		Assert.assertTrue(driver.findElement(By.id("demo")).isDisplayed());
	}

//	@Test
	public void TC_02_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		// Quit chưa hiển thị
		Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());

		// Right click
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		sleepInSecond(2);

		// Hover chuột vào
		action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
		// Quit sẽ update thêm trạng thái hover
		Assert.assertTrue(
				driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-hover")).isDisplayed());
		// Click vào quit
		action.click(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
		// Accept alert
		driver.switchTo().alert().accept();
		Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
	}

//	@Test
	public void TC_03_Drag_Drop_HTML4() {
		// Drag and drop được list vào những case không nên auto
		// Những case không nên auto: Captcha/ SMS/ OTP/ Bar Code/ QR Code/ Chart/
		// Canvas/ Drag Drop/ Game/ Flex/ Flash...
		// Những trang chuyên chống auto: Google/ Facebook
		// Lý do: 
		driver.get("https://automationfc.github.io/kendo-drag-drop/");

		action.dragAndDrop(driver.findElement(By.cssSelector("div#draggable")),
				driver.findElement(By.cssSelector("div#droptarget"))).perform();
		sleepInSecond(2);

		Assert.assertEquals(driver.findElement(By.cssSelector("div#droptarget")).getText(), "You did great!");

		String targetCircle = driver.findElement(By.cssSelector("div#droptarget")).getCssValue("background-color");
		// Chuyển từ RGB/ RGBA qua kiểu Color
		Color targetCircleColor = Color.fromString(targetCircle);
		// Color có hàm chuyển qua hexa
		// Nên chuyển qua viết hoa
		String targetCircleHexa = targetCircleColor.asHex().toUpperCase();
		// Verify màu background login button
		Assert.assertEquals(targetCircleHexa, "#03A9F4");
	}

//	@Test
	public void TC_04_Drag_Drop_HTML5_Css() throws IOException {
		// Chỉ dùng cho CSS
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		String  dragAndDropContent = getContentFile(dragDropFile);

		// Drag from A to B
		jsExecutor.executeScript(dragAndDropContent);
		sleepInSecond(1);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "B");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "A");
		// Drop from B to A
		jsExecutor.executeScript(dragAndDropContent);
		sleepInSecond(1);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "A");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "B");


	}
	
//	@Test
	public void TC_05_Drag_Drop_HTML5_Offset() throws AWTException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		
		// Drag from A to B
		dragAndDropHTML5ByOffset("div#column-a", "div#column-b");
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "B");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "A");
		
		// Drop from B to A
		dragAndDropHTML5ByOffset("div#column-a", "div#column-b");
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "A");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "B");
	}
	
	public String getContentFile(String filePath) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(filePath);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}
	
	public void dragAndDropHTML5ByOffset(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.cssSelector(sourceLocator));
		WebElement target = driver.findElement(By.cssSelector(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
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