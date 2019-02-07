import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentManager {

	private static ExtentReports extent;
	private static ExtentHtmlReporter htmlReporter;
	private static String filePath = "./htmlreport.html";
	
	public static ExtentReports getExtent()
	{
		if(extent!=null)
		{
			return extent;
		}
		else
		{
			extent = new ExtentReports();
			extent.attachReporter(getHtmlReporter());
			extent.setSystemInfo("Host Name", "Sakshi Kalra");
			
			extent.setAnalysisStrategy(AnalysisStrategy.CLASS);
			return extent;
		}
	}
	
	public static ExtentHtmlReporter getHtmlReporter()
	{
		htmlReporter = new ExtentHtmlReporter(filePath);
		htmlReporter.setAppendExisting(false);
		htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "\\src\\test\\java\\ReportsConfig.xml");
		
		return htmlReporter;
	}
}
