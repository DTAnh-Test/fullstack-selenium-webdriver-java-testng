package groupTest;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Payment {
	
	@BeforeClass
	public void beforeClass() {

	}

	@Test(groups = "pay")
	public void TC_01_() {
		System.out.println("TC_01");
	}

	@Test(groups = "pay")
	public void TC_02_() {
		System.out.println("TC_02_Pay");
	}
	
	@Test(groups = "pay")
	public void TC_03_() {
		System.out.println("TC_03_Pay");
	}
	
	@Test(groups = "pay")
	public void TC_04_() {
		System.out.println("TC_04_Pay");		
	}
	
	@Test(groups = "pay")
	public void TC_05_() {
		System.out.println("TC_05_Pay");		
	}
}
