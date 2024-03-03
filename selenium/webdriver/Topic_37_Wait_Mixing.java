package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Topic_37_Wait_Mixing {
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
		driver.manage().window().maximize();
	}

//	@Test
	public void TC_01_Element_Found_By_Param() {
		// Dung ca 2 loai implicit vaf explicit nhung element duoc tim thay
		// Khong co ngoai le xay ra
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver,10);
		driver.get("https://www.facebook.com/");
		// Implicit
		System.out.println("Start: " + getDateTimeNow());
		driver.findElement(By.id("email"));
		System.out.println("End: " + getDateTimeNow());

		// Explicit
		System.out.println("Start: " + getDateTimeNow());
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		System.out.println("End: " + getDateTimeNow());
	}

//	@Test
	public void TC_02_Element_Not_Found_Only_Implicit_By_Param() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.facebook.com/");
		// Implicit
		// Khong tim thay element => Danh fail testcase tai step findElement
		// Cho het timeout cua implicit
		System.out.println("Start: " + getDateTimeNow());
		driver.findElement(By.cssSelector("input#email1"));
		System.out.println("End: " + getDateTimeNow());

	}

//	@Test
	public void TC_03_Element_Not_Found_Only_Explicit_By_Param() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver,10);
		driver.get("https://www.facebook.com/");
		// Explicit
		// Cho het timeout va danh fail testcase tai line 69
		// Show ra loi exception
		System.out.println("Start: " + getDateTimeNow());
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email1")));
		System.out.println("End: " + getDateTimeNow());
	}

//	@Test
	public void TC_04_Not_Found_Implicit_Explicit() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver,10);
		driver.get("https://www.facebook.com/");
		// Luon luon uu tien implicit truoc: Tim duoc element roi moi lam gi thi lam
		// Implicit
//		System.out.println("Start: " + getDateTimeNow());
//		driver.findElement(By.id("email1"));
//		System.out.println("End: " + getDateTimeNow());

		// Explicit
			// Trong step nay bi ap dung ca 2 loai Wait
			// 10s cua implicit cho findElement
			// 10s cua explicit cho dieu kien
		// Implicit vaf explicit chay bat dong bo: (cos the chay cung luc hoac explicit chay sau implicit 1 chut
		System.out.println("Start: " + getDateTimeNow());
//		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email1")));
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email1")));
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("End: " + getDateTimeNow());
	}

//	@Test
	public void TC_05_Element_Not_Fount_Implicit_Less_Than_Explicit_By_Param(){
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver,10);
		driver.get("https://www.facebook.com/");
		// Luon luon uu tien implicit truoc: Tim duoc element roi moi lam gi thi lam
		// Implicit
//		System.out.println("Start: " + getDateTimeNow());
//		driver.findElement(By.id("email1"));
//		System.out.println("End: " + getDateTimeNow());

		// Explicit
		// Trong step nay bi ap dung ca 2 loai Wait
		// 5s cua implicit cho findElement
		// 10s cua explicit cho dieu kien
		// Implicit vaf explicit chay bat dong bo (Async): (co the chay cung luc hoac explicit chay sau implicit 0.5-1s
		System.out.println("Start: " + getDateTimeNow());
//		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email1")));
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email1")));
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("End: " + getDateTimeNow());
	}

//	@Test
	public void TC_06_Element_Not_Fount_Implicit_Greater_Than_Explicit_By_Param(){
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver,5);
		driver.get("https://www.facebook.com/");
		// Luon luon uu tien implicit truoc: Tim duoc element roi moi lam gi thi lam
		// Implicit
//		System.out.println("Start: " + getDateTimeNow());
//		driver.findElement(By.id("email1"));
//		System.out.println("End: " + getDateTimeNow());

		// Explicit
		// Trong step nay bi ap dung ca 2 loai Wait
		// 10s cua implicit cho findElement
		// 5s cua explicit cho dieu kien
		// Nhan timeout theo dang Asnyc
		// Implicit va explicit chay bat dong bo (Async): (co the chay cung luc hoac explicit chay sau implicit 0.5-1s
		System.out.println("Start: " + getDateTimeNow());
//		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email1")));
		try {
			// Viec findElement (apply implicit timeout) no nam trong ham cua explicit
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email1")));
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("End: " + getDateTimeNow());
	}

	@Test
	public void TC_07_Element_Not_Found_Implicit_Explicit_WebElement_Param(){
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver,5);
		driver.get("https://www.facebook.com/");
		// Luon luon uu tien implicit truoc: Tim duoc element roi moi lam gi thi lam
		System.out.println("Start: " + getDateTimeNow());
//		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email1")));
		try {
			// Explicit co nhung ham co tham so la By/ WebElement (Cung 1 chuc nang nhu nhau) nhwng thoi gian va thong bao loi khac nhau
			// Tham so By: Chay theo bat dong bo (Song song) => Show loi cua ca implicit va explicit
			// Tham so WebElement: Chay theo dang dong bo (Hang doi) => show loi cua implicit
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#email1"))));
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("End: " + getDateTimeNow());
	}

	// Trường hợp 1: Chỉ dùng explicit
		// => Thì tất cả các step liên quan đến find element đều phải dùng explicit trước hết toàn bộ
		// => Rủi ro quên wait là fail ngay
		// => Nếu như viết hàm dùng chung cho framework sau này: Các hàm Wait chỉ được dùng tham số By.
		// => Testcase chạy không ổn định, fail bất thường không rõ lý do ở đâu
	// Trường hợp 2: Dùng cả implicit, explicit
		// => Set wait dư nhưng testcase ổn định (Chênh lệch nhau 0,5 -> 1s)
		// => Nên set timeout 2 wait bằng nhau

	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}