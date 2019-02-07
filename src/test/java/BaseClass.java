import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class BaseClass extends ExtentManager{

	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> classLevelLogger = new ThreadLocal<ExtentTest>();
	private static ThreadLocal<ExtentTest> testLevelLogger = new ThreadLocal<ExtentTest>();

	@BeforeSuite
	public void setUp() {
		extent = ExtentManager.getExtent();
	}

	@BeforeClass
	public void beforeClass() {
		ExtentTest parent = extent.createTest(getClass().getName());
		classLevelLogger.set(parent);
	}

	@BeforeMethod
	public void beforeMethod(Method method) {
		ExtentTest child = classLevelLogger.get().createNode(method.getName());
		testLevelLogger.set(child);
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) 
		{
			testLogger().log(Status.FAIL, "Testcase is failed due to below mentioned problem: ");
			testLogger().log(Status.FAIL, result.getThrowable());
		} 
		else if (result.getStatus() == ITestResult.SUCCESS) {
			testLogger().log(Status.PASS, result.getName() + " This test is Pass");
		} 
		else if (result.getStatus() == ITestResult.SKIP) {
			testLogger().log(Status.SKIP, result.getName() + " This test is Skipped");
			testLogger().log(Status.SKIP, result.getThrowable());
		}

		extent.flush();
	}

	public static ExtentTest testLogger() {
		return testLevelLogger.get();
	}

}
