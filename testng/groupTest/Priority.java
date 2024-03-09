package groupTest;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Priority {
	
	@BeforeClass(alwaysRun = true) // Luôn luôn chạy
	public void beforeClass() {

	}

	@Test(groups = {"user","other"})
	public void TC_01_() {
		System.out.println("TC_01_User");
	}

	@Test(groups = "user")
	public void TC_02_() {
		System.out.println("TC_02_User");
	}
	
	@Test(groups = "user")
	public void TC_03_() {
		System.out.println("TC_03_User");
	}
	
	@Test(groups = "user")
	public void TC_04_() {
		System.out.println("TC_04_User");		
	}
	
	@Test(groups = "user")
	public void TC_05_() {
		System.out.println("TC_05_User");		
	}
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {

	}
}
