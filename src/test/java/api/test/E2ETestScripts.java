package api.test;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.helpers.BaseService;
import api.helpers.ReusableMethods;
import api.models.EmpDataPojo;
import api.models.GetEmployeeDataPojo;
import api.models.SuccessCreateEmployeeDataPojo;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class E2ETestScripts extends BaseService 
{
	String extractedId = null;
	
	@BeforeClass
	public void init() 
	{
		RestAssured.baseURI = super.url;
	}

	
	@Test
	public void getEmployeeData() 
	{
		log.info("Begin Get_Employeedata");
		
		Response res = getEmpData();
		
		GetEmployeeDataPojo emps = res.getBody().as(GetEmployeeDataPojo.class);
		
		//Fetching the records
		res.prettyPrint();
		
		//validating the response body
		ReusableMethods.verifySchema(res,"GetEmployeeDatas.json");
		
		int statuscode = res.getStatusCode();
		log.info("Status code :" + statuscode);
		
		//status code validation
		Assert.assertEquals(statuscode, 200);
		log.info("Assertion matched");
		
		//Validating response body status is "success"
		String statusMessage = res.body().jsonPath().getString("status");
		ReusableMethods.getjsonPathData(res, statusMessage);
		log.info("Status message is: " + statusMessage);
		
		//Fetch the number of records
		List<List<EmpDataPojo>> empData = Arrays.asList(emps.getData());
		List<Integer> totalCount = Arrays.asList((emps.getData().size()));
		log.info("Total Count = "+ totalCount);
		
		log.info("End Get_Employeedata");
		
	  }
	 
	@Test
	public void createEmployeeData() throws InterruptedException 
	{
		log.info("Begin Create_Employeedata");

		// Arrange and Act are done in addUserData
		Response res = addUserData();
		res.prettyPrint();
		
		//validating the response body
		SuccessCreateEmployeeDataPojo response = res.getBody().as(SuccessCreateEmployeeDataPojo.class);
		ReusableMethods.verifySchema(res,"CreateEmployeeSuccessSchema.json");
		
		JsonPath responseJson = res.body().jsonPath();
		 if(res.statusCode() == 429 || responseJson.toString().contains("Too Many Requests"))
		 {
			 log.info("Too many requests!! Try running the test again!!");
			  
		 }
		 else
		 {
			 String statusMessage = response.getStatus();
			 log.info("Status message is: " + statusMessage);
			 
			 int statusCode = res.getStatusCode();
			 log.info("Status code :" + statusCode);
			 AssertJUnit.assertEquals(200, statusCode);
		
			 extractedId = res.body().jsonPath().getString("data.id");
			 log.info("Extracted id : " + extractedId);
		
			 String empName = res.body().jsonPath().getString("data.employee_name");
			 String empsalary = res.body().jsonPath().getString("data.employee_salary");
			 String empAge = res.body().jsonPath().getString("data.employee_age");
		
			 Assert.assertTrue(empName.equalsIgnoreCase(this.empName));
			 Assert.assertEquals(empsalary, this.empSalary);
			 Assert.assertEquals(empAge, this.empAge);

			 log.info("All Assertions validated");
			 log.info("End Create_Employeedata");
		 }	
	}
	
	@Test
	public void deleteEmployeeDataById() throws InterruptedException 
	{
		log.info("Begin Delete_Employeedata by create Emp_Id");
		
		Response res = deleteUserData();
		
		log.info(res.statusCode());
			
		
		 JsonPath responseJson = res.body().jsonPath();
		 if(res.statusCode() == 429 || responseJson.toString().contains("Too Many Requests"))
		 {
			 log.info("Too many requests!! Try running the test again!!");
			  
		 }
		 else
		 {
			 log.info("res.statusCode()" + res.statusCode());
			 log.info("res.getStatusCode()" + res.getStatusCode());
			 
			 String statusMessage = res.body().jsonPath().get("status");
			 log.info("Delete success. Status message is: " + statusMessage);
		  }	
		 log.info("End Delete_Employeedata by create Emp_Id");
	}
	
	@Test
	public void deleteEmployeeDataByGivenId() throws InterruptedException 
	{
		log.info("Begin Delete_Employeedata by Given Emp_Id");
		
		Response res =deleteById();
		log.info(res.statusCode());
		res.prettyPrint();
		
		JsonPath responseJson = res.body().jsonPath();
		 if(res.statusCode() == 429 || responseJson.toString().contains("Too Many Requests"))
		 {
			 log.info("Too many requests!! Try running the test again!!");
			  
		 }
		 else
		 {
			 String status = res.body().jsonPath().get("status");
			 AssertJUnit.assertEquals(status, "error");
		
			 String message = res.body().jsonPath().get("message");
			 AssertJUnit.assertEquals(message, "Not found record");
		
			 int code = res.body().jsonPath().get("code");
			 AssertJUnit.assertEquals(code, 400);
		
			 String errors = res.body().jsonPath().get("errors");
			 AssertJUnit.assertEquals(errors, "id is empty");
		 }
		
		log.info("End Delete_Employeedata by Given Emp_Id");
				
	}
	
	@Test
	public void fetchEmployeeDataById() 
	{
		log.info("Begin Fetch_Employeedata by Given Emp_Id");
		
		Response res = fetchEmployeeData();
		res.prettyPrint();
		
		JsonPath responseJson = res.body().jsonPath();
		 if(res.statusCode() == 429 || responseJson.toString().contains("Too Many Requests"))
		 {
			 log.info("Too many requests!! Try running the test again!!");
			  
		 }
		 else
		 {
			 int statusCode = res.statusCode();
			 log.info("Status code : " + statusCode);
			 AssertJUnit.assertEquals(statusCode, 200);
		
			 String status = res.body().jsonPath().get("status");
			 AssertJUnit.assertEquals(status, "success");
		
			 String content = res.contentType();
			 log.info("Content is : " + content);
			 AssertJUnit.assertEquals(content, "application/json");
		
			 String empName = res.body().jsonPath().getString("data.employee_name");
			 Assert.assertTrue(empName.equalsIgnoreCase(this.Name));
		
			 String empsalary = res.body().jsonPath().getString("data.employee_salary");
			 Assert.assertEquals(empsalary, this.Salary);
		
			 String empAge = res.body().jsonPath().getString("data.employee_age");
			 Assert.assertEquals(empAge, this.Age);
		 }
		log.info("End Fetch_Employeedata by Given Emp_Id");
		
	}
}
