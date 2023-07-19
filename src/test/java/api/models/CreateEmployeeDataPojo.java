
package api.models;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"employee_name",
"employee_salary",
"employee_age"
})

public class CreateEmployeeDataPojo {

@JsonProperty("employee_name")
private String employeeName;
@JsonProperty("employee_salary")
private String employeeSalary;
@JsonProperty("employee_age")
private String employeeAge;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

@JsonProperty("employee_name")
public String getEmployeeName() {
return employeeName;
}

@JsonProperty("employee_name")
public void setEmployeeName(String employeeName) {
this.employeeName = employeeName;
}

@JsonProperty("employee_salary")
public String getEmployeeSalary() {
return employeeSalary;
}

@JsonProperty("employee_salary")
public void setEmployeeSalary(String employeeSalary) {
this.employeeSalary = employeeSalary;
}

@JsonProperty("employee_age")
public String getEmployeeAge() {
return employeeAge;
}

@JsonProperty("employee_age")
public void setEmployeeAge(String employeeAge) {
this.employeeAge = employeeAge;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}
