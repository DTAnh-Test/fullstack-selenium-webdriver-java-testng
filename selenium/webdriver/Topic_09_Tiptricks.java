package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Tiptricks {
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
		// Xpath: $x(""): Tìm element: $x("//button[@id='send2']")
		// Css: $$(""): Tìm element: $$("button#send2")
		// Jquery:$(""). ... (Cần action lên element cần tìm): $("button#send2").click()
		// DOM: document.: 
			// document.getElementById("send2")
			// document.querySelector("button#send2")
			// document.querySelector("button#send2").click()
		// Lấy value vừa nhập vào của element mà chưa send lên server (Không xuất hiện trong HTML):
			// $(document.evaluate("//*[@id='email')". document, null, XPathResult.FIRST_ORDERED_NODE_TYPE. null).singleNodeValue).value
			// $(document.evaluate("//*[@id='email']". document, null, XPathResult.FIRST_ORDERED_NODE_TYPE. null).singleNodeValue).val()
		 	// $(document.evaluate("//*[@id='email')". document, null, XPathResult.STRING_TYPE, null ).stringValue);
			// $("input#Email".val()
			// $("input#Email".value
			// $(document.evaluate("//*[@id='Email')". document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue).value
			// Jquery: $("css").val()
			// JQuery: $("css").text
			// DOM: document.querySelector("css").text

		// Xpath
		 	// var nameTextbox
			// nameTextbox.setAttribute('value'. 'Xpath'):
		 // CSS
		 	// var nameCSS=$$("nput(id 'FirstName'""(0);
		 	// nameCSS.setAttribute('value'. 'Manual'):
		 //JQuery
			 // var nameJQuery = $("#gender-male"):
			 // name/Query.click():
		 //DOM
			 // var domFirstName
			 // document.querySelector("#FirstName"):
			 // domFirstName.setAttribute('value'. 'Testing'):



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