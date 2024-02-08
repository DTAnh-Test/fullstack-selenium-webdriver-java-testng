package webdriver;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Web_Browser_Command {
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
	public void TC_01_Browsers() {
		// Các commands/ hàm để tương tác với browser: Thông qua biến driver
		// Dùng để đóng tab hiện tại/ trình duyệt (nếu chỉ có 1 tab)
			 driver.close(); // Dùng khi handle windows/ tab
		// Không đóng tab -> Đóng browser
			 driver.quit();
		
		// Tìm 1 element với 1 locator nào đó
			 driver.findElement(By.id(""));
		// Tìm nhiều element với 1 locator nào đó
			 driver.findElements(By.id(""));
		
		// Mở ra 1 Url
			 driver.get("");
		
		// Lấy ra Url của page hiện tại
			 driver.getCurrentUrl();
		
		// Lấy ra code HTML/CSS/ JS của page hiện tại
			 driver.getPageSource();
		// Lấy ra title của page hiện tại
			 driver.getTitle();
		// window/ tab: 
			//Lấy ra id của tab/ window hiện tại
				 driver.getWindowHandle();
			// Lấy ra tất cả ID của các tab/ window 
				 driver.getWindowHandles();
		// Gồm các hàm xử lý cookies
				 driver.manage().deleteAllCookies();
				 Set<Cookie> cookies = driver.manage().getCookies();
				 for (Cookie cookie : cookies) {
					driver.manage().addCookie(cookie);
				}
			// logs:
				 driver.manage().logs().get(LogType.BROWSER);
			
			// Chờ element xuất hiện sau bao lâu...:
				 driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			// Chờ cho page được load xong trong bao lâu
				 driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.MINUTES);
			// Chờ 1 đoạn script được thực thi xong: JavascriptExecutor
				 driver.manage().timeouts().setScriptTimeout(15, TimeUnit.HOURS);
			
				 // Window
				  driver.manage().widrindow().fullscreen();
				  driver.manage().window().maximize();
				 // Lấy vị trí ra
				  Point point = driver.manage().window().getPosition();
				  point.getX();
				  point.getY();
				  // Set tại 1 vị trí
				  driver.manage().window().setPosition(new Point(0, 0));
				  Dimension dimension = driver.manage().window().getSize();
				  dimension.getHeight();
				  dimension.getWidth();
				  // Set chiều rộng/ cao cho browser
				  driver.manage().window().setSize(new Dimension(1280, 768));
				  
				  // Navigate: Giả lập hành vi
				  driver.navigate().back();
				  driver.navigate().forward();
				  driver.navigate().refresh();
				  driver.navigate().to("https://tiki.vn/");
				  
				  // SwitchTo: 
				  	// Window/ Tab
				  	// Frame/ Ifame
				  	// Alert
				  driver.switchTo().alert();
				  driver.switchTo().frame(1);
				  driver.switchTo().window("");
	}

	@Test
	public void TC_02_Element() {
		// Các commands/ hàm để tương tác với element: Thông qua findElement
		
	}

	@Test
	public void TC_03_Tips() {
		// Chia ra gồm 3 nhóm chính:
		// Nhóm 1: Hàm tương tác/ action: 
			//Tên hàm thể hiện chức năng của nó
			// Không trả về (return) dữ liệu
			// click/ sendkeys/ select,...
		// Nhóm 2: Lấy ra dữ liệu để làm gì đó
			// Bắt đầu bằng tiền đó getxxx: getText/ getCurrentUrl/...
			// Trả về dữ liệu: Dùng để kiểm tra dữ liệu thực tế và dữ liệu mong đợi  (equals)
			// Assert (Junit/ TestNG/ AssertJ/ Hamcrest/...)
		// Nhóm 3: Kiểm tra
			// Dùng để kiểm tra tính đúng đắn của dữ liệu (true/false) -> hàm Assert
			// isDisplayed/ isEnabled/ isSelected/ isMultiple/...
			// Bắt đầu bằng tiền tố isXXX
			// trả về dữ liệu -> boolean
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}