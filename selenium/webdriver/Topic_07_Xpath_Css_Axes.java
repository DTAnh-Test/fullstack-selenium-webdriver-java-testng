package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Xpath_Css_Axes {
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
		driver.get("https://live.techpanda.org/index.php/mobile.html");
	}

	@Test
	public void TC_01_() {
		// Note:
			// Luôn nhớ mình đang đứng ở node nào để xét
			// Sẽ lấy cái cố định để đi tìm cái không cố định
		// ancestor: Quét tới tất cả các node cha (ông/...) của node hiện tại trở đi
		// parent: Quét tới node cha của node hiện tại
		// preceding: Quét tới tất cả các node bác của node hiện tại (Chính xác không cao)
		// following: Quét tới tất cả các node chú của node hiện tại (Chính xác không cao)
		// preceding-sibling: Quét tới node anh của node hiện tại
		// following-sibling: Quét tới node em của node hiện tại
		// child: Tương đương /*
		// descendant: Tương đương //*
		// Cú pháp: //tagname[]/parent::tagname[]...
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