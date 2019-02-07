import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

public class Test1 extends BaseClass{
	
	@Test
	public void test1()
	{
		testLogger().assignAuthor("Sakshi Kalra");
		testLogger().assignCategory("Regression Testing");
		testLogger().log(Status.INFO, "This is info....");
		Assert.assertEquals("Sakshi", "Sakshi");
	}

}
