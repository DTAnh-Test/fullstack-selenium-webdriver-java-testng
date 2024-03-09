package groupTest;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class User {
	
	@BeforeClass(alwaysRun = true) // Luôn luôn chạy
	public void beforeClass() {

	}
	// Mặc định TestNG chạy thứ tự theo Alphebet A-Z, 0-9
	// Cách 1: Đánh priority
	// Cách 2: Đặt tên tescase theo Alphebet
	
	@Test(priority=1)
	public void User_01_Create_New_User() {
		System.out.println("User_01_User");
	}

	@Test(priority=2)
	public void User_02_View_Existing_User() {
		System.out.println("User_02_User");
	}
	
	@Test
	public void User_03_Update_User() {
		System.out.println("User_03_User");
	}
	
	@Test
	public void User_04_Move_User() {
		System.out.println("User_04_User");		
	}
	
	@Test
	public void User_05__Delete_User() {
		System.out.println("User_05_User");		
	}
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {

	}
}
