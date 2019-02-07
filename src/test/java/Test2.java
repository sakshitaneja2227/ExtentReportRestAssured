import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Test2 extends BaseClass{
	
	@Test
	public void test2()
	{
		testLogger().assignAuthor("Sakshi Kalra");
		testLogger().assignCategory("Regression Testing");
		testLogger().log(Status.INFO, "This is info....");
		Assert.assertEquals("Sakshi", "Sakshi");
	}
	
	@Test
	public void cobaGetCountry()
	{
		
		RestAssured.defaultParser = Parser.JSON;
		Response res = given().contentType("application/json")
				.get("http://vma-wm-bc-0304:13180/coba/entity/v1/Country/IN").then().extract().response();

		String strRes = res.asString();
		JsonPath jsonstr= new JsonPath(strRes) ;
		System.out.println(jsonstr.get("name").toString());
		
		if(res.statusCode()==200)
		{
			testLogger().log(Status.PASS, "Status code is 200");
			String expectedStr="India";
			String actualStr = jsonstr.get("name").toString().replaceAll("\\[", "").replaceAll("\\]", "");
			if(actualStr.equals(expectedStr))
			{
				testLogger().log(Status.PASS, "Country name is India");
			}
			else
			{
				testLogger().log(Status.FAIL, "Country name is " + res.path("name"));
			}
		}
		else
		{
			testLogger().log(Status.FAIL, "Status code is " + res.getStatusCode());
		}
		
	}


}
