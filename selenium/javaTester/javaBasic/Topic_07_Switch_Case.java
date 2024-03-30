package javaTester.javaBasic;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Topic_07_Switch_Case {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Scanner scanner =  new Scanner(System.in);
	
	@Parameters("browser")
//	@Test
	public void TC_01_Switch_case(String browserName)  {
		switch (browserName) {
		case "FIREFOX":
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case "CHROME":
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "EDGE":
			System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
			driver = new EdgeDriver();
			break;

		default:
			throw new RuntimeException("Browser is not valid");
		}
	}
	
	@Test
	public void TC_02()  {
		int month = scanner.nextInt();
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			System.out.println("Thang co 31 ngay");
			break;
		case 2:	
			System.out.println("Thang co 28 hoac 29 ngay");
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			System.out.println("Thang co 30 ngay");
			break;
		default:
			System.out.println("Thang nhap khong hop le");
			break;
		}
	}
	
	@Test
	public void TC_03()  {
		
	}
}
