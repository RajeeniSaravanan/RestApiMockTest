package api.models;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"status",
"data",
"message"
})

public class GetEmployeeDataPojo {

@JsonProperty("status")
private String status;
@JsonProperty("data")
private List<EmpDataPojo> data;
@JsonProperty("message")
private String message;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

@JsonProperty("status")
public String getStatus() {
return status;
}

@JsonProperty("status")
public void setStatus(String status) {
this.status = status;
}

@JsonProperty("data")
public List<EmpDataPojo> getData() {
return data;
}

@JsonProperty("data")
public void setData(List<EmpDataPojo> data) {
this.data = data;
}

@JsonProperty("message")
public String getMessage() {
return message;
}

@JsonProperty("message")
public void setMessage(String message) {
this.message = message;
}

/*
 * @JsonAnyGetter public Map<String, Object> getAdditionalProperties() { return
 * this.additionalProperties; }
 * 
 * @JsonAnySetter public void setAdditionalProperty(String name, Object value) {
 * this.additionalProperties.put(name, value); }
 */

}
