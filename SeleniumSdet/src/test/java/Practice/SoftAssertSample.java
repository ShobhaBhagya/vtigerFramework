package Practice;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAssertSample {
	
	@Test
	 public void createCustomerTest()
	 {
	  System.out.println("step 1");
	  System.out.println("step 2");
	  SoftAssert s = new SoftAssert();
	  s.assertEquals("A", "B");
	  System.out.println("step 3");
	  s.assertEquals("X", "Y");
	  System.out.println("step 4");
	  s.assertAll();
	 }
	 
	 @Test
	 public void modifyCustomerTest()
	 {
	  System.out.println("==================================================");
	  System.out.println("step 1");
	  System.out.println("step 2");
	  System.out.println("step 3");
	 }

}
