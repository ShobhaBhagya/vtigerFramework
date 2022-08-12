package Practice;

import org.testng.annotations.Test;

public class test2 {
	
	@Test
	public void createcontact()
	{
		System.out.println("contact created");
		int[] arr = {1,2,3};
		System.out.println(arr[5]);
	}
	
	@Test(dependsOnMethods = "createcontact")
	public void modifycontact()
	{
		System.out.println("modified contact");
	}
	
	@Test(dependsOnMethods = "modifycontact")
	public void deletecontact()
	{
		System.out.println("modified contact");
	}

}
