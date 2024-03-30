package javaTester.javaBasic;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Topic_02_Data_Type {
	// Primitive type/ value type: Nguyên thuỷ
		// Không có function nào
	byte bNumber = 127;
	short sNumber = 15000;
	int iNumber = 650000;
	long lNumber = 9834892;
	float fNumber = 15.98f;
	double dNumber = 15.98d;
	char cChar = 'a';
		
	// Reference type: Tham chiếu
		// Có những functions đi kèm
		// Array
	String[] addressDetail = {"Ha Noi", "HCM"};
	Integer[] studentsNumber = {15, 20, 30};
		
		// Class
	Topic_02_Data_Type topic;
		// Interface
	WebDriver driver;
	
		// Object
	Object aObject;
	
		// Collection: List/ Set/ Queue/ Map
	List<WebElement> homepageLinks =  driver.findElements(By.xpath(""));
	Set<String> allWindows =  driver.getWindowHandles();
	List<String> productName = new ArrayList<String>();
	
	// Global variable
	static int number;
	String address = "Ha Noi";
	
	public static void main() {
		// Local variable
		int studentNumber = 1;
		
		System.out.println(number);
		
		Topic_02_Data_Type topic = new Topic_02_Data_Type();
		System.out.println(topic.address);
		
		System.out.println(studentNumber);
		
		
		
	}

}
