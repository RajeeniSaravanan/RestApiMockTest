package api.helpers;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import api.constants.EndPoints;
import api.models.CreateEmployeeDataPojo;
import api.test.E2ETestScripts;
import api.utilities.Utils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class BaseService 
{
	protected Response response;
	protected Properties appProp = null;
	protected String url = null;
	protected Logger log;
	protected  String id = null;
	String extractedId = null;
	protected String empName = null;
	protected String empSalary = null;
	protected String empAge = null;
	protected String empId = null;
	protected String Name = null;
	protected String Salary = null;
	protected String Age = null;
	
	public BaseService()
	{
		Utils pro=new Utils();
		this.appProp= pro.loadFile("configProperties");
		this.url=appProp.getProperty("baseUrl");
		this.empName = appProp.getProperty("employeename");
		this.empSalary=appProp.getProperty("EmployeeSalary");
		this.empAge=appProp.getProperty("EmployeeAge");
		this.empId=appProp.getProperty("GivenId");
		this.Name = appProp.getProperty("name");
		this.Salary=appProp.getProperty("salary");
		this.Age=appProp.getProperty("age");
		this.log = LogManager.getLogger(E2ETestScripts.class.getName());
	}
	
	public Response getEmpData()
	{
		Response response = RestAssured.given().when().get("/employees");
		return response;
	}
	
	protected Response addUserData() throws InterruptedException
	{
		CreateEmployeeDataPojo user = new CreateEmployeeDataPojo();
		
		user.setEmployeeName(this.empName);
		user.setEmployeeSalary(this.empSalary);
		user.setEmployeeAge(this.empAge);
		
		response=RestAssured.given()
		.contentType(ContentType.JSON)
		.body(user)
		.when() // execute the below post method when the body contains the user pojo
		.post(EndPoints.ADD_DATA);
		//response.wait();
		return response;	
	}
	
	protected String getId() throws InterruptedException
	{
		response = addUserData();
		Thread.sleep(2000);
		response.prettyPrint();
		extractedId = response.body().jsonPath().getString("data.id");
		log.info("Created a new user and Extracted id : " + extractedId);
		return extractedId;
	}
	
	protected Response deleteUserData() throws InterruptedException
	{
		String id = getId();
		Response response = RestAssured.delete(EndPoints.DELETE_DATA,id);
		Thread.sleep(2000);
		log.info("Deleting " + id);
		response.prettyPrint();
		return response;
	}
	
	protected Response deleteById() 
	{
		String id = "0";
		Response response = RestAssured.delete(EndPoints.DELETE_DATA,id);
		log.info("Deleting ID-0");
		return response;
		
	}
	
	protected Response fetchEmployeeData()
	{
		//String id = "2";
		Response response = RestAssured.given().when().get(EndPoints.GET_DATABYID,this.empId);
		log.info("Fetching Employee data of Id=2");
		return response;	
	}
	
	
	 
}
