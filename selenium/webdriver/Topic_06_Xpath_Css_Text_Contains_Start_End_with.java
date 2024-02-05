package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Xpath_Css_Text_Contains_Start_End_with {
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
		driver.get("https://member.lazada.vn/user/register?spm=a2o4n.home-vn.header.d6.62793bdcsDwAHF");
	}

	//@Test
	public void TC_01_() {
		// Lấy giá trị tuyệt đối (Nhanh hơn so với lấy tương đối do phạm vi quét nhỏ hơn)
		driver.findElement(By.xpath("//p[text()='Or, sign up with']"));
		driver.findElement(By.xpath("//p[string()='Or, sign up with']"));
		driver.findElement(By.xpath("//p[@class='mod-change-register-title']"));	
		
		// Lấy giá trị tương đối
			// Contains()
		driver.findElement(By.xpath("//p[contains(text(),'sign up with')]"));
		driver.findElement(By.xpath("//p[contains(@class,'register-title')]"));
		driver.findElement(By.cssSelector("input[data-spm-anchor-id*='a2o42.login_signup']"));
			// starts-with: Hỗ trợ cho text(), @attribute
		driver.findElement(By.xpath("//p[starts-with(text(),'Or, sign')]]"));
		driver.findElement(By.xpath("//input[starts-with(@data-spm-anchor-id,'a2o42.login_signup')]"));
		driver.findElement(By.cssSelector("input[data-spm-anchor-id^='a2o42.login_signup']"));
			// ends-with: Xpath không support, Css support
		driver.findElement(By.cssSelector("input[placeholder$='phone number']"));
	}

	@Test
	public void TC_02_() {
		// Các hàm xử lý text:
		driver.get("https://automationfc.github.io/basic-form/");
			// text()=
			    // text nằm ở trên cùng hàng với chính node (tagname) đó - ko có nằm trong child node
				// - không phải dạng nested text (text lồng nhau - đều nằm trong cùng 1 thẻ cha nhưng các text
				// sẽ được phân chia ra theo từng cấp
			    // text có the nằm index ở đầu/ giữa/ cuối đều lấy được (so với các thẻ con khác)
			    // lấy text tuyệt đối => ko có khoảng trắng/ xuống dòng/ tab ở đầu hoặc cuối chuỗi
				// Không nên dùng string()=''vì dễ gây hiểu nhầm
				// Text()=: Không quan tâm index của text
				// String()=: Bắt buộc text nằm trong chính thẻ đó
			// contains(text(),'')
				// text nằm ở trên chính node đó
				// dạng nested text nhưng text phải nằm ở đầu tiên
				// nếu text nằm trực tiếp trong chid node thì không lấy được
				// Text có khoảng trắng/ xuống dòng, tab ở đầu/ cuối vẫn lấy được (tương đối)
				// không dùng contains(text()='') mà dùng contains(text(),'')
			// contains(.,'')
				// case nào cũng lấy được
				// không nên lạm dụng
			// contains(string(),'')
				// text nằm ở trên chính node đó hoặc nằm trong chid node ở bất kỳ vị trí nào/ nested text cũng lấy được
				// Text có khoảng trắng/ xuống dòng, tab ở đầu/ cuối vẫn lấy được
				// không dùng contains(string='') mà dùng contains(string(),'')
				// Giống contains(.,'')

			// concat()
				// Case hiếm
				// Dùng để nối 2 chuỗi với nhau
				// Nếu có dấu nháy đơn thì dùng nháy đôi bọc lại
				// Nếu có dấu nháy đôi thì dùng nháy đơn bọc lại
				// span[text()=concat('Hello "John", What',"'s happened?")]
	}

	@Test
	public void TC_03_() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}