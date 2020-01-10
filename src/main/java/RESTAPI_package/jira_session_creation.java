package RESTAPI_package;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import Responses.get_responses;
import Reusable_methods.declaringhost;
import Reusable_methods.jsonpath;
import Reusable_methods.property_load;



public class jira_session_creation extends declaringhost
{
	public get_responses  g=new get_responses();
   @Test	
   public String create_session(String username, String password) throws IOException
   {
	   
	   host_initialization();
	   
	  /* Response res=given().
	   header("content-Type", "application/json").
	   body("{ \"username\": \"muralijira\", \"password\": \"Krish@1992\" }").
	   when().
	   post("/rest/auth/1/session").
	   then().assertThat().statusCode(200).
	   extract().response();*/
	   Response r=g.session_response(username,password);
	   /*String res_string=session_response.response().asString();
	   
	   System.out.println(res_string);
	   
	   JsonPath js= new JsonPath(res_string);*/
	   
	   //String name=jsonpath.StringtoJson(r).get("name");
	   String session_ID=jsonpath.StringtoJson(r).get("session.value");
	   
	   return session_ID;
	   
	   
	   
   }
}
