package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Css {
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
	public void TC_01_() {
		driver.get("https://live.techpanda.org/index.php/customer/account/login/");
		// Cú pháp Xpath thông thường:
			//tagname[@attribute='value']
		// Cú pháp Css thông thường:
			// tagname[attribute='value'] (bỏ // và @)
		
		// Cú pháp Xpath - Cú pháp Css
			// Đi 1 node: / tương đương với >
		driver.findElement(By.xpath("//ul/li/div/input[@id='email']"));
		driver.findElement(By.cssSelector("ul>li>div>input[id='email']"));
			// Đi nhiều node: // tương đương với ký tự space
		driver.findElement(By.xpath("//ul//input[@id='email']"));
		driver.findElement(By.cssSelector("ul input[id='email']"));
			// Dấu # đại diện cho id
		driver.findElement(By.xpath("input[@id='email']"));
		driver.findElement(By.cssSelector("input[id='email']"));
		driver.findElement(By.cssSelector("input#email"));
		driver.findElement(By.cssSelector("#email"));
			// Dấu . đại diện cho class
		driver.findElement(By.xpath("//span[@class='count']"));
		driver.findElement(By.cssSelector("span[class='count']"));
		driver.findElement(By.cssSelector("span.'count'"));
		driver.findElement(By.cssSelector(".count'"));
			// Dấu . đại diện cho nối 1 chuỗi value trong Css
		driver.findElement(By.cssSelector("div[class='col-1 new-users']"));
		driver.findElement(By.cssSelector("div.new-users"));
		driver.findElement(By.cssSelector("div.col-1.new-users"));
			// Dấu * tương đương contains()
		driver.findElement(By.xpath("//div[contains(@class,'registered')]"));
		driver.findElement(By.cssSelector("div[class*='registered']"));
			// Dấu ^ tương đương starts-with
		driver.findElement(By.xpath("//form[starts-with(@id,'login')]"));
		driver.findElement(By.cssSelector("form[id^='login']"));
			// Dấu $ tương đương ends-with. Xpath không support
		driver.findElement(By.cssSelector("a[class$='skip-search']"));
			// Index
		driver.findElement(By.xpath("//ul[@class='form-list']/li[2]"));
		driver.findElement(By.cssSelector("ul.form-list>li:nth-child(2)"));
			// First Index
		driver.findElement(By.xpath("//ul[@class='form-list']/li[1]"));
		driver.findElement(By.cssSelector("ul.form-list>li:first-child"));
			// Last Index
		driver.findElement(By.xpath("//ul[@class='form-list']/li[last()]"));
		driver.findElement(By.cssSelector("ul.form-list>li:last-child"));

			// Kết hợp nhiều attribute: and or
		driver.findElement(By.xpath("//input[@id='email' and @name='login[username]']"));
		driver.findElement(By.cssSelector("input[id='email'][name='login[username]']"));

		driver.findElement(By.xpath("//input[@id='email' or @name='login[username]']"));
		driver.findElement(By.cssSelector("input[id='email'],[name='login[username]']"));
		
			// Phủ định giá trị: not
		driver.findElement(By.xpath("//input[not(@id='email')]"));
		driver.findElement(By.cssSelector("input:not([id='email'])"));
			
		// Dấu ~ tương tự following-sibling: Css hỗ trợ vì chiều đi xuống
		driver.get("https://live.techpanda.org/index.php/mobile.html");
		driver.findElement(By.xpath("//a[@title='IPhone']/following-sibling::div/h2/following-sibling::div"));
				// Lấy tất cả các node em của node hiện tại: ~
		driver.findElement(By.cssSelector("a[title='IPhone']~div>h2~div"));
				// Lấy ra node em gần kề với node hiện tại: +
		driver.findElement(By.cssSelector("a[title='IPhone']~div>h2+div"));
		// Preceding-sibling/ parent/ ancestor: Css không hỗ trợ vì các hàm này đi ngược lên
		// Text: Css không hỗ trợ
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