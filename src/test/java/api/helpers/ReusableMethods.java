package api.helpers;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class ReusableMethods 
{
	public String getContentType(Response response)
	{
		return response.getContentType();
	}
	
	public long getResponseTimeIn(Response response, TimeUnit unit)
	{
		return response.getTimeIn(unit);	
	}
	
	public static void verifyStatusCodeIs(Response response, int statuscode)
	{
		response.then().statusCode(statuscode);
	}
	public static ValidatableResponse verifySchema(Response response, String schema)
	{
		 return response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schema));
	}
	
	public static String getjsonPathData(Response response, String status)
	{
		Assert.assertEquals(status, "success");
		return status;
	}

}
