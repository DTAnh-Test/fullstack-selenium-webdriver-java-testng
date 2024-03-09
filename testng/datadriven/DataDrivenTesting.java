package datadriven;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class DataDrivenTesting {
	
//  @Test(dataProvider = "getUserData")
  @Test(dataProvider = "loginData")
  public void TC_01(String firstParam, String secondParam) {
	  System.out.println(firstParam);
	  System.out.println(secondParam);
  }
  
  @DataProvider(name="loginData")
  public Object[][] getUserData() {
    return new Object[][] {
      new Object[] { "name", "Auto" },
      new Object[] { "address", "123" },
      new Object[] { "city", "abc" },
      new Object[] { "email", "abc@da.co" },
    };
  }
  
  @Test(dataProvider = "getAdminData")
  public void TC_02(String firstParam, String secondParam) {
	  System.out.println(firstParam);
	  System.out.println(secondParam);
  }
  
  @DataProvider(name="admin")
  public Object[][] getAdminData() {
	  return new Object[][] {
		  new Object[] { "name", "Auto" },
		  new Object[] { "address", "123" },
		  new Object[] { "city", "abc" },
		  new Object[] { "email", "abc@da.co" },
	  };
  }
  
  
}
