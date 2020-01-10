package RESTAPI_package;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import Reusable_methods.property_load;
import io.restassured.RestAssured;

public class delete_comment 
{
  @Test
  public void delete() throws IOException
  {
	  Properties p=property_load.load_properties();
	   RestAssured.baseURI=p.getProperty("host");
	   
	   String cookie_id="JSESSIONID=33EF2DF91D3646266ADFE833AE5C1007";
	   /*String issue_id="10006";
	   String comment_id="10009";*/
	   
	   given().
		  header("cookie",cookie_id).
		  pathParam("commentid", 10006).
		  when().
		  delete("/rest/api/2/issue/{commentid}/comment/10009").
		  then().
		  assertThat().statusCode(200);
  }
}
