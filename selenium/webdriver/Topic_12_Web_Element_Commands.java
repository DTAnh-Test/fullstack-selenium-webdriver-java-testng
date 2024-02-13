package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Web_Element_Commands {
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Web_Elements() {
		// 1. Chỉ tương tác lên element 1 lần -> Không cần khai báo biến
		// 2. Element dùng lại nhiều lần (Trong 1 page) -> Khai báo biến
		
		// Xoá dữ liệu trong 1 text box/ text area/...
		driver.findElement(By.id("")).clear();
		// Nhập dữ liệu vào 1 text box/ text area/..
		driver.findElement(By.id("")).sendKeys("");
		
		// Trả 1 list elements
		driver.findElements(By.xpath(""));
		
		// Lấy ra giá trị của thuộc tính
		driver.findElement(By.id("search")).getAttribute("placeholder");
		driver.findElement(By.id("send2")).getCssValue("background-color");
		
		// Lấy ra kích thước element
		Dimension loginButtonSize = driver.findElement(By.id("")).getSize();
		// Lấy toạ độ bên ngoài so với độ phân giải màn hình
		Point loginButtonLocation = driver.findElement(By.id("")).getLocation();
		
		Rectangle loginButtonRect = driver.findElement(By.id("")).getRect();
		
		// Chụp ảnh
		driver.findElement(By.id("search")).getScreenshotAs(OutputType.FILE);
		driver.findElement(By.id("search")).getScreenshotAs(OutputType.BASE64);
		
		// Lấy ra tên thẻ khi dùng các loại locator mà không biết trước tên thẻ là gì
		String searchTextboxTagname = driver.findElement(By.cssSelector("#search")).getTagName();
			// Đầu ra của 1 element trên sẽ là đầu vào của 1 element dưới
		driver.findElement(By.xpath("//" + searchTextboxTagname + "[@id='email']"));
		
		// Lấy ra text của chính element và cả các thẻ con của nó
		driver.findElement(By.cssSelector("#search")).getText();
		
		// Áp dụng được với tất cả element
			// Kiểm tra 1 element có hiển thị trên màn hình không (nhìn thấy và thao tác được, có kích thước cụ thể)
		driver.findElement(By.id("")).isDisplayed();
		// Áp dụng được với tất cả element
			// Kiểm tra element có thể thao tác lên được không
		driver.findElement(By.id("")).isEnabled();
		// Áp dụng cho 3 loại: checkbox, radio, dropdown (select)
			// Kiểm tra element đã được chọn rồi hay chưa
		driver.findElement(By.id("")).isSelected();
		// Chỉ apply cho form/ element ttrong form
			// Thay thế cho hành vi click vào button để submit
		driver.findElement(By.id("")).submit();
			
		
	}

	@Test
	public void TC_02_() {
		
	}

	@Test
	public void TC_03_() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}