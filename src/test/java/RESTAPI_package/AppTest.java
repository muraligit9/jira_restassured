package RESTAPI_package;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void run()
    {
    	RestAssured.baseURI="http://localhost:8080";
  	  
  	  Response res=given().header("content-Type", "application/json").
  	  body("{ \"username\": \"gsaimurali134\", \"password\": \"Krish@1992\" }").
  	  when().post("/rest/auth/1/session").
  	  then().assertThat().statusCode(200).and().body("session.name", equalTo("JSESSIONID")).
  	  extract().response();
  	  
  	  JsonPath js=new JsonPath(res.asString());
  	  
  	  String name=js.get("session.name");
  	  String value=js.get("session.value");
  	  
  	  String accesskey=name+"="+value;
  	  
  	  Response res1=given().header("content-Type", "application/json").header("cookie",accesskey).
  	  body("{\r\n" + 
  	  		"    \"fields\": {\r\n" + 
  	  		"       \"project\":\r\n" + 
  	  		"       {\r\n" + 
  	  		"          \"key\": \"REST\"\r\n" + 
  	  		"       },\r\n" + 
  	  		"       \"summary\": \"third issue\",\r\n" + 
  	  		"       \"description\": \"third defect created\",\r\n" + 
  	  		"       \"issuetype\": {\r\n" + 
  	  		"          \"name\": \"Bug\"\r\n" + 
  	  		"       }\r\n" + 
  	  		"   }\r\n" + 
  	  		"}").
  	  when().post("/rest/api/2/issue/").
  	  then().assertThat().statusCode(201).extract().response();
  	  
  	 JsonPath js1=new JsonPath(res1.asString());
  	 System.out.println(js1.getString("id"));
  	 System.out.println(js1.getString("key"));
  	 System.out.println(js1.getString("self"));
  	 
  	 
  	  
  	  
  	  
  	  
    }
}
