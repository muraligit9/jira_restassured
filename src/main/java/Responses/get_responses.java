package Responses;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Properties;

import Reusable_methods.declaringhost;
import Reusable_methods.property_load;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;

public class get_responses extends declaringhost
{
	
	public  Response session_response(String username, String password) throws IOException
	{
	       host_initialization();
		   Response res=given().
		   header("content-Type", "application/json"). 
		   body("{ \"username\": \""+username+"\", \"password\": \""+password+"\" }").
		   when().
		   post("/rest/auth/1/session").
		   then().assertThat().statusCode(200).
		   extract().response();
		   
		   return res;
	}
	
	public  Response create_issue_response(String cookie_id, String key, String description) throws IOException
	{
		host_initialization();
		Response res=given().
				  header("Content-Type","application/json").
				  header("cookie",cookie_id).
				  body("{\r\n" + 
				  		"    \"fields\": {\r\n" + 
				  		"       \"project\":\r\n" + 
				  		"       {\r\n" + 
				  		"          \"key\": \""+key+"\"\r\n" + 
				  		"       },\r\n" + 
				  		"       \"summary\": \"Defect-2\",\r\n" + 
				  		"       \"description\": \""+description+"\",\r\n" + 
				  		"       \"issuetype\": {\r\n" + 
				  		"          \"name\": \"Bug\"\r\n" + 
				  		"       }\r\n" + 
				  		"   }\r\n" + 
				  		"}").
				  when().
				  post("/rest/api/2/issue/").
				  then().
				  assertThat().statusCode(201).
				  extract().response();
		return res;
	}
	
	public Response Get_issue_response(String cookie_id,String issue_id) throws IOException
	{
		host_initialization();
		Response res=given().
				  header("Accept","application/json").
				  header("cookie",cookie_id).
				  when().
				  get("/rest/api/2/issue/"+issue_id).
				  then().
				  assertThat().statusCode(200).and().
				  body("id",equalTo(issue_id)).extract().response();
		return res;
	}
	 
	public Response add_comment_response(String cookie_id,String issue_id,String comment_description) throws IOException
	{
		host_initialization();
		Response res=given().
				  header("Content-Type","application/json").
				  header("cookie",cookie_id).
				  body("{\r\n" + 
				  		"    \"body\": \""+comment_description+"\",\r\n" + 
				  		"    \"visibility\": {\r\n" + 
				  		"        \"type\": \"role\",\r\n" + 
				  		"        \"value\": \"Administrators\"\r\n" + 
				  		"    }\r\n" + 
				  		"}").
                  when().
                  post("/rest/api/2/issue/"+issue_id+"/comment").
                  then().
                  assertThat().statusCode(201).and().body("body",equalTo(comment_description)).
                  extract().response();
		
		return res;
	}
	
	public Response get_comment_response(String cookie_id,String issue_id) throws IOException
	{
		host_initialization();
		Response res=given().
				  header("cookie",cookie_id).
				  when().
				  get("/rest/api/2/issue/"+issue_id+"/comment").
				  then().
				  assertThat().statusCode(200).extract().response();
		
		return res;
	}
	
	public void delete_comment_response(String cookie_id,String issue_id, String comment_id) throws IOException
	{
		host_initialization();
		          given().
				  header("cookie",cookie_id).
				  when().
				  delete("/rest/api/2/issue/"+issue_id+"/comment/"+comment_id+"").
				  then().
				  assertThat().statusCode(204);
		
	}
	
	public Response update_comment_response(String cookie_id,String issue_id, String comment_id,String update_comment_description) throws IOException
	{
		host_initialization();
		Response res=given().
				  header("cookie",cookie_id).
				  header("Content-Type","application/json").
				  body("{\r\n" + 
				  		"    \"body\": \""+update_comment_description+"\",\r\n" + 
				  		"    \"visibility\": {\r\n" + 
				  		"        \"type\": \"role\",\r\n" + 
				  		"        \"value\": \"Administrators\"\r\n" + 
				  		"    }\r\n" + 
				  		"}").
				  when().
				  put("/rest/api/2/issue/"+issue_id+"/comment/"+comment_id+"").
				  then().
				  assertThat().statusCode(200).extract().response();
		
		return res;
		
	}
	
	public void delete_issue_response(String cookie_id, String issue_id) throws IOException
	{
		host_initialization();
		given().
				  header("cookie",cookie_id).
				  when().
				  delete("/rest/api/2/issue/"+issue_id+"").
				  then().
				  assertThat().statusCode(204).extract().response();
		
	}

}
