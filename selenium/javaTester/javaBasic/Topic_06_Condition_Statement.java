package javaTester.javaBasic;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_06_Condition_Statement {

	public static void main(String[] args) {
		boolean status = 5 > 3;
		System.out.println(status);
		// Hàm if nhận vào 1 điều kiện đúng
		// Kiểm tra duy nhất 1 điều kiện
		// Nếu điều kiện đúng thì sẽ action vào trong phần thân
		if (status) {
			// Đúng thì vào phần thân của if
			// Sai thì bỏ qua
		}
		
		WebDriver driver = new FirefoxDriver();
		WebElement salePopup = driver.findElement(By.id(""));
		// Element luôn luôn có trong DOM dù popup có hiển thị hay không
		if (salePopup.isDisplayed()) {
			
		}
		
		// Element không có trong DOM khi popup k hiển thị
		List<WebElement> salePopups = driver.findElements(By.xpath(""));
		// Check element k hiển thị
		if (salePopups.size() > 0 && salePopups.get(0).isDisplayed()) {
			
		}
//		if (browserName.equalsIgnoreCase("Firefox")) {
//			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//			driver = new FirefoxDriver();
//		} else if(browserName.equalsIgnoreCase("Chrome")) {
//			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//			driver = new ChromeDriver();
//		} else if(browserName.equalsIgnoreCase("Edge")) {
//			System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
//			driver = new EdgeDriver();
//		} else {
//			throw new RuntimeException("Browser is not valid");
//		}
		
	}

}
